/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo.seguridad;

import com.teamj.distribuidas.integracion.protocolo.Cuerpo;
import com.teamj.distribuidas.integracion.util.MyStringUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author LuisAlberto
 */
public class AutenticacionRQ implements Cuerpo{
    private String usuario;
    private String clave;

    @Override
    public String asTexto() {
        return this.usuario+this.clave;        
    }
    @Override
    public void build(String input) {
        if (validate(input)) {
            try {

                String values[] = MyStringUtil.splitByFixedLengths(input, new int[]{10, 10});
                this.usuario = values[0];
                this.clave = values[1];

            } catch (Exception e) {
                System.out.println("" + e);
            }
        }

    }
    @Override
    public boolean validate(String input) {
        return input.length() == 20;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = StringUtils.rightPad(usuario, 10);
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = StringUtils.rightPad(clave, 10);
    }

    
    
}
