/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import observer.ChatObserver;

/**
 *
 * @author NEOx
 */
public interface ChatController extends Remote{
    public void sendMessage(String msg)throws RemoteException;
    public void addRegisteredChatter(ChatObserver chtOb,String name)throws RemoteException;
    public void addTemporaryChatter(ChatObserver chtOb)throws RemoteException;
    public void removeRegisteredChatter(ChatObserver chtOb,String name)throws RemoteException;
    public void removeTemporaryChatter(ChatObserver chtOb)throws RemoteException;
    
    public int registerCustomer(String name,String pass,String email)throws RemoteException;
    public int confirmLogin(String name,String pass)throws RemoteException;
    public int checkUsername(String name)throws RemoteException;
    public void getOnlineList()throws RemoteException;
    public ArrayList<ChatObserver> getAllOnline()throws RemoteException;
    
    public void checkInFriendList(String sendName,String receiverName,ChatObserver chtOb)throws RemoteException;
    public void setPrivateChat(String msg,String sendName,String recieverName)throws RemoteException;
    public void sendRequest(String sendName,String receiverName)throws RemoteException;
    public void confirmRequest(String sendName,String receiverName)throws RemoteException;
    public void cancelRequest(String sendName,String receiverName)throws RemoteException;
    
    public void privateChatHistory(String logoutTime,String loginTime,String sendName,String receiverName,String[] msgs)throws RemoteException;
    public String[] getPrivateChatHistory(String sendName,String receiverName)throws RemoteException;

    public void writeLogs(int registered, String loginTime, String logoutTime, String name)throws RemoteException;
}
