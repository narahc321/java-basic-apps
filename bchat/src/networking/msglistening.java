/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking;

import bchat.GUI.Screen;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charan
 */
//public class msglistening extends Thread{
    ServerSocket server;
    int port=0000;
    Screen gui;
    
    msglistening(){
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(msglistening.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public msglistening(Screen gui, int port) {
        this.port=port;
        this.gui=gui;
        try {
            server=new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(msglistening.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void run() {
        Socket clientsocket;
        try {
            while((clientsocket=server.accept() )!=null){
                InputStream is=clientsocket.getInputStream();
                BufferedReader b=new BufferedReader(new InputStreamReader((is)));
                String line=b.readLine();
                if(line != null){
                    gui.write(line);
                }
            }
                } catch (IOException ex) {
            Logger.getLogger(msglistening.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
