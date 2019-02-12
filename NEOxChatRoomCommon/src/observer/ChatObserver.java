/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NEOx
 */
public interface ChatObserver extends Remote{
    public void update(String msg) throws RemoteException;
    public void updateOnlineList(ArrayList<String> onlineList) throws RemoteException;
    public String getWindowTitle()throws RemoteException;

    public void updatePrivateChat(String msg, String sendName, String recieverName)throws RemoteException;
    public void checkFriends(String sendName, String receiverName)throws RemoteException;
    public void updateFriendsList(boolean find, String sendName, String receiverName) throws  RemoteException;
    public void cancelRequest(String sendName, String receiverName)throws RemoteException;

    
}
