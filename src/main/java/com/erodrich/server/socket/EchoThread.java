/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erodrich.server.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import org.apache.log4j.Logger;

public class EchoThread extends Thread {

    final static Logger logger = Logger.getLogger(EchoThread.class.getSimpleName());
    private static final String NOMBRE_CLASE = EchoThread.class.getSimpleName();
    public static final String RESPONSE = "response";
    protected Socket socket;

    public EchoThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        try {
            String metodo = "run";
            InputStream inp;
            BufferedReader brinp;
            DataOutputStream out;
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            out = new DataOutputStream(socket.getOutputStream());
            String command;
            while (true) {
                command = brinp.readLine();
                if (command == null) {
                    if (!socket.isClosed()) {
                        socket.close();
                    }
                    break;
                } else {
                    logger.debug(" *** InputStream [ " + command + " ] ");
                    logger.debug(" *** InputStream [ " + command.trim().length() + " ] ");
                    out.write(0x01);
                    if(command.trim().length() == 15){
                        out.writeByte(0x1);
                        
                    }

                }
//                        if (command.contains("disconnect()")) {
//                        logger.debug(LogLevel.INFO, NOMBRE_CLASE, metodo, "Realizando logout.");
//                        logger.debug(LogLevel.INFO, NOMBRE_CLASE, metodo, "Logout realizado correctamente");
//                        //log.debug("Realizando logout.");
//                        //log.debug("Logout realizado correctamente");
//                        out.writeBytes("Logout correctamente.");
//                        out.flush();
//                        socket.close();
//                        break;
//                    } else if (command.contains("connect (version 1.0, idletimeout 10, txnmode single)")) {
//                        printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, "Realizando Login.");
//                        printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, "Login realizado correctamente");
//                        //log.debug("Realizando Login.");
//                        //log.debug("Login realizado correctamente");
//                        out.writeBytes("rc 0");
//                    } else if (command.contains("OK")) {
//                        printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, "Mensaje recibido: " + command);
//                        //log.debug("Mensaje recibido: " + command);
//                        out.writeBytes("rsp (rc 0)");
//                    } else if (!command.isEmpty()) {
//                        try {
//                            printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, "Mensaje recibido: " + command);
//                            //log.debug("Mensaje recibido: " + command);
//
//                            // dlt_sub (dn 5116064452,  timeout 50)
//                            //respuesta
//                            //String response = "rsp (rc 0, data (dblevel 329716977))\u0000";
//                            String response = ThreadedEchoServer.properties.getProperty(RESPONSE);
//                            printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, "Respuesta: " + response);
//                            //log.debug("Respuesta: " + response);
//                            out.writeBytes(response);
//                            /*
//                             JSONObject request = new JSONObject(command);
//                             JSONArray identifiers = request.getJSONArray("identificador");
//                             JSONObject response = new JSONObject();
//
//                             response.put("estado", "OK");
//                             response.put("codigo-respuesta", "0");
//                             response.put("descripcion-respuesta", "Respuesta OK");
//                             response.put("fecha-envio", "2018-08-03 23:53:50.511");
//                             response.put("fecha-recepcion", "2018-08-03 23:54:10.611");
//                             response.put("fecha-respuesta", "2018-08-03 23:55:10.811");
//
//                             JSONArray detalle = new JSONArray();
//                             for (int i = 0; i < identifiers.length(); i++) {
//                             JSONObject jsonDetalle = new JSONObject();
//                             JSONArray caracteristicas = new JSONArray(ThreadedEchoServer.PROPERTIES.get(LIST_BOLTONS));
//                             jsonDetalle.put("identificador", identifiers.getString(i));
//                             jsonDetalle.put("caracteristicas", caracteristicas);
//                             detalle.put(jsonDetalle);
//                             }
//                             response.put("detalle", detalle);
//                             log.info(" *** response: [" + response + "]");
//                             out.writeBytes(response.toString());
//                             */
//                        } catch (Exception ex) {
//                            printLog(LogLevel.FATAL, NOMBRE_CLASE, metodo, "Error: " + ex.getMessage());
//                            //log.error(ex.getMessage(), ex);
//                            out.writeBytes("Wrong command");
//                        }
            }
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(EchoThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//                if (!socket.isClosed()) {
//                    out.writeBytes("\n");
//                    out.flush();
//                }

}
