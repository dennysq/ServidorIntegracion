/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.test;

import com.teamj.distribuidas.integracion.protocolo.Mensaje;
import com.teamj.distribuidas.integracion.protocolo.MensajeRQ;
import com.teamj.distribuidas.integracion.protocolo.MensajeRS;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRQ;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRS;
import com.teamj.distribuidas.integracion.server.AppClient;

/**
 *
 * @author Dennys
 */
public class Test {

    public static void main(String[] args) {
        AppClient appClient = new AppClient();
        MensajeRQ mensajeRQ = new MensajeRQ("dennys", Mensaje.ID_MENSAJE_AUTENTICACION);
        AutenticacionRQ autenticacionRQ = new AutenticacionRQ();
        autenticacionRQ.setClave("juan");
        autenticacionRQ.setUsuario("juan");
        mensajeRQ.setCuerpo(autenticacionRQ);
        MensajeRS mensajeRS=appClient.sendRequest(mensajeRQ);
        if (mensajeRS != null) {
                AutenticacionRS aers = (AutenticacionRS) mensajeRS.getCuerpo();
                if (aers.getMessage().equals("OK")) {
                    System.out.println("Exito");
                }else if(aers.getMessage().equals("BA")){
                    System.out.println("Fallo");
                }
            }
    }
}
