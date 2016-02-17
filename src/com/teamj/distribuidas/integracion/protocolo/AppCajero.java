/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamj.distribuidas.integracion.protocolo;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dennys
 */
public class AppCajero {

    private Socket socket;
    //private MensajeRQ messageRQ;
    //private MensajeRS messageRS;
    private PrintWriter output;
    private BufferedReader input;
    public static final String IPADDRESS = "127.0.0.1";
    public static final int PORT = 4001;

    public AppCajero() {
//        socket = new Socket(IPADDRESS, PORT);
//        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public MensajeRS sendRequest(Mensaje mensajeRQ) {

        String response = null;
        int attemps = 0;
        try {
            socket = new Socket(IPADDRESS, PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            do {

                output.write(mensajeRQ.asTexto()+"\n");
                output.flush();
                response = input.readLine();
                if (response != null) {
                    break;
                }
            } while (attemps <= 5);

            output.write("FIN");
            output.flush();
            socket.close();
            if (response != null) {
                if(response.equals(Mensaje.ID_MENSAJE_FALLOBUILD))
                {
                    System.out.println("El mensaje no se pudo construir");
                    return null;
                }
                MensajeRS mensajeRS = new MensajeRS();
                if (mensajeRS.build(response)) {
                    return mensajeRS;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AppCajero.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
