/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import view.LogIn;
import view.MainWindow;


/**
 *
 * @author NEOx
 */
public class ChatObserverImpl extends UnicastRemoteObject implements ChatObserver{
    private MainWindow mainWindow;
    
    public  ChatObserverImpl(MainWindow mainWindow)throws RemoteException{
        this.mainWindow=mainWindow;
    }

    @Override
    public void update(String msg) throws RemoteException {
        mainWindow.previewMessage(msg);
    }

    @Override
    public void updateOnlineList(ArrayList<String> onlineList) throws RemoteException {
        mainWindow.getOnlineList(onlineList);
    }

    @Override
    public String getWindowTitle()throws RemoteException{
        return mainWindow.getTitle();
    }

    @Override
    public void updatePrivateChat(String msg, String sendName, String recieverName)throws RemoteException{
        mainWindow.setPrivateMessage(msg,sendName,recieverName);
    }

    @Override
    public void checkFriends(String sendName, String receiverName) throws RemoteException{
        mainWindow.setRequest(sendName,receiverName);
    }

    @Override
    public void updateFriendsList(boolean isFind, String sendName, String receiverName) throws RemoteException{
       mainWindow.updateFriendsList(isFind,sendName,receiverName);
    }

    @Override
    public void cancelRequest(String sendName, String receiverName) {
        mainWindow.cancelRequest(sendName,receiverName);
    }
    
}
