/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.servicio;

import com.teamj.distribuidas.corebancario.model.Cuenta;
import com.teamj.distribuidas.corebancario.model.Empleado;
import com.teamj.distribuidas.corebancario.services.remote.EmpleadoServicioInterface;
import com.teamj.distribuidas.corebancario.services.remote.CuentaServicioInterface;
import com.teamj.distribuidas.integracion.server.MyInitialContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Dennys
 */
public class AppFacade {

    public static boolean getAuthentication(String user, String password) {
        MyInitialContext ctx = new MyInitialContext();
        ctx.setIp("192.168.1.115");
        ctx.openConection();

        EmpleadoServicioInterface bean;
        try {
            bean = (EmpleadoServicioInterface) ctx.getCtx().lookup("java:global/CoreBancario-ear/CoreBancario-ejb-1/EmpleadoServicioRemote!com.teamj.distribuidas.corebancario.services.remote.EmpleadoServicioInterface");
            return bean.login(user, password);
        } catch (NamingException ex) {
            Logger.getLogger(AppFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ctx.close_context();
        }
        return false;
    }
    
    public static Cuenta obtenerCuenta(String cuenta, String tipoCuenta){
        MyInitialContext ctx = new MyInitialContext();
        ctx.setIp("192.168.1.115");
        ctx.openConection();
        CuentaServicioInterface bean;
        try {
            bean= (CuentaServicioInterface) ctx.getCtx().lookup("java:global/CoreBancario-ear/CoreBancario-ejb-1/CuentaServicioRemote!com.teamj.distribuidas.corebancario.services.remote.CuentaServicioInterface");
            return bean.obtenerCuenta(cuenta, tipoCuenta);
        } catch (NamingException ex) {
            Logger.getLogger(AppFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            ctx.close_context();
        }
        return null;
    }

//    public static Boolean insernewclient(String id, String nombre, String telefono, String direccion)
//    {
//        DBClient dbclient =new DBClient();
//        MensajeRQ msj = new MensajeRQ("appserver",MensajeRQ.ID_MENSAJE_INGRESOCLIENTE);
//        IngresoClienteRQ ing =new IngresoClienteRQ();
//        ing.setCliente(new Cliente(id,nombre,telefono,direccion));
//        msj.setCuerpo(ing);
//        MensajeRS response = dbclient.sendRequest(msj);
//        IngresoClienteRS ingrs = (IngresoClienteRS) response.getCuerpo();
//        if (ingrs.getResultado().equals("1")) {
//            return true;
//        } else {
//            return false;
//        }
//    }//ing.getIdentificacion(),ing.getNombre(),ing.getDireccion(),ing.getTelefono(),ing.getDetalles()
//
//    public static String insertarNuevaFactura(String id_facura, String identificacion, String fecha, String total, String numeroDetalles, List<DetalleFacturaAppRQ> detalles) {
//        DBClient dbclient = new DBClient();
//        MensajeRQ msj = new MensajeRQ(NetUtil.getLocalIPAddress(), MensajeRQ.ID_MENSAJE_INGRESOFACTURA);
//        IngresoFacturaRQ ing = new IngresoFacturaRQ();
//        ing.setIdentificacionCliente(identificacion);
//        ing.setFecha(fecha);
//        ing.setTotal(total);
//        ing.setIdFactura(id_facura);
//        ing.setNumeroDetalles(numeroDetalles);
//
//        Detalle detalle = null;
//        List<Detalle> details = new ArrayList<>();
//        for (int i = 0; i < detalles.size(); i++) {
//            detalle = new Detalle();
//            detalle.setIdFactura(id_facura);
//            detalle.setCantidad(detalles.get(i).getCantidad());
//            detalle.setIdProducto(detalles.get(i).getIdProducto());
//            Producto p = getProducto(detalles.get(i).getIdProducto());
//            if (p == null) {
//                detalle.setNombreProducto("quemado");//tengo que esperar el medtodo para buscar el numero de ese producto cn esa celda
//            } else {
//                if (Integer.valueOf(p.getCantidad().trim()) < Integer.valueOf(detalles.get(i).getCantidad())) {
//                    return "2";//el numero de productos que se desea facturar es mayor al stock
//                }
//                detalle.setNombreProducto(p.getNombre());//tengo que esperar el medtodo para buscar el numero de ese producto cn esa celda
//            }
//
//            details.add(detalle);
//        }
//
//        ing.setDetalles(details);
//        ing.setTotal(total);
//        ing.setNumeroDetalles(String.valueOf(details.size()));
//        
//        msj.setCuerpo(ing);
//        MensajeRS response = dbclient.sendRequest(msj);
//        IngresoFacturaRS ingrs = (IngresoFacturaRS) response.getCuerpo();
//        if (ingrs != null) {
//            return ingrs.getResultado();
//        } else {
//            return "5";//no se construyo el mensaje correctamente
//        }
//    }
//
//    public static Cliente consultaCliente(String datos) {
//        DBClient dbClient = new DBClient();
//        MensajeRQ msj = new MensajeRQ("appserver", MensajeRQ.ID_MENSAJE_CONSULTACLIENTE);
//        ConsultaClienteRQ con = new ConsultaClienteRQ();
//        con.setIdentificacion(datos);
//        msj.setCuerpo(con);
//
//        MensajeRS response = dbClient.sendRequest(msj);
//        ConsultaClienteRS cli = (ConsultaClienteRS) response.getCuerpo();
//        if (cli.getResultado().equals("1")) {
//            return cli.getCliente();
//        }
//        return null;
//    }
//
//    public static Producto getProducto(String idProducto) {
//        DBClient dbClient = new DBClient();
//        MensajeRQ msj = new MensajeRQ("appserver", MensajeRQ.ID_MENSAJE_CONSULTAPRODUCTO);
//        ConsultaProductoRQ cprq = new ConsultaProductoRQ();
//        cprq.setIdProducto(idProducto);
//        msj.setCuerpo(cprq);
//
//        MensajeRS response = dbClient.sendRequest(msj);
//
//        ConsultaProductoRS cprs = (ConsultaProductoRS) response.getCuerpo();
//        if (cprs.getResultado().equals("1")) {
//            return cprs.getProducto();
//        }
//        return null;
//    }
//    
//    public static Factura consultarFactura(String idFactura) {
//        DBClient dbClient = new DBClient();
//        MensajeRQ msj = new MensajeRQ("appserver", MensajeRQ.ID_MENSAJE_CONSULTAFACTURA);
//        ConsultaFacturaRQ con = new ConsultaFacturaRQ();
//        con.setIdFactura(idFactura);
//        msj.setCuerpo(con);
//
//        MensajeRS response = dbClient.sendRequest(msj);
//        ConsultaFacturaRS fac = (ConsultaFacturaRS) response.getCuerpo();
//        if (fac.getResultado().equals("1")) {
//            return fac.getFactura();
//        }
//        return null;
//    }
}
