package com.projeto.estudos.projeto_estudos_java.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.projeto.estudos.projeto_estudos_java.dtos.ProdutoDTO;
import com.projeto.estudos.projeto_estudos_java.entitys.Produto;
import com.projeto.estudos.projeto_estudos_java.exceptions.ObjectNotFoundException;
import com.projeto.estudos.projeto_estudos_java.repositories.ProdutoRepository;


@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> searchAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Produto insert(ProdutoDTO objDTO) {
        Produto obj = dtoToEntity(objDTO);
        obj.setId(null);
        return produtoRepository.save(obj);
    }

    public Produto update(Integer id, ProdutoDTO objDTO) {
        Produto updatedObj = findById(id);
        updatedObj.setNome(objDTO.nome());
        updatedObj.setCategoriaProduto(objDTO.categoriaProduto());
        updatedObj.setValor(objDTO.valor());
        return produtoRepository.save(updatedObj);
    }

    public void deleteById(Integer id) {
        findById(id);
        produtoRepository.deleteById(id);
    }

    public Produto dtoToEntity(ProdutoDTO objDTO) {
        Produto obj = new Produto();
        obj.setId(objDTO.id());
        obj.setNome(objDTO.nome());
        obj.setCategoriaProduto(objDTO.categoriaProduto());
        obj.setValor(objDTO.valor());
        return obj;
    }

}
