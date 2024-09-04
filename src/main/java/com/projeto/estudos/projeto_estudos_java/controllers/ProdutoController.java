package com.projeto.estudos.projeto_estudos_java.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projeto.estudos.projeto_estudos_java.dtos.ProdutoDTO;
import com.projeto.estudos.projeto_estudos_java.entitys.Produto;
import com.projeto.estudos.projeto_estudos_java.services.ProdutoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> searchAll() {
        List<Produto> list = produtoService.searchAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> searchById(@PathVariable @Positive Integer id) {
        Produto obj = produtoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Produto> insert(@RequestBody @Valid ProdutoDTO objDTO) {
        Produto newObj = produtoService.insert(objDTO);
        URI locator = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(newObj.getId())
        .toUri();
        return ResponseEntity.created(locator).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable @Positive Integer id, @RequestBody @Valid ProdutoDTO objDTO) {
        Produto obj = produtoService.update(id, objDTO);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Positive Integer id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
