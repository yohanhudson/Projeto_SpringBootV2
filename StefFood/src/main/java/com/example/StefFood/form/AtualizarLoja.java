package com.example.StefFood.form;


import com.example.StefFood.modelo.Loja;
import com.example.StefFood.repository.LojaRepository;
import com.example.StefFood.service.LojaService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class AtualizarLoja {


    @NotNull @Length(min = 5)
    private String nome;
    @NotNull @Length(min = 5)
    private String endereco;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Loja atualizar(Long id, LojaService lojaService) {
        Loja loja = lojaService.findById(id);

        loja.setNome(this.nome);
        loja.setEndereco(this.endereco);

        return loja;
    }

}
