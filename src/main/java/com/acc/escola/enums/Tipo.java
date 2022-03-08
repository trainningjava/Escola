package com.acc.escola.enums;

public enum Tipo {
    SEM_BOLSA("1"),
    BOLSISTA("2");

    private String cod;

    Tipo(String id){
        this.cod = id;
    }

    public String getCod() {
        return cod;
    }


    public static Tipo toEnum(String cod){
        for(Tipo x : Tipo.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }

    public static String toDesc(String cod){
        for(Tipo x : Tipo.values()){
            if(cod.equals(x.getCod())){
                return x.name();
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }
}
