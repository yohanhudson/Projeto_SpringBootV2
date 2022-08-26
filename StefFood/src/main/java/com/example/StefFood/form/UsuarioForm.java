package com.example.StefFood.form;



import com.example.StefFood.modelo.Usuario;
import com.example.StefFood.repository.UsuarioRepository;
import com.example.StefFood.service.UsuarioService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UsuarioForm {

    @NotNull @Length(min = 5)
    private String nome;
    @NotNull @Length(min = 5)
    private String email;
    @NotNull @Length(min = 5)
    private String senha;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario converter(UsuarioService usuarioService) {
        Usuario usuario = usuarioService.findByNome(nome);
        return new Usuario(nome, email, senha);
    }
}
