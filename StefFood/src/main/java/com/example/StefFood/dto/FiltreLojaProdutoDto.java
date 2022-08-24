package com.example.StefFood.dto;

import com.example.StefFood.modelo.Produto;

public class FiltreLojaProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double valor;

    public FiltreLojaProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }
}
