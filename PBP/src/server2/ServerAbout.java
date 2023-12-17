//package server2;
//
//import java.util.Vector;
//
//public class ServerAbout {
//    private Vector<ServerThread> buffer;
//    private Vector<String> logName;
//    private Vector turnNum;
//
//    public ServerAbout() {
//        buffer = new Vector<ServerThread>(5, 1);
//        logName = new Vector<String>(5, 1);
//        turnNum = new Vector(5, 1);
//    }
//
//    public synchronized Vector<String> getLogName() {
//        return logName;
//    }
//
//    public synchronized Vector<ServerThread> getUser() {
//        return buffer;
//    }
//
//    public synchronized Vector getTurnNum() {
//        return turnNum;
//    }
//
//    public synchronized void addClient(ServerThread ft) {
//        buffer.addElement(ft);
//        logName.addElement(ft.toString());
//    }
//
//    public synchronized void removeClient(String name) {
//        for (int i = 0; i < buffer.size(); i++) {
//            if (buffer.elementAt(i).getName().equals(name)) {
//                buffer.removeElementAt(i);
//                logName.removeElementAt(i);
//                break;
//            }
//        }
//    }
//
//    public synchronized void broadCasting(ServerData data) {
//        for (int i = 0; i < buffer.size(); i++) {
//            ServerThread clientThread = buffer.elementAt(i);
//            clientThread.sendMessage(data);
//        }
//    }
//
//    public void setStop(ServerData data) {
//        for (int i = 0; i < buffer.size(); i++) {
//            buffer.elementAt(i).setStop();
//        }
//    }
//}