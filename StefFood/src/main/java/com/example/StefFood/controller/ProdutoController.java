package com.example.StefFood.controller;

import com.example.StefFood.dto.ProdutoDto;
import com.example.StefFood.form.AtualizarProdutoForm;
import com.example.StefFood.form.ProdutoForm;
import com.example.StefFood.modelo.Produto;
import com.example.StefFood.repository.LojaRepository;
import com.example.StefFood.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @GetMapping
    public List<ProdutoDto> lista(String nomeLoja) {
        if (nomeLoja == null) {
            List<Produto> produtos = produtoRepository.findAll();
            return ProdutoDto.converter(produtos);
        } else {
            List<Produto> produtos = produtoRepository.findByLojaNome(nomeLoja);
            return ProdutoDto.converter(produtos);
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        Produto produto = form.converter(lojaRepository);
        produtoRepository.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping("/{id}")
    @Transactional
    public ProdutoDto detalhar(@PathVariable Long id) {
        Produto produto = produtoRepository.getOne(id);
        return new ProdutoDto(produto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarProdutoForm form) {
       Produto produto = form.atualizar(id, produtoRepository);

       return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        produtoRepository.deleteById(id);

        return ResponseEntity.ok("Produto deletado com sucesso");
    }
}