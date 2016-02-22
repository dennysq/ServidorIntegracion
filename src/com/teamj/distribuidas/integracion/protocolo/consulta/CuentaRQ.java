/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.consulta;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import com.teamj.distribuidas.integracion.util.MyStringUtil;
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
        return input.length() == 13;
    }

    @Override
    public void build(String input) {
        if (validate(input)) {
            try {

                String values[] = MyStringUtil.splitByFixedLengths(input, new int[]{2, 11});
                this.tipoCuenta = values[0];
                this.cuentaCliente = values[1];

            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
    }

    public String getCuentaCliente() {
        return cuentaCliente.trim();
    }

    public void setCuentaCliente(String cuentaCliente) {
        this.cuentaCliente = StringUtils.rightPad(cuentaCliente, 11);
    }

    public String getTipoCuenta() {
        return tipoCuenta.trim();
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = StringUtils.rightPad(tipoCuenta, 2);
    }

}
