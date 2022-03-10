package com.acc.escola.enums;

public enum Bolsa {

    B90 (90),
    B50 (50),
    B10 (10),
    B0 (0);

    private int code;

    Bolsa(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Bolsa toEnum(int cod){
        for(Bolsa x : Bolsa.values()){
            if(cod == x.getCode()){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }

    public static String toDesc(int cod){
        for(Bolsa x : Bolsa.values()){
            if(cod == x.getCode()){
                return x.name();
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }

}


