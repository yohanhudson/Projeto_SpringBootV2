package com.example.StefFood.dto;

import com.example.StefFood.modelo.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double valor;

    private String nomeLoja;

    public ProdutoDto(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.nomeLoja = produto.getLoja().getNome();
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

    public String getNomeLoja(){
        return nomeLoja;
    }

    public static List<ProdutoDto> converter(List<Produto> produtos){
        return produtos.stream().map(ProdutoDto::new).collect(Collectors.toList());
    }
}
