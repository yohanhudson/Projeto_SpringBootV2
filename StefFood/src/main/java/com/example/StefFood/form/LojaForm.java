package com.example.StefFood.form;


import com.example.StefFood.modelo.Loja;
import com.example.StefFood.repository.LojaRepository;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LojaForm {

    @NotNull
    @Length(min = 5)
    private String nome;
    @NotNull
    @Length(min = 5)
    private String endereco;
    @NotNull
    @Length(min = 5)
    private String cnpj;


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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Loja converter(LojaRepository lojaRepository) {
        Loja loja = lojaRepository.findByNome(nome);
        return new Loja(nome, endereco, cnpj);
    }
}
