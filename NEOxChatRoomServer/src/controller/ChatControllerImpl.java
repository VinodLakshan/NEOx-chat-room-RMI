/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.xml.internal.bind.v2.schemagen.Util;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import observer.ChatObserver;
import observerble.ChatObserverble;

/**
 *
 * @author NEOx
 */
public class ChatControllerImpl extends UnicastRemoteObject implements ChatController{
    private ChatObserverble chtObsvrble=new ChatObserverble();
    
    public ChatControllerImpl() throws RemoteException{
    
    }
    
    @Override
    public void sendMessage(String msg) throws RemoteException {
        chtObsvrble.notifyMessage(msg);
    }

    @Override
    public void addTemporaryChatter(ChatObserver chtOb) throws RemoteException {
        chtObsvrble.addTemporaryChatter(chtOb);
    }

    @Override
    public void removeTemporaryChatter(ChatObserver chtOb) throws RemoteException {
       chtObsvrble.removeTemporaryChatter(chtOb);
    }

    @Override
    public int registerCustomer(String name,String pass,String email) throws RemoteException {
       return chtObsvrble.registerCustomer(name, pass, email);
    }

    @Override
    public int confirmLogin(String name, String pass)throws RemoteException{
        return chtObsvrble.confirmLogin(name, pass);
    }

    @Override
    public int checkUsername(String name) throws RemoteException {
        return chtObsvrble.checkUserName(name);
    }

    @Override
    public void addRegisteredChatter(ChatObserver chtOb, String name) throws RemoteException {
        chtObsvrble.addRegisteredChatter(chtOb, name);
    }

    @Override
    public void removeRegisteredChatter(ChatObserver chtOb, String name) throws RemoteException {
        chtObsvrble.removeRegisteredChatter(chtOb, name);
    }

    @Override
    public void getOnlineList() throws RemoteException {
        chtObsvrble.getOnlineList();
    }

    @Override
    public ArrayList<ChatObserver> getAllOnline()throws RemoteException{
        return chtObsvrble.getAllOnline();
    }

    @Override
    public void setPrivateChat(String msg, String sendName, String recieverName) throws RemoteException {
        chtObsvrble.setPrivateChat(msg,sendName,recieverName);
    }

    @Override
    public void checkInFriendList(String sendName, String receiverName, ChatObserver chtOb) throws RemoteException {
        chtObsvrble.checkInFriendList(sendName, receiverName, chtOb);
    }

    @Override
    public void sendRequest(String sendName, String receiverName)throws RemoteException{
        chtObsvrble.sendRequest(sendName, receiverName);
    }

    @Override
    public void confirmRequest(String sendName, String receiverName) throws RemoteException{
        chtObsvrble.confirmRequest(sendName,receiverName);
    }

    @Override
    public void cancelRequest(String sendName,String receiverName) throws RemoteException {
        chtObsvrble.cancelRequest(sendName,receiverName);
    }

    @Override
    public void privateChatHistory(String logoutTime, String loginTime, String sendName, String receiverName, String[] msgs) throws RemoteException{
        chtObsvrble.privateChatHistory(logoutTime,loginTime,sendName,receiverName,msgs);
    }

    @Override
    public String[] getPrivateChatHistory(String sendName, String receiverName) throws RemoteException {
        return chtObsvrble.getPrivateChatHistory(sendName, receiverName);
    }

    @Override
    public void writeLogs(int registered, String loginTime, String logoutTime, String name) throws RemoteException {
        chtObsvrble.writeLogs(registered,loginTime,logoutTime,name);
    }
}
