package com.example.StefFood.controller;

import com.example.StefFood.dto.UsuarioDto;
import com.example.StefFood.form.AtualizarCliente;
import com.example.StefFood.form.UsuarioForm;
import com.example.StefFood.modelo.Usuario;
import com.example.StefFood.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDto> lista() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter(usuarioRepository);
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @GetMapping("/{id}")
    @Transactional
    public UsuarioDto detalhar(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getOne(id);

        return new UsuarioDto(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarCliente form) {
        Usuario usuario = form.atualizar(id, usuarioRepository);

        return ResponseEntity.ok(new UsuarioDto(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        usuarioRepository.deleteById(id);

        return ResponseEntity.ok("Cliente deletada com sucesso");
    }
}
