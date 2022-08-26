package com.example.StefFood.controller;


import com.example.StefFood.dto.LojaDto;
import com.example.StefFood.dto.LojaProdutoDto;
import com.example.StefFood.form.AtualizarLoja;
import com.example.StefFood.form.LojaForm;
import com.example.StefFood.modelo.Loja;
import com.example.StefFood.repository.LojaRepository;
import com.example.StefFood.repository.ProdutoRepository;
import com.example.StefFood.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaService lojaService;


    @GetMapping
    @Cacheable(value = "listaDeLojas")
    public List<LojaDto> lista(){
        List<Loja> lojas = lojaService.findAll();
        return LojaDto.converter(lojas);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeLojas", allEntries = true)
    public ResponseEntity<LojaDto> cadastrar(@RequestBody @Valid LojaForm form, UriComponentsBuilder uriBuilder){
        Loja loja = form.converter(lojaService);
        lojaService.save(loja);

        URI uri = uriBuilder.path("/lojas/{id}").buildAndExpand(loja.getId()).toUri();
        return ResponseEntity.created(uri).body(new LojaDto(loja));
    }

    @GetMapping("/{id}")
    @Transactional
    public LojaDto detalhar(@PathVariable Long id) {
        Loja loja = lojaService.findById(id);

        return new LojaDto(loja);
    }

    @GetMapping("/lojaprodutos/{id}")
    @Transactional
    public LojaProdutoDto mostrarProdutos(@PathVariable Long id) {
        Loja loja = lojaService.findById(id);
        return new LojaProdutoDto(loja);
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeLojas", allEntries = true)
    public ResponseEntity<LojaDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarLoja form) {
        Loja loja = form.atualizar(id, lojaService);

        return ResponseEntity.ok(new LojaDto(loja));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeLojas", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id) {
        lojaService.deleteById(id);


        return ResponseEntity.ok("Loja deletada com sucesso");
    }
}
