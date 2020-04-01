/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import form.FrmConnect;
import form.FrmLogin;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.util.Operation;

/**
 *
 * @author not-sure
 */
public class EndThread extends Thread{
    private FrmLogin frmLogin;
    private String host;
    private int port;
    private FrmConnect frmConnect;

    public EndThread(FrmLogin frmLogin, String host, int port) {
        this.frmLogin = frmLogin;
        this.host = host;
        this.port = port;
    }

    public EndThread(FrmConnect frmConnect, FrmLogin frmLogin, String host, int port) {
        this.frmLogin = frmLogin;
        this.host = host;
        this.port = port;
        this.frmConnect = frmConnect;
    }
    
    @Override
    public void run() {
        try {
            Socket socket = new Socket(host, port);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Request request = (Request) in.readObject();
                if (request.getOperation() == Operation.OPERATION_SHUTDOWN) {
                    frmLogin.shutDown();
                    frmConnect.setVisible(true);
                }
            }
        } catch (IOException ex) {
            //Logger.getLogger(EndThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EndThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
