package com.example.StefFood.dto;


import com.example.StefFood.modelo.Loja;

import java.util.List;
import java.util.stream.Collectors;

public class LojaDto {


    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;

    public LojaDto(Loja loja) {
        this.id = loja.getId();
        this.nome = loja.getNome();
        this.endereco = loja.getEndereco();
        this.cnpj = loja.getCnpj();
    }

    public LojaDto(){
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public static List<LojaDto> converter(List<Loja> lojas){
        return lojas.stream().map(LojaDto::new).collect(Collectors.toList());
    }
}
