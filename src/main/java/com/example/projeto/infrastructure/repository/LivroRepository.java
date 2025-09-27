package com.example.projeto.infrastructure.repository;

import com.example.projeto.infrastructure.entitys.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Integer> {
    Optional<Livro> findByTitulo(String titulo);
    void deleteByTitulo(String titulo);
}