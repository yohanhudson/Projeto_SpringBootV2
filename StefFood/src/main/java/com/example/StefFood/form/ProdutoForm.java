package com.example.StefFood.form;

import com.example.StefFood.modelo.Loja;
import com.example.StefFood.modelo.Produto;
import com.example.StefFood.repository.LojaRepository;
import com.example.StefFood.service.LojaService;
import com.example.StefFood.service.ProdutoService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class ProdutoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String nome;
    @NotNull @NotEmpty @Length(min = 5)
    private String descricao;
    @NotNull
    private Double valor;
    @NotNull @NotEmpty @Length(min = 5)
    private String nomeLoja;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public Produto converter(LojaService lojaService) {
        Loja loja = lojaService.findByNome(nomeLoja);
        return new Produto(nome, descricao, valor, loja);
    }
}
