package com.example.StefFood.service;

import com.example.StefFood.modelo.Usuario;
import com.example.StefFood.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }


    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.getOne(id);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }
}
