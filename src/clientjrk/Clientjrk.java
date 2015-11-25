/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientjrk;

/**
 *
 * @author R16
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class Clientjrk {

    String username, address = "localhost", msg;
    ArrayList<String> users = new ArrayList();
    int port = 2225;
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getIsConnected() {
        return isConnected;
    }

    public BufferedReader getReader() {
        return reader;
    }

  /**  public void ListenThread() {
        Thread IncomingReader = new Thread(new incomingReader());
        IncomingReader.start();
    }*/

    public void userAdd(String data) {
        users.add(data);
    }

    public String userRemove(String data) {
        String s = data + " is now offline)";
        return s;
    }

    public void writeUsers() {
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
        for (String token : tempList) {
            //users.append(token + "\n");
        }
    }

    public void Connect() throws IOException {
        sock = new Socket(address, port);
        InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(streamreader);
        writer = new PrintWriter(sock.getOutputStream());
        writer.println(username + ":has connected.:Connect");
        writer.flush();
        isConnected = true;
    }

    public void anonConnect() throws IOException {
        String anon = "anon";
        Random generator = new Random();
        int i = generator.nextInt(999) + 1;
        String is = String.valueOf(i);
        anon = anon.concat(is);
        setUsername(anon);
        sock = new Socket(address, port);
        InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
        reader = new BufferedReader(streamreader);
        writer = new PrintWriter(sock.getOutputStream());
        writer.println(anon + ":has connected.:Connect");
        writer.flush();
        isConnected = true;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public String sendDisconnect() {
        String s = "";
        String bye = (username + ": :Disconnect");
        try {
            writer.println(bye);
            writer.flush();
        } catch (Exception e) {
            s = "Could not send Disconnect message";
        }
        return s;
    }

    public String Disconnect() {
        String s = "";
        try {
            msg = "Disconnected";
            sock.close();
        } catch (Exception ex) {
            s = "Failed to disconnect";
        }
        isConnected = false;
        //tf_username.setEditable(true);
        return s;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

}
