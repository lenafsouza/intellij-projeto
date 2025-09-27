package com.example.projeto.controller;

import com.example.projeto.business.LivroService;
import com.example.projeto.infrastructure.entitys.Livro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // permite acesso do frontend
public class LivroController {

    private final LivroService service;

    @PostMapping
    public ResponseEntity<String> salvarLivro(@RequestBody Livro livro){
        service.salvarLivro(livro);
        return ResponseEntity.ok("Livro cadastrado!");
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listarLivros(){
        return ResponseEntity.ok(service.listarTodos());
    }

    // NOVO MÉTODO para buscar livro pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPorId(@PathVariable Integer id){
        Livro livro = service.buscarLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarLivro(@PathVariable Integer id, @RequestBody Livro livro){
        service.atualizarLivroPorId(id, livro);
        return ResponseEntity.ok("Livro atualizado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Integer id){
        service.deletarLivroPorId(id);
        return ResponseEntity.ok("Livro deletado!");
    }

}
