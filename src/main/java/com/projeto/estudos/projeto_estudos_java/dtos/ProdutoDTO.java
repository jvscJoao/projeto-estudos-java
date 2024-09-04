package com.projeto.estudos.projeto_estudos_java.dtos;

import com.projeto.estudos.projeto_estudos_java.enums.CategoriaProduto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProdutoDTO(
    Integer id,
    @NotBlank @NonNull String nome,
    @NotNull CategoriaProduto categoriaProduto,
    @Positive @Min(value = 1) Double valor
) {
    
}
