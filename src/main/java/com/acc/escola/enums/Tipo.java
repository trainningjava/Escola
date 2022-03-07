package com.acc.escola.enums;

public enum Tipo {
    ALUNO(1),
    BOLSISTA(2);

    private int cod;

    private Tipo(Integer id){
        this.cod = id;
    }

    public int getCod() {
        return cod;
    }


    public static Tipo toEnum(Tipo cod){
        for(Tipo x : Tipo.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }
}
