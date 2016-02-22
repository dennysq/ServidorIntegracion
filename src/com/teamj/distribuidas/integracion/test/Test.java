/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.test;

import com.teamj.distribuidas.integracion.protocolo.Mensaje;
import com.teamj.distribuidas.integracion.protocolo.MensajeRQ;
import com.teamj.distribuidas.integracion.protocolo.MensajeRS;
import com.teamj.distribuidas.integracion.protocolo.consulta.CuentaRQ;
import com.teamj.distribuidas.integracion.protocolo.consulta.CuentaRS;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRQ;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRS;
import com.teamj.distribuidas.integracion.protocolo.transaccion.RetiroRQ;
import com.teamj.distribuidas.integracion.protocolo.transaccion.RetiroRS;
import com.teamj.distribuidas.integracion.server.AppClient;

/**
 *
 * @author Dennys
 */
public class Test {

    public static void main(String[] args) {
//        AppClient appClient = new AppClient();
//        MensajeRQ mensajeRQ = new MensajeRQ("dennys", Mensaje.ID_MENSAJE_AUTENTICACION);
//        AutenticacionRQ autenticacionRQ = new AutenticacionRQ();
//        autenticacionRQ.setClave("juan");
//        autenticacionRQ.setUsuario("juan");
//        mensajeRQ.setCuerpo(autenticacionRQ);
//        MensajeRS mensajeRS=appClient.sendRequest(mensajeRQ);
//        if (mensajeRS != null) {
//                AutenticacionRS aers = (AutenticacionRS) mensajeRS.getCuerpo();
//                if (aers.getMessage().equals("OK")) {
//                    System.out.println("Exito");
//                }else if(aers.getMessage().equals("BA")){
//                    System.out.println("Fallo");
//                }
//            }

        AppClient appClient = new AppClient();
        //appClient.setIp("192.168.1.115");
        CuentaRQ cueRQ = new CuentaRQ();
        cueRQ.setCuentaCliente("1212");
        cueRQ.setTipoCuenta("AH");

        MensajeRQ mensajeRQ = new MensajeRQ("CONSULTACU", Mensaje.ID_MENSAJE_CONSULTACUENTA);
        mensajeRQ.setCuerpo(cueRQ);
        MensajeRS mensajeRS = appClient.sendRequest(mensajeRQ);
        CuentaRS cueRS = (CuentaRS) mensajeRS.getCuerpo();
        if (cueRS.getMessage().equals("OK")) {
            System.out.println("" + cueRS.getCuenta());

        }

        RetiroRQ retiroRQ = new RetiroRQ();
        retiroRQ.setDocumentoCliente("0604133546");
        retiroRQ.setFechaRetiro("22022016213023");
        retiroRQ.setNumeroCuenta("1212");
        retiroRQ.setTipoCuenta("AH");
        retiroRQ.setValorRetiro("10.00");

        MensajeRQ retiroMensajeRQ = new MensajeRQ("dennys", Mensaje.ID_MENSAJE_RETIRO);
        retiroMensajeRQ.setCuerpo(retiroRQ);
        mensajeRS = appClient.sendRequest(retiroMensajeRQ);
        RetiroRS retiroRS = (RetiroRS) mensajeRS.getCuerpo();
        if (retiroRS.getMessage().equals("OK")) {
            System.out.println("" + retiroRS.asTexto());
        }
    }

}
