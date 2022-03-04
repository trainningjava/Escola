package com.acc.escola.model;

public enum Sexo {

        M(1,"Masculino"),
        F(2,"Feminino"),
        O(3,"Outros");

        private int cod;
        private String label;

        private Sexo(Integer id, String label){
            this.cod = id;
            this.label = label;
        }

    public int getCod() {
        return cod;
    }

    public String getlabel(){
            return label;
        }

        public static Sexo toEnum(Integer cod){
            if(cod == null) {
                return null;
            }
            for(Sexo x : Sexo.values()){
                if(cod.equals(x.getCod())){
                    return x;
                }
            }
            throw new IllegalArgumentException("ID inválido:" + cod);
    }
}
