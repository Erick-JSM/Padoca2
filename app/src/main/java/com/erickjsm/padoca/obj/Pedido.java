package com.erickjsm.padoca.obj;

public class Pedido {

    private String codPedido;
    private String data;
    private String qtdPedida;
    private String CpfFuncionario;

    public String getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(String codPedido) {
        this.codPedido = codPedido;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getQtdPedida() {
        return qtdPedida;
    }

    public void setQtdPedida(String qtdPedida) {
        this.qtdPedida = qtdPedida;
    }

    public String getCpfFuncionario() {
        return CpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        CpfFuncionario = cpfFuncionario;
    }
}
