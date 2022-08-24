package com.example.StefFood.repository;

import com.example.StefFood.modelo.Loja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LojaRepository extends JpaRepository<Loja, Long> {

    Loja findByNome(String nomeLoja);


}
