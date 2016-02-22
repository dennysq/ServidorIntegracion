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
public class RetiroRQ implements Cuerpo {

    private String numeroCuenta;
    private String tipoCuenta;
    private String valorRetiro;
    private String documentoCliente;
    private String fechaRetiro;

    @Override
    public String asTexto() {
        return this.numeroCuenta + this.tipoCuenta + this.valorRetiro + this.documentoCliente + this.fechaRetiro;
    }

    @Override
    public boolean validate(String input) {
        return input.length() == 64;
    }

    @Override
    public void build(String input) {
        if (validate(input)) {
            try {

                String values[] = MyStringUtil.splitByFixedLengths(input, new int[]{11, 5, 10, 15, 14});
                this.numeroCuenta = values[0];
                this.tipoCuenta = values[1];
                this.valorRetiro = values[2];
                this.documentoCliente = values[3];
                this.fechaRetiro = values[4];

            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = StringUtils.rightPad(numeroCuenta, 11);
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = StringUtils.rightPad(tipoCuenta, 5);
    }

    public String getValorRetiro() {
        return valorRetiro;
    }

    public void setValorRetiro(String valorRetiro) {
        this.valorRetiro = StringUtils.rightPad(valorRetiro, 10);
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = StringUtils.rightPad(documentoCliente, 15);
    }

    public String getFechaRetiro() {
        return fechaRetiro;
    }

    public void setFechaRetiro(String fechaRetiro) {
        this.fechaRetiro = StringUtils.rightPad(fechaRetiro, 14);
    }

}
