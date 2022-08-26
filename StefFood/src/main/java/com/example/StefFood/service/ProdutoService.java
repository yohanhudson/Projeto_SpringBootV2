package com.example.StefFood.service;

import com.example.StefFood.modelo.Loja;
import com.example.StefFood.modelo.Produto;
import com.example.StefFood.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public void save(Produto produto) {
        produtoRepository.save(produto);
    }

    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto findById(Long id) {
        return produtoRepository.getOne(id);
    }
}
