package com.projeto.estudos.projeto_estudos_java.enums;

public enum CategoriaProduto {

    BEBIDAS(1),
    CONSUMIVEL(2),
    WHISKY(3),
    PETISCOS(4),
    ALCOOLICOS(5);

    private Integer id;

    private CategoriaProduto(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
