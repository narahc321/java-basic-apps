/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charan
 */
public class MsgTransmitter extends Thread{
    String msg,hostname;
    int port;

    public MsgTransmitter() {
    }

    public MsgTransmitter(String msg, String hostname, int port) {
        this.msg = msg;
        this.hostname = hostname;
        this.port = port;
    }
    @Override
    public void run(){
        try {
            Socket s=new Socket(hostname, port);
            s.getOutputStream().write(msg.getBytes());
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MsgTransmitter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
