package Server;

import java.net.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class ServerThread implements Runnable {
    private Server server;
    private Socket soc;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String name = null;
    private boolean bStop = true;
    private ServerData data;
    private ServerAbout sa;
    private int turn;

    public ServerThread(Server server, Socket soc) throws IOException {
        this.server = server;
        this.soc = soc;
        this.sa = server.cp;
        out = new ObjectOutputStream(soc.getOutputStream());
        in = new ObjectInputStream(soc.getInputStream());
        new Thread(this).start();
    }

    public void run() {
        while (bStop) {
            try {
                data = (ServerData) in.readObject();

                if (name == null) {
                    name = data.getName();
                    turn = sa.getUser().size();
                }
                server.updateData(data);
            } catch (EOFException eof) {
                System.exit(0);
            } catch (IOException e) {
                this.setStop();
                sa.removeClient(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Client로부터 Socket이 끊어질 경우
        try {
            in.close();
            out.close();
            soc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setStart() {
        this.bStop = true;
    }

    public void setStop() {
        this.bStop = false;
    }

    public synchronized void sendMessage(ServerData data) {
        try {
            out.writeObject(data);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e, "ServerThread", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
