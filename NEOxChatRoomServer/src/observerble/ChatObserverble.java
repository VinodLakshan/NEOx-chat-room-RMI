/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package observerble;

import com.sun.corba.se.impl.io.IIOPInputStream;
import controller.ChatControllerImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import observer.ChatObserver;

/**
 *
 * @author NEOx
 */
public class ChatObserverble {

    private ArrayList<ChatObserver> chtList = new ArrayList<>();
    private ArrayList<String> onlineList = new ArrayList<>();
    private HashMap<String, ArrayList<String>> frinds = new HashMap<>();
    FileWriter fileWriter;
    FileWriter logsWriter;

    public ChatObserverble() {
        try {
            fileWriter = new FileWriter("Friends List.txt", true);
            logsWriter = new FileWriter("Logs.txt", true);
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addTemporaryChatter(ChatObserver chtOb) {
        chtList.add(chtOb);
    }

    public void removeTemporaryChatter(ChatObserver chtOb) {
        chtList.remove(chtOb);
    }

    public void notifyMessage(String msg) throws RemoteException {
        for (ChatObserver chtOb : chtList) {
            chtOb.update(msg);
        }
    }

    public void addRegisteredChatter(ChatObserver chtOb, String name) {
        chtList.add(chtOb);
        onlineList.add(name);
    }

    public void removeRegisteredChatter(ChatObserver chtOb, String name) {
        chtList.remove(chtOb);
        onlineList.remove(name);
    }

    public void getOnlineList() throws RemoteException {
        for (ChatObserver chatObserver : chtList) {
            chatObserver.updateOnlineList(onlineList);
        }
    }

    public ArrayList<ChatObserver> getAllOnline() throws RemoteException {
        return chtList;
    }

    public void setPrivateChat(String msg, String sendName, String recieverName) throws RemoteException {
        for (ChatObserver chatObserver : chtList) {
            chatObserver.updatePrivateChat(msg, sendName, recieverName);
        }
    }

    public void checkInFriendList(String sendName, String receiverName, ChatObserver chtOb) {
        try {
            FileReader fileReader = new FileReader("Friends List.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            boolean isFriends=false;
            if (line != null) {
                String[] frinds = line.split("/");
                for (int i = 0; i < frinds.length; i++) {
                    String[] frnsWith = frinds[i].split(",");
                    if (frnsWith[0].equalsIgnoreCase(sendName) || frnsWith[1].equalsIgnoreCase(receiverName) && frnsWith[1].equalsIgnoreCase(receiverName) || frnsWith[0].equalsIgnoreCase(sendName)) {
                        isFriends = true;
                    }

                }
            }
                for (ChatObserver chtobs : chtList) {
                    chtobs.updateFriendsList(isFriends, sendName, receiverName);
                }
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendRequest(String sendName, String receiverName) throws RemoteException {
        for (ChatObserver chatObserver : chtList) {
            chatObserver.checkFriends(sendName, receiverName);
        }
    }

    public void confirmRequest(String sendName, String receiverName) {
        try {
            fileWriter.write(sendName + "," + receiverName + "/" + receiverName + "," + sendName + "/");
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int registerCustomer(String name, String pass, String email) {
        try {
            FileWriter fileWriter1 = new FileWriter("Register Customer.txt", true);
            fileWriter1.write(name + "," + pass + "," + email + "/");
            fileWriter1.flush();
        } catch (IOException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public int confirmLogin(String name, String pass) {
        int n = 0;
        try {
            FileReader fileReader = new FileReader("Register Customer.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = bufferedReader.readLine();

            String[] user = readLine.split("/");
            int count = 0;
            for (int i = 0; i < user.length; i++) {
                String[] loginDetails = user[i].split(",");
                if (loginDetails[0].equalsIgnoreCase(name) && loginDetails[1].equals(pass)) {
                    n = 1;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int checkUserName(String name) {
        int n = 0;
        try {
            FileReader fileReader = new FileReader("Register Customer.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String readLine = bufferedReader.readLine();
            String[] user = readLine.split("/");

            for (int i = 0; i < user.length; i++) {
                String[] uName = user[i].split(",");
                if (uName[0].equalsIgnoreCase(name)) {
                    n = 1;
                }
            }
            return n;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public void cancelRequest(String sendName, String receiverName) throws RemoteException {
        for (ChatObserver chatObserver : chtList) {
            chatObserver.cancelRequest(sendName, receiverName);
        }
    }

    public void privateChatHistory(String logoutTime, String loginTime, String sendName, String receiverName, String[] msgs) {
        File f1 = new File("PrivateChatHistory.txt");

        if (f1.exists()) {
            try {
                FileWriter fw = new FileWriter("PrivateChatHistory.txt", true);
                fw.write(sendName + "//" + receiverName + "//");
                fw.write("--------------------" + loginTime + "--------------------~msg~");
                for (int i = 0; i < msgs.length; i++) {
                    fw.write(msgs[i] + "~msg~");
                }
                fw.write("                               " + logoutTime);
                fw.write("===========================");
                fw.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                f1.createNewFile();
                FileWriter fw = new FileWriter("PrivateChatHistory.txt", true);
                fw.write(sendName + "//" + receiverName + "//");
                fw.write("--------------------" + loginTime + "--------------------~msg~");
                for (int i = 0; i < msgs.length; i++) {
                    fw.write(msgs[i] + "~msg~");
                }
                fw.write("                               " + logoutTime);
                fw.write("===========================");
                fw.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String[] getPrivateChatHistory(String sendName, String receiverName) {
        try {
            FileReader fr = new FileReader("PrivateChatHistory.txt");
            BufferedReader br = new BufferedReader(fr);
            String res = br.readLine();
            if (res != null) {
                String[] sessions = res.split("===========================");
                for (String session : sessions) {
                    String[] names = session.split("//");
                    if ((names[0].equalsIgnoreCase(sendName) || names[0].equalsIgnoreCase(receiverName)) && (names[1].equalsIgnoreCase(sendName) || names[1].equalsIgnoreCase(receiverName))) {
                        String[] msg = names[2].split("~msg~");
                        return msg;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void writeLogs(int registered, String loginTime, String logoutTime, String name) {
        if (registered == 1) {
            try {
                logsWriter.write("Registered Member  : " + name + " logedin at " + loginTime + " logout at " + logoutTime + "/");
                logsWriter.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                logsWriter.write("Tempory Logger  : " + name + " logedin at " + loginTime + " logout at " + logoutTime + "/");
                logsWriter.flush();
            } catch (IOException ex) {
                Logger.getLogger(ChatObserverble.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
