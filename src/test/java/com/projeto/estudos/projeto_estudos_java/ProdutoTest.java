package com.projeto.estudos.projeto_estudos_java;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projeto.estudos.projeto_estudos_java.dtos.ProdutoDTO;
import com.projeto.estudos.projeto_estudos_java.entitys.Produto;
import com.projeto.estudos.projeto_estudos_java.enums.CategoriaProduto;
import com.projeto.estudos.projeto_estudos_java.exceptions.ObjectNotFoundException;
import com.projeto.estudos.projeto_estudos_java.repositories.ProdutoRepository;
import com.projeto.estudos.projeto_estudos_java.services.ProdutoService;

@ExtendWith(MockitoExtension.class)
public class ProdutoTest {

    /*
     Junit é um framework de teste unitários que fornece um ambiente de execução para nossos testes;
     Mockito é uma biblioteca que nos oferece uma série de ferramentas para teste;
    */

    
    @InjectMocks // Anotação a qual quero testar
    private ProdutoService service;

    @Mock // É usado para criar uma instância simulada de uma classe ou interface.
    private ProdutoRepository repository; // Simulação do repositório usado pelo serviço

    @Test
    @DisplayName("Deve salvar um novo produto") //Insert
    public void sucessoAoInserirProduto() {
        ProdutoDTO prodDTO = new ProdutoDTO(null, "Testando", CategoriaProduto.ALCOOLICOS, 2.0);

        Produto produto = service.dtoToEntity(prodDTO);

        when(repository.save(eq(produto))).thenReturn(produto);
        /*
            Caso eu queira apenas verificar se alguma classe produto foi adicionada, ao em vez de eq(), posso usar o any(Produto.class)
        */
        Produto newProd = service.insert(prodDTO);
        assertEquals(produto, newProd);
    }

    @Test
    @DisplayName("Deve achar um produto pelo ID") // SearchById
    public void AcharProdutoPeloID() {
        ProdutoDTO prodDTO = new ProdutoDTO(1, "Testando", CategoriaProduto.ALCOOLICOS, 2.0);
        Produto produto = service.dtoToEntity(prodDTO);

        when(repository.findById(1)).thenReturn(Optional.of(produto));

        Produto findProduto = service.findById(1);
        assertEquals(produto, findProduto);
    }

    @Test
    @DisplayName("Deve retornar um ObjectNotFoundException caso não ache o produto passando o ID") // SearchById Error
    public void errorAoNaoAcharUmProduto() {
        when(repository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> service.findById(1));
    }

    @Test
    @DisplayName("Deve retornar uma lista de produtos") // SearchAll
    public void retornarListaDeProdutos() {
        Produto produto1 = new Produto("Produto 1", CategoriaProduto.ALCOOLICOS, 10.0);
        Produto produto2 = new Produto("Produto 2", CategoriaProduto.BEBIDAS, 5.0);
        
        List<Produto> produtos = Arrays.asList(produto1, produto2);
        when(repository.findAll()).thenReturn(produtos);
        List<Produto> result = service.searchAll();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Devo retornar o valor de produto atualizado") // Update
    public void atualizarDadosDoProduto() {
        ProdutoDTO antigoProdutoDTO = new ProdutoDTO(1, "Produto 1", CategoriaProduto.ALCOOLICOS, 10.0);
        Produto antigoProduto = service.dtoToEntity(antigoProdutoDTO);

        ProdutoDTO novoProdutoDTO = new ProdutoDTO(1, "Produto Atualizado", CategoriaProduto.ALCOOLICOS, 20.0);
        Produto novoProduto = service.dtoToEntity(antigoProdutoDTO);

        when(repository.findById(1)).thenReturn(Optional.of(antigoProduto));

        when(repository.save(antigoProduto)).thenReturn(novoProduto);

        Produto result = service.update(1, novoProdutoDTO);

        assertEquals(novoProduto, result);
    }

    @Test
    @DisplayName("Deve deletar um produto existente com sucesso")
    public void deleteProdutoComSucessoTest() {
        ProdutoDTO produtoDTO = new ProdutoDTO(1, "Produto 1", CategoriaProduto.ALCOOLICOS, 10.0);
        Produto produto = service.dtoToEntity(produtoDTO);
        when(repository.findById(1)).thenReturn(Optional.of(produto));
        service.deleteById(1);

        verify(repository, times(1)).deleteById(1);
    }
    


}
