package com.example.StefFood.dto;

import com.example.StefFood.modelo.Loja;
import com.example.StefFood.modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LojaProdutoDto {

    private Long id;

    private String nome;

    private List<FiltreLojaProdutoDto> produto = new ArrayList<>();


    public LojaProdutoDto(Loja loja){
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.produto = new ArrayList<>();
        this.produto.addAll(loja.getProduto().stream().map(FiltreLojaProdutoDto::new).collect(Collectors.toList()));
    }



    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<FiltreLojaProdutoDto> getProduto() {
        return produto;
    }
}
