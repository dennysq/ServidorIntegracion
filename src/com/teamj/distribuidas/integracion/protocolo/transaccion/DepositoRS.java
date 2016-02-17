/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.transaccion;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;

/**
 *
 * @author LuisAlberto
 */
public class DepositoRS implements Cuerpo{
    private String message;
    
    @Override
    public String asTexto() {
        return this.message;
    }
    @Override
    public boolean validate(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void build(String input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
