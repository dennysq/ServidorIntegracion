/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.server;

/**
 *
 * @author RAUL
 */
import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.integracion.protocolo.Mensaje;
import com.teamj.distribuidas.integracion.protocolo.MensajeRQ;
import com.teamj.distribuidas.integracion.protocolo.MensajeRS;
import com.teamj.distribuidas.integracion.protocolo.consulta.CuentaRQ;
import com.teamj.distribuidas.integracion.protocolo.consulta.CuentaRS;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRQ;
import com.teamj.distribuidas.integracion.protocolo.seguridad.AutenticacionRS;
import com.teamj.distribuidas.integracion.protocolo.transaccion.DepositoRQ;
import com.teamj.distribuidas.integracion.protocolo.transaccion.DepositoRS;
import com.teamj.distribuidas.integracion.servicio.AppFacade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AppSocketSession extends Thread {

    private static Integer global = 0;
    private PrintWriter output;
    private BufferedReader input;
    private Socket socket;

    private Integer id;

    public AppSocketSession(Socket socket) throws IOException {

        this.id = AppSocketSession.global++;
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {

            String userInput;

            while ((userInput = input.readLine()) != null) {

                if ("FIN".equalsIgnoreCase(userInput)) {
                    break;
                }
                System.out.println("Hilo: " + this.id + " Mensaje recibido: " + userInput);
                MensajeRQ msj = new MensajeRQ();
                if (msj.build(userInput)) {
                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_AUTENTICACION)) {
                        AutenticacionRQ aut = (AutenticacionRQ) msj.getCuerpo();
                        boolean response = AppFacade.getAuthentication(aut.getUsuario(), aut.getClave());

                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_AUTENTICACION);
                        AutenticacionRS autRS = new AutenticacionRS();
                        if (response) {
                            autRS.setMessage("OK");
                            
                        } else {
                            autRS.setMessage("BA");
                        }

                        mensajeRS.setCuerpo(autRS);
                        output.write(mensajeRS.asTexto() + "\n");
                        output.flush();
                    }
                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_CONSULTACUENTA)) {
                        CuentaRQ cue = (CuentaRQ) msj.getCuerpo();
                        Cuenta response = AppFacade.obtenerCuenta(cue.getCuentaCliente(), cue.getTipoCuenta());

                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_CONSULTACUENTA);
                        CuentaRS cueRS = new CuentaRS();
                        cueRS.setCuenta(response);
                        
                        mensajeRS.setCuerpo(cueRS);
                        output.write(mensajeRS.asTexto() + "\n");
                        output.flush();
                    }
                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_DEPOSITO)) {
                        DepositoRQ dep = (DepositoRQ) msj.getCuerpo();
                        boolean response = AppFacade.registrarDeposito(dep.getNumeroCuenta(), dep.getTipoCuenta(), dep.getValorDeposito(), dep.getFechaDeposito());

                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_DEPOSITO);
                        DepositoRS autRS = new DepositoRS();
                        if (response) {
                            autRS.setMessage("OK");
                            
                        } else {
                            autRS.setMessage("BA");
                        }

                        mensajeRS.setCuerpo(autRS);
                        output.write(mensajeRS.asTexto() + "\n");
                        output.flush();
                    }
                }
                else {
                    output.write(Mensaje.ID_MENSAJE_FALLOBUILD + "\n");
                    output.flush();
                }

            }
            socket.close();
            
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        try {
//
//            String userInput;
//
//            while ((userInput = input.readLine()) != null) {
//
//                if ("FIN".equalsIgnoreCase(userInput)) {
//                    break;
//                }
//                System.out.println("Hilo: " + this.id + " Mensaje recibido: " + userInput);
//                MensajeRQ msj = new MensajeRQ();
//                if (msj.build(userInput)) {
//                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_AUTENTICACIONCLIENTE)) {
//
//                        //metodo de autenticacion
//                        AutenticacionEmpresaRQ aut = (AutenticacionEmpresaRQ) msj.getCuerpo();
//                        Empresa response = AppFacade.getAuthenticationEmpresa(aut.getUserId(), aut.getPassword());
//
//                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_AUTENTICACIONCLIENTE);
//                        AutenticacionEmpresaRS autRS = new AutenticacionEmpresaRS();
//                        if (response != null) {
//                            autRS.setResultado("1");
//                            autRS.setEmpresa(response);
//                        } else {
//                            autRS.setResultado("2");
//                        }
//
//                        mensajeRS.setCuerpo(autRS);
//                        output.write(mensajeRS.asTexto() + "\n");
//                        output.flush();
//                    }
//                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_INGRESOCLIENTE)) {
//                        IngresoClienteRQ ing = (IngresoClienteRQ) msj.getCuerpo();
//                
//                        Boolean ingresocorrecto = AppFacade.insernewclient(ing.getCliente().getIdentificacion(), ing.getCliente().getNombre(), ing.getCliente().getTelefono(), ing.getCliente().getDireccion());
//                       // System.out.print("******"+ing.getCliente().getIdentificacion());
//                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_INGRESOCLIENTE);
//                        IngresoClienteRS ingrs = new IngresoClienteRS();
//                        if (ingresocorrecto) {
//                            ingrs.setResultado("1");
//                        } else {
//                            ingrs.setResultado("2");
//                        }
//                        mensajeRS.setCuerpo(ingrs);
//                        output.write(mensajeRS.asTexto() + "\n");
//                        output.flush();
//                    }
//                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_CONSULTACLIENTE)) {
//                        ConsultaClienteRQ ing = (ConsultaClienteRQ) msj.getCuerpo();
//                        Cliente response = AppFacade.consultaCliente(ing.getIdentificacion());
//                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_CONSULTACLIENTE);
//                        ConsultaClienteRS cli = new ConsultaClienteRS();
//                        if (response != null) {
//                            cli.setResultado("1");
//                            cli.setCliente(response);
//                        } else {
//                            cli.setResultado("2");
//                        }
//                        mensajeRS.setCuerpo(cli);
//                        output.write(mensajeRS.asTexto() + "\n");
//                        output.flush();
//                    }
//                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_CONSULTAPRODUCTO)) {
//
//                        //metodo de consulta producto
//                        ConsultaProductoRQ cprq = (ConsultaProductoRQ) msj.getCuerpo();
//                        Producto response = AppFacade.getProducto(cprq.getIdProducto());
//
//                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_CONSULTAPRODUCTO);
//                        ConsultaProductoRS cprs = new ConsultaProductoRS();
//                        if (response != null) {
//                            cprs.setResultado("1");
//                            cprs.setProducto(response);
//                        } else {
//                            cprs.setResultado("2");
//                        }
//
//                        mensajeRS.setCuerpo(cprs);
//                        output.write(mensajeRS.asTexto() + "\n");
//                        output.flush();
//                    }
//                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_INGRESOFACTURA)) {                        
//                        IngresoFacturaRQ ing = (IngresoFacturaRQ) msj.getCuerpo();
//                        String ingreso = AppFacade.insertarNuevaFactura(ing.getIdFactura(), ing.getIdentificacion(), ing.getFecha(), ing.getTotal(), ing.getNumeroDetalles(), ing.getDetalles());
//                        MensajeRS mensajeRS = new MensajeRS(NetUtil.getLocalIPAddress(), Mensaje.ID_MENSAJE_INGRESOFACTURA);
//                        IngresoFacturaRS ingrs = new IngresoFacturaRS();
//                        ingrs.setResultado(ingreso);
//                        mensajeRS.setCuerpo(ingrs);
//                        output.write(mensajeRS.asTexto() + "\n");
//                        output.flush();
//                    }
//                    
//                    if (msj.getCabecera().getIdMensaje().equals(Mensaje.ID_MENSAJE_CONSULTAFACTURA)) {
//
//                        //metodo de consulta producto
//                        ConsultaFacturaRQ cfrq = (ConsultaFacturaRQ) msj.getCuerpo();
//                        Factura response = AppFacade.consultarFactura(cfrq.getIdFactura());
//
//                        MensajeRS mensajeRS = new MensajeRS("appserver", Mensaje.ID_MENSAJE_CONSULTAFACTURA);
//                        ConsultaFacturaRS cprs = new ConsultaFacturaRS();
//                        if (response != null) {
//                            cprs.setResultado("1");
//                            cprs.setFactura(response);
//                        } else {
//                            cprs.setResultado("2");
//                        }
//                        mensajeRS.setCuerpo(cprs);
//                        output.write(mensajeRS.asTexto() + "\n");
//                        output.flush();
//                    }
//                    
//                } else {
//                    output.write(Mensaje.ID_MENSAJE_FALLOBUILD + "\n");
//                    output.flush();
//                }
//
//            }
//            socket.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Error: " + e);
//        }
    }

}
