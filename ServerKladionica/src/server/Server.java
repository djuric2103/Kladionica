/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import controller.Controller;
import form.FrmMain;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thread.ClientThread;

/**
 *
 * @author not-sure
 */
public class Server extends Thread{
    private ServerSocket serverSocket;
    private boolean active = true;
    private final int port;
    private final FrmMain frmMain;
    private final List<ClientThread> clientThreads;

    public Server(FrmMain frmMain, int port) {
        this.frmMain = frmMain;
        this.port = port;
        clientThreads = new LinkedList<>();
    }
    
    public void createSocket() throws Exception{
        try {     
            serverSocket = new ServerSocket(9000);
            frmMain.showStartupMessage();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Port is already taken");
            throw ex;
        }
    }
    
    @Override
    public void run(){
       while(active){
            try {
                Socket socket =  serverSocket.accept();
                System.out.println("Accepted");
                ClientThread client = new ClientThread(socket);
                client.start();
                clientThreads.add(client);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                frmMain.showShutDownMessage();
            }
           
       }
    }

    public void stopServer() {
        try {
            active = false;
            serverSocket.close();
            clientThreads.forEach(Thread::interrupt);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendShutDownMessage() {
        for (ClientThread clientThread : clientThreads) {
            clientThread.sendShutDownMessage();
        }
    }
    
}
