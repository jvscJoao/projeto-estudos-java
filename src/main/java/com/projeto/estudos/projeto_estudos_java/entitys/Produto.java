package com.projeto.estudos.projeto_estudos_java.entitys;

import com.projeto.estudos.projeto_estudos_java.enums.CategoriaProduto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Nome não pode ser vazio!")
    @NotEmpty(message = "Nome não pode ser vazio!")
    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoriaProduto;

    @Positive(message = "Número precisa ser maior que 0.")
    @Min(value = 1)
    private Double valor;

    public Produto() {}

    public Produto(String nome, CategoriaProduto categoriaProduto, Double valor) {
        this.nome = nome;
        this.categoriaProduto = categoriaProduto;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CategoriaProduto getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(CategoriaProduto categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    
}
