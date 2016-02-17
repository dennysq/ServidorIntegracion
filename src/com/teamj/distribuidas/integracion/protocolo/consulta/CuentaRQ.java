/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.consulta;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LuisAlberto
 */
public class CuentaRQ implements Cuerpo {

    private String cuentaCliente;
    private String tipoCuenta;

    @Override
    public String asTexto() {
        return this.tipoCuenta + this.cuentaCliente;
    }

    @Override
    public boolean validate(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void build(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCuentaCliente() {
        return cuentaCliente;
    }

    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = StringUtils.rightPad(cuentaCliente, 11);
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = StringUtils.rightPad(tipoCuenta, 4);
    }

}
