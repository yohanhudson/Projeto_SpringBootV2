package com.example.StefFood.service;

import com.example.StefFood.config.validacao.ErroDeFormularioDto;
import com.example.StefFood.dto.LojaDto;
import com.example.StefFood.modelo.Loja;
import com.example.StefFood.repository.LojaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LojaService {

    private final LojaRepository  lojaRepository;

    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public Loja findById(Long id) {
        return lojaRepository.getOne(id);
    }

    public List<Loja> findAll() {
        return lojaRepository.findAll();
    }

    public Loja save(Loja loja) {
        return lojaRepository.save(loja);
    }

    public Loja findByNome(String nome) {
        return lojaRepository.findByNome(nome);
    }

    public void deleteById(Long id) {
        lojaRepository.deleteById(id);
    }
}
