package com.example.StefFood.controller;

import com.example.StefFood.dto.UsuarioDto;
import com.example.StefFood.form.AtualizarUsuario;
import com.example.StefFood.form.UsuarioForm;
import com.example.StefFood.modelo.Usuario;
import com.example.StefFood.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDto> lista() {
        List<Usuario> usuarios = usuarioService.findAll();

        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        form.setSenha(new BCryptPasswordEncoder().encode(form.getSenha()));
        Usuario usuario = form.converter(usuarioService);
        usuarioService.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }

    @GetMapping("/{id}")
    @Transactional
    public UsuarioDto detalhar(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);

        return new UsuarioDto(usuario);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarUsuario form) {
        Usuario usuario = form.atualizar(id, usuarioService);

        return ResponseEntity.ok(new UsuarioDto(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        usuarioService.deleteById(id);

        return ResponseEntity.ok("Cliente deletada com sucesso");
    }
}
