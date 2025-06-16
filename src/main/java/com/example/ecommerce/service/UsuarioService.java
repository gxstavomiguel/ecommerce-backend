package com.example.ecommerce.service;

import com.example.ecommerce.entity.Usuario;
import com.example.ecommerce.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Usuario usuario, Long id) {
        Usuario usuarioParaSerAtualizado = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com id " + id));

        usuarioParaSerAtualizado.setNome(usuario.getNome());
        usuarioParaSerAtualizado.setEmail(usuario.getEmail());
        usuarioParaSerAtualizado.setEndereco(usuario.getEndereco());
        return usuarioRepository.save(usuarioParaSerAtualizado);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
}
