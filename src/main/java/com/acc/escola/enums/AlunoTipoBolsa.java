package com.acc.escola.enums;

public enum AlunoTipoBolsa {
    B00 (0),
    B90 (90/100),
    B50 (50/100),
    B10 (10/100);

    private int code;

    AlunoTipoBolsa(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}


