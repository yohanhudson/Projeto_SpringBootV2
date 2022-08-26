package com.example.StefFood.controller;

import com.example.StefFood.dto.ProdutoDto;
import com.example.StefFood.form.AtualizarProdutoForm;
import com.example.StefFood.form.ProdutoForm;
import com.example.StefFood.modelo.Produto;
import com.example.StefFood.repository.LojaRepository;
import com.example.StefFood.repository.ProdutoRepository;
import com.example.StefFood.service.LojaService;
import com.example.StefFood.service.ProdutoService;
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
    private ProdutoService produtoService;

    @Autowired
    private LojaService lojaService;

    @GetMapping
    public List<ProdutoDto> lista(String nomeLoja) {
            List<Produto> produtos = produtoService.findAll();
            return ProdutoDto.converter(produtos);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm form, UriComponentsBuilder uriBuilder) {
        Produto produto = form.converter(lojaService);
        produtoService.save(produto);

        URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDto(produto));
    }

    @GetMapping("/{id}")
    @Transactional
    public ProdutoDto detalhar(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        return new ProdutoDto(produto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarProdutoForm form) {
       Produto produto = form.atualizar(id, produtoService);

       return ResponseEntity.ok(new ProdutoDto(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover(@PathVariable Long id) {
        produtoService.deleteById(id);

        return ResponseEntity.ok("Produto deletado com sucesso");
    }
}