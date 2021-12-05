package com.redbank.bankaccount.model.collection;

import javax.validation.constraints.NotBlank;

public class Conta {

    @NotBlank
    private String agencia;

    @NotBlank
    private String conta;

    private String tipoConta;

    private Double saldo;

    public Conta(String agencia, String conta, Double saldo, String tipoConta) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.tipoConta = tipoConta;
    }

    public  Conta(){

    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }


}
