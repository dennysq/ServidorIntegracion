/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.transaccion;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import java.math.BigDecimal;

/**
 *
 * @author LuisAlberto
 */
public class RetiroRQ implements Cuerpo {

    private String numeroCuenta;
    private int tipoCuenta;
    private BigDecimal valorRetiro;
    private String documentoCliente;
    private String fechaDeposito;

    @Override
    public String asTexto() {
        return this.numeroCuenta + this.tipoCuenta + this.valorRetiro + this.documentoCliente + this.fechaDeposito;
    }

    @Override
    public boolean validate(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void build(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public int getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(int tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getValorRetiro() {
        return valorRetiro;
    }

    public void setValorRetiro(BigDecimal valorRetiro) {
        this.valorRetiro = valorRetiro;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getFechaDeposito() {
        return fechaDeposito;
    }

    public void setFechaDeposito(String fechaDeposito) {
        this.fechaDeposito = fechaDeposito;
    }

}
