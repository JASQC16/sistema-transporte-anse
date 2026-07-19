package edu.upc.anse.backendanse.security;

import edu.upc.anse.backendanse.entity.Usuario;
import edu.upc.anse.backendanse.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UsuarioDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario usuarioEntity = usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + usuario));

        return new User(
                usuarioEntity.getUsuario(),
                usuarioEntity.getContrasenia(),
                usuarioEntity.getEstado(),
                true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(usuarioEntity.getRol()))
        );
    }
}