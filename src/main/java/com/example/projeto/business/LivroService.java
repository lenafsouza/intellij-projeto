package com.example.projeto.business;

import com.example.projeto.infrastructure.entitys.Livro;
import com.example.projeto.infrastructure.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;

    public void salvarLivro(Livro livro){
        repository.saveAndFlush(livro);
    }

    public Livro buscarLivroPorTitulo(String titulo){
        return repository.findByTitulo(titulo).orElseThrow(
                () -> new RuntimeException("Livro não encontrado")
        );
    }

    public Livro buscarLivroPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public void deletarLivroPorId(Integer id){
        Livro livro = repository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        repository.delete(livro);
    }


    public void atualizarLivroPorId(Integer id, Livro livro){
        Livro livroEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Livro não encontrado")
        );
        Livro atualizado = Livro.builder()
                .id(livroEntity.getId())
                .titulo(livro.getTitulo() != null ? livro.getTitulo() : livroEntity.getTitulo())
                .autor(livro.getAutor() != null ? livro.getAutor() : livroEntity.getAutor())
                .anoPublicacao(livro.getAnoPublicacao() != null ? livro.getAnoPublicacao() : livroEntity.getAnoPublicacao())
                .status(livro.getStatus() != null ? livro.getStatus() : livroEntity.getStatus())
                .build();

        repository.saveAndFlush(atualizado);
    }

    public List<Livro> listarTodos(){
        return repository.findAll();
    }
}
