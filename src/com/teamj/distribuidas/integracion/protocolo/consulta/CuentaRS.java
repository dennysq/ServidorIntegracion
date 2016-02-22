/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.consulta;

import com.teamj.distribuidas.corebancario.model.Cliente;
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
    private String numeroCuenta;
    private String identificacion;
    private String nombre;
    private Cuenta cuenta;

    @Override
    public String asTexto() {
        if (this.message != null && this.message.equals("OK")) {
            return this.message + this.tipoCuenta + this.numeroCuenta + this.saldoActual + this.identificacion + this.nombre;
        } else {
            return this.message;
        }
    }

    @Override
    public boolean validate(String input) {
        return input.length() >= 2 && input.length() <= 167;
    }

    @Override
    public void build(String input) {
        if (validate(input)) {
            if (input.length() > 2) {
                try {
                    String cuentaValues[] = MyStringUtil.splitByFixedLengths(input, new int[]{2, 2, 11, 10, 15, 128});
                    this.message = cuentaValues[0];
                    this.tipoCuenta = cuentaValues[1];
                    this.numeroCuenta = cuentaValues[2];
                    this.saldoActual = cuentaValues[3];
                    this.identificacion = cuentaValues[4];
                    this.nombre = cuentaValues[5];

                    this.cuenta = new Cuenta();
                    this.cuenta.setNumero(numeroCuenta);
                    this.cuenta.setSaldo(new BigDecimal(saldoActual));
                    this.cuenta.setTipo(tipoCuenta);
                    Cliente cliente = new Cliente();
                    cliente.setNombre(nombre);
                    cliente.setIdentificacion(identificacion);
                    this.cuenta.setCliente(cliente);
                } catch (Exception ex) {
                    Logger.getLogger(CuentaRS.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                this.message = input;
            }
        }
    }

    public String getMessage() {
        return message.trim();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTipoCuenta() {
        return tipoCuenta.trim();
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getSaldoActual() {
        return saldoActual.trim();
    }

    public void setSaldoActual(String saldoActual) {
        this.saldoActual = StringUtils.leftPad(saldoActual, 10, "0");
    }

    public String getIdentificacion() {
        return identificacion.trim();
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = StringUtils.leftPad(identificacion, 15, " ");
    }

    public String getNombre() {
        return nombre.trim();
    }

    public void setNombre(String nombre) {
        this.nombre = StringUtils.leftPad(nombre, 128, " ");
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = StringUtils.leftPad(numeroCuenta, 11, " ");
    }

    public String getNumeroCuenta() {
        return numeroCuenta.trim();
    }

}
