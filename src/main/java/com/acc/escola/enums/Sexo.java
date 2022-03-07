package com.acc.escola.enums;

public enum Sexo {

        MASCULINO(1),
        FEMININO(2),
        OUTROS(3);

        private int cod;

        private Sexo(Integer id){
            this.cod = id;
        }

        public int getCod() {
        return cod;
    }


        public static Sexo toEnum(Sexo cod){
            for(Sexo x : Sexo.values()){
                if(cod.equals(x.getCod())){
                    return x;
                }
            }
            throw new IllegalArgumentException("ID inválido:" + cod);
    }
}
