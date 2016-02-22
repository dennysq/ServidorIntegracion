/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.consulta;

import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import com.teamj.distribuidas.integracion.util.MyStringUtil;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LuisAlberto
 */
public class CuentaRS implements Cuerpo {

    private String message;
    private String tipoCuenta;
    private String saldoActual;
    private String identificacion;
    private String nombre;
    private Cuenta cuenta;
    

    @Override
    public String asTexto() {
        if (this.message != null && this.message.equals("OK")) {
            return this.message + this.identificacion + this.nombre + this.tipoCuenta + this.saldoActual;
        } else {
            return this.message;
        }
    }

    @Override
    public boolean validate(String input) {
        return input.length() >= 1 && input.length() <= 17;
    }

    @Override
    public void build(String input) {
        if (validate(input)) {
            
                if (input.length() < 17) {
                    input = StringUtils.rightPad(input, 17);
                }
            try {    
                String cuentaValues[] = MyStringUtil.splitByFixedLengths(input, new int[]{2, 5, 10});
                this.message= cuentaValues[0];
                this.cuenta = new Cuenta();
                this.cuenta.setTipo(cuentaValues[1]); 
                this.cuenta.setSaldo(new BigDecimal(cuentaValues[2]));
            } catch (Exception ex) {
                Logger.getLogger(CuentaRS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(String saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
}