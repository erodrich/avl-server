/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erodrich.server.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ThreadedEchoServer {

    final static Logger logger = Logger.getLogger(EchoThread.class.getSimpleName());
    private static final String NOMBRE_CLASE = ThreadedEchoServer.class.getSimpleName();
//    private static final String PROPERTIES_FILE_PATH = "./propiedades.properties";
//    private static final String LOG_PLANTILLA_PATH = "path-plantilla-log";
//    private static final String PORT = "3113";
    private ServerSocket serverSocket = null;
    private List<Thread> clients;
    //private static final String PORT = "PORT";
    private String propertiesFile;
    public static ThreadedEchoServer container = new ThreadedEchoServer();
    //public static final Map<String, String> PROPERTIES = new ConcurrentHashMap<String, String>();
    public static final Properties properties = new Properties();

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {

        container.init(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                String metodo = "run";
                try {
                    container.shutDown();
                } catch (Exception ex) {
//                    log.error(ex.getMessage(), ex);

                }
            }
        });
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public void init(String... args) throws IOException {
        String metodo = "init";
        Socket socket = null;
        int port;
//        if (args == null || args.length <= 1 || args[1] == null) {
//            propertiesFile = PROPERTIES_FILE_PATH;
//        } else {
//            propertiesFile = args[1];
//        }
//        try {
//            properties.load(new FileInputStream(propertiesFile));
//            System.out.println(NOMBRE_CLASE + " ::: " + metodo + " Cargando archivo propiedades desde: " + propertiesFile);
//            System.out.println(NOMBRE_CLASE + " ::: " + metodo + " Archivo: " + propertiesFile + " cargado.");
//
//            // Inicializando log
//            String pathToLog = properties.getProperty(LOG_PLANTILLA_PATH);
//            System.out.println(NOMBRE_CLASE + " ::: " + metodo + " Inicializando log desde : " + pathToLog);
//
//            ByteLog.initDefaultLogger(pathToLog, "log.xml");
//            setLogger(ByteLog.getLogger(""));
//            printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, " Log configurado y escribiendo");
//
//            printLog(LogLevel.INFO, NOMBRE_CLASE, metodo, " ****** ::: Fin de inicializacion  :::::**********");
//        } catch (Exception ex) {
//            printLog(LogLevel.DEBUG, NOMBRE_CLASE, metodo, " Error al leer propiedades: " + ex.getMessage());
//        }
        port = Integer.parseInt(args[0]);
        serverSocket = new ServerSocket(port);
        clients = new ArrayList<Thread>();
        while (!serverSocket.isClosed()) {
            try {
                socket = serverSocket.accept();
                logger.debug(" *** Server is UP!");
            } catch (IOException e) {
                //log.error(e.getMessage(), e);
//                printLog(LogLevel.FATAL, NOMBRE_CLASE, metodo, " Error:  " + e.getMessage());
                logger.error(" *** Server ERROR on start up! :: ");
                e.printStackTrace();
            }
            Thread thread = new EchoThread(socket);
            logger.debug(" *** Starting thread!");
            thread.start();
            clients.add(thread);
        }
    }

    public void shutDown() {
        Iterator iterator = clients.iterator();
        while (iterator.hasNext()) {
            Thread thread = (Thread) iterator.next();
            thread.interrupt();
            iterator.remove();
        }
    }
    /*
     private void loadProperties() {
     String metodo = "loadProperties";
     InputStream inputStream;
     Properties properties;
     try {
     properties = new Properties();
     inputStream = new FileInputStream(propertiesFile);
     properties.load(inputStream);
     for (String key : properties.stringPropertyNames()) {
     ThreadedEchoServer.PROPERTIES.put(key, properties.getProperty(key));
     }
     } catch (Exception ex) {
     //log.error(ex.getMessage(), ex);
     printLog(LogLevel.FATAL, NOMBRE_CLASE, metodo, " Error:  " + ex.getMessage());

     }
     }
     */

}
