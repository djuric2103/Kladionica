/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transfer.Request;
import transfer.Response;

/**
 *
 * @author not-sure
 */
public class Communication {
    private static Communication instance;
    private Socket socket;
    
    private Communication(){
        
    }
    
    public static Communication getInstance(){
        return instance = instance == null ? new Communication() : instance;
    }
    
    public void sendRequest(Request request) throws IOException{
        ObjectOutputStream outSocket = null;
        try {
            outSocket = new ObjectOutputStream(socket.getOutputStream());
            outSocket.writeObject(request);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server leave the session");
            System.exit(0);
        }
    }
    
    public Response readResponse() throws IOException, ClassNotFoundException{
        ObjectInputStream inSocket = null;
        Response response = null;
        try {
            inSocket = new ObjectInputStream(socket.getInputStream());
            response = (Response) inSocket.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Server leave the session");
            System.exit(0);
        }
        return response;
    }
    
    public void connect(String host, int port)throws IOException{
        socket = new Socket(host, port);
    }
}
