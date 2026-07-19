package edu.upc.anse.backendanse.controller;

import edu.upc.anse.backendanse.dto.AuthResponse;
import edu.upc.anse.backendanse.dto.LoginRequest;
import edu.upc.anse.backendanse.dto.RegistroRequest;
import edu.upc.anse.backendanse.entity.Usuario;
import edu.upc.anse.backendanse.repository.UsuarioRepository;
import edu.upc.anse.backendanse.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtService jwtService,
                          PasswordEncoder passwordEncoder,
                          UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsuario(),
                        request.getContrasenia()
                )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        String rol = userDetails.getAuthorities().iterator().next().getAuthority();

        return ResponseEntity.ok(new AuthResponse(token, userDetails.getUsername(), rol));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistroRequest request) {
        if (usuarioRepository.existsByUsuario(request.getUsuario())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El usuario ya existe: " + request.getUsuario());
        }

        String rol = request.getRol();
        if (rol == null || (!rol.equals("ROLE_ADMIN") && !rol.equals("ROLE_USER"))) {
            rol = "ROLE_USER";
        }

        Usuario usuario = new Usuario();
        usuario.setUsuario(request.getUsuario());
        usuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        usuario.setRol(rol);
        usuario.setEstado(true);

        usuarioRepository.save(usuario);

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getContrasenia())
                .roles(rol.replace("ROLE_", ""))
                .build();

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token, usuario.getUsuario(), usuario.getRol()));
    }
}