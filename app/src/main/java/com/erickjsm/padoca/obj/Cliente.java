package com.erickjsm.padoca.obj;


public class Cliente extends Pessoa{

  private String valorPago;
  private String divida;

  public String getValorPago() {
    return valorPago;
  }
  public void setValorPago(String valorPago) {
    this.valorPago = valorPago;
  }
  public String getDivida() {
    return divida;
  }
  public void setDivida(String divida) {
    this.divida = divida;
  }
}
