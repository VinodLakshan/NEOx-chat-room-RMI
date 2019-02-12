/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connector;

import controller.ChatController;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author NEOx
 */
public class ServerConnector {
    private ChatController chatController;
    private static ServerConnector svrConnector;

    private ServerConnector() throws NotBoundException, MalformedURLException, RemoteException {
        chatController=(ChatController) Naming.lookup("rmi://localhost:1616/NEOxServer");
    }
    
    public ChatController getChatController(){
        return  chatController;
    }
    
    public static ServerConnector getServerConnector() throws NotBoundException, MalformedURLException, RemoteException{
        if (svrConnector==null) {
            svrConnector=new ServerConnector();
        }
        return svrConnector;
    }
    
}
