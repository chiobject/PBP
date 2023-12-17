//package server2;
//
//import java.io.*;
//import java.util.*;
//
//public class ClientThread implements Runnable {
//    private Client ct;
//    private ObjectInputStream in;
//    private boolean bStop = true;
//
//    public ClientThread(Client ct, ObjectInputStream in) {
//        this.ct = ct;
//        this.in = in;
//        Thread t = new Thread(this);
//        t.start();
//    }
//
//    public void run() {
//        while (bStop) {
//            try {
//                ServerData data = (ServerData) in.readObject();
//                ct.updateData(data);
//            } catch (EOFException eof) {
//                System.exit(0);
//            } catch (IOException e) {
//                this.bStop = false;
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void setStop() {
//        bStop = false;
//    }
//}