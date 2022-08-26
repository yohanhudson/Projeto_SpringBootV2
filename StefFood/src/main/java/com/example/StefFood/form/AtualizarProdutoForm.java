package com.example.StefFood.form;

import com.example.StefFood.modelo.Produto;
import com.example.StefFood.repository.ProdutoRepository;
import com.example.StefFood.service.ProdutoService;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AtualizarProdutoForm {

    @NotNull @NotEmpty @Length(min = 5)
    private String descricao;

    @NotNull
    private Double valor;



    private String getDescricao() {
        return descricao;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private Double getValor() {
        return valor;
    }

    private void setValor(Double valor) {
        this.valor = valor;
    }

    public Produto atualizar(Long id, ProdutoService produtoService) {
        Produto produto = produtoService.findById(id);

        produto.setDescricao(this.descricao);
        produto.setValor(this.valor);

        return produto;
    }
}
