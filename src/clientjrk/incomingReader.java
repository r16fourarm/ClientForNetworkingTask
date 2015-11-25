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
public class incomingReader implements Runnable { 

    public incomingReader(guiclient gui,Clientjrk cli) {
        this.gui = gui;
        this.client=cli;
    }
        guiclient gui;
        Clientjrk client;
        @Override
        public void run() {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try {
                while ((stream = client.getReader().readLine()) != null) {
                    data = stream.split(":");
                    
                    System.out.println(stream);

                    if (data[2].equals(chat)) {
                       
                        gui.getTa_chat().append(data[0] + ": " + data[1] + "\n");
                        System.out.println(gui.getTa_chat().getText());
                        gui.getTa_chat().setCaretPosition(gui.getTa_chat().getDocument().getLength());
                    } else if (data[2].equals(connect)) {
                        gui.getTa_chat().removeAll();
                        client.userAdd(data[0]);
                    } else if (data[2].equals(disconnect)) {
                        client.userRemove(data[0]);
                    } else if (data[2].equals(done)) {
                        //users.setText("");
                        client.writeUsers();
                        client.getUsers().clear();
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
