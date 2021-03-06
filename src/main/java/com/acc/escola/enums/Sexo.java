package com.acc.escola.enums;

public enum Sexo {

    MASCULINO("M"),
    FEMININO("F"),
    OUTROS("O");

    private final String cod;

    Sexo(String id){
        this.cod = id;
    }

    public String getCod() {
        return cod;
    }

    public static Sexo toEnum(String cod){
        for(Sexo x : Sexo.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }

    public static String toDesc(String cod){
        for(Sexo x : Sexo.values()){
            if(cod.equals(x.getCod())){
                return x.name();
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }
}
