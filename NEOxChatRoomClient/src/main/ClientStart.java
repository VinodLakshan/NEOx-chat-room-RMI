/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import connector.ServerConnector;
import controller.ChatController;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.ChatObserverImpl;
import view.LogIn;
import view.MainWindow;

/**
 *
 * @author NEOx
 */
public class ClientStart {
    public static void main(String[] args) {
        new LogIn().setVisible(true);
    }
}
