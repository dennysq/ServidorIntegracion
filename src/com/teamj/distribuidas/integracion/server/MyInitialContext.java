/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.server;

import com.teamj.distribuidas.corebancario.services.remote.RemoteInterface;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author LuisAlberto
 */
public class MyInitialContext {
    private static final MyInitialContext instance= new MyInitialContext();
    private InitialContext ctx = null;
    
    private String ip= "127.0.0.1";
    private String port="3700";

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public MyInitialContext() {
        
      
    }
    public void openConection(){
          try {
            //Thread.currentThread().setContextClassLoader(Test.class.getClassLoader());

            Properties prop = new Properties();
            //aqui va la ip donde se encuentra el servidor glassfish que contiene los EJB remotos.
            prop.put("org.omg.CORBA.ORBInitialHost", ip);
            prop.put("org.omg.CORBA.ORBInitialPort", port);
            prop.put("java.naming.factory.initial", "com.sun.enterprise.naming.SerialInitContextFactory");
            prop.put("java.naming.factory.url.pkgs", "com.sun.enterprise.naming");
            prop.put("java.naming.factory.state", "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
            ctx = new InitialContext(prop);
//            RemoteInterface bean = (RemoteInterface) ctx.lookup("java:global/CoreBancario-ear/CoreBancario-ejb-1/RemoteBean!com.teamj.distribuidas.corebancario.services.remote.RemoteInterface");
//            System.out.println("The number of inserted values is: " + bean.addValue("new"));
        } catch (Exception ex) {

            System.out.println("FATAL ERROR: --- ");
            ex.printStackTrace();
        } 
    }
    private void close_context() {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception ex) {   //    
                ex.printStackTrace();
            }
        }
    }

    public InitialContext getCtx() {
        return ctx;
    }
    public static MyInitialContext getInstance(){
        return instance;
    }
}
