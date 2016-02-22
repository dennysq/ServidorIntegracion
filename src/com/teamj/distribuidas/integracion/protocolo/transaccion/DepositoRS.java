/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.transaccion;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import org.apache.commons.lang3.StringUtils;

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
        return input != null && input.length() >= 1 && input.length() <= 2;
    }

    @Override
    public void build(String input) {
        if (validate(input)) {
            if (input.length() < 2) {
                input = StringUtils.rightPad(input, 2);
            }
            this.message = input;
        }
    }

    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    
}
