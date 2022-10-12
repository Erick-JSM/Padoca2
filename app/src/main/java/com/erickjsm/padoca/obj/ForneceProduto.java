package com.erickjsm.padoca.obj;

import java.util.Date;

public class ForneceProduto {

    private Date data;
    private int qtdFornecida;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQtdFornecida() {
        return qtdFornecida;
    }

    public void setQtdFornecida(int qtdFornecida) {
        this.qtdFornecida = qtdFornecida;
    }
}
