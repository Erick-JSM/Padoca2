package com.erickjsm.padoca.obj;

public class Funcionario extends Pessoa{
    // Variaveis
    private String cargo;
    private String salario;

    // Gets e Sets

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getSalario() {
        return salario;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
}
