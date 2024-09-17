package com.projeto.estudos.projeto_estudos_java.enums;

public enum Perfil {
    ADMIN(1),
    GERENTE(2),
    VENDEDOR(3);

    private Integer id;

    Perfil(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static Perfil toEnum(Integer id) {
        if (id == null) { return null; }
        for (Perfil x: Perfil.values()) {
            if (id.equals(x.getId())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
    
}
