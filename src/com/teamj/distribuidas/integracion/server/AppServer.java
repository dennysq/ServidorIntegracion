/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.server;


import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dennys
 */
public class AppServer {
    public static void main(String[] args) {
        System.out.println("Servidor de Aplicaciones arriba");
        try{
            ServerSocket server = new ServerSocket(4001);
            while(true){
                Socket client = server.accept();
                new AppSocketSession(client).start();
                System.out.println("El servidor ha recibido una conexion");
            }    
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error: "+e.getMessage());
        }
    }
}
