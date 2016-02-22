/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.transaccion;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import com.teamj.distribuidas.integracion.util.MyStringUtil;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LuisAlberto
 */
public class DepositoRQ implements Cuerpo {

    private String numeroCuenta;
    private String tipoCuenta;
    private String valorDeposito;
    private String documentoCliente;
    private String fechaDeposito;

    @Override
    public String asTexto() {
        return this.numeroCuenta + this.tipoCuenta + this.valorDeposito + this.documentoCliente + this.fechaDeposito;
    }

    @Override
    public boolean validate(String input) {
        return input.length() == 52;
    }

    @Override
    public void build(String input) {
        if (validate(input)) {
            try {

                String values[] = MyStringUtil.splitByFixedLengths(input, new int[]{11, 2, 10, 15, 14});
                this.numeroCuenta = values[0];
                this.tipoCuenta = values[1];
                this.valorDeposito = values[2];
                this.documentoCliente = values[3];
                this.fechaDeposito = values[4];

            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
    }

    public String getNumeroCuenta() {
        return numeroCuenta.trim();
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = StringUtils.rightPad(numeroCuenta, 11);
    }

    public String getTipoCuenta() {
        return tipoCuenta.trim();
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = StringUtils.rightPad(tipoCuenta, 2);
    }

    public String getValorDeposito() {
        return valorDeposito;
    }

    public void setValorDeposito(String valorDeposito) {
        this.valorDeposito = StringUtils.leftPad(valorDeposito, 10, "0");
    }

    public String getDocumentoCliente() {
        return documentoCliente.trim();
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = StringUtils.rightPad(documentoCliente, 15);
    }

    public String getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(String fechaDeposito) {
        this.fechaDeposito = StringUtils.rightPad(fechaDeposito, 14);
    }

}
