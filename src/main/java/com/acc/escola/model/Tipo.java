package com.acc.escola.model;

public enum Tipo {
    A(1,"Aluno"),
    B(2, "Bolsista");

    private int cod;
    private String label;

    private Tipo(Integer id, String label){
        this.cod = id;
        this.label = label;
    }

    public int getCod() {
        return cod;
    }

    public String getlabel(){
        return label;
    }

    public static Tipo toEnum(Integer cod){
        if(cod == null) {
            return null;
        }
        for(Tipo x : Tipo.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("ID inválido:" + cod);
    }
}
