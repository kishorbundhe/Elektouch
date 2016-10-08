package com.deba.elektouch;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Deba
 */
public class Client implements Serializable{

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String serverIP;
    private Socket connection;
    public String messageToSend="";

    public Client(String host){
        serverIP=host;
    }
    public void startRunning(){
        try{
            connectToServer();
            setupStreams();
            whileRunning();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void connectToServer()throws IOException{
        System.out.println("Attempting to conenct!");
        connection=new Socket(InetAddress.getByName(serverIP),6789);
        System.out.println("Connected to "+connection.getInetAddress().getHostName());
    }
    public void setupStreams()throws IOException{
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();
        input=new ObjectInputStream(connection.getInputStream());
        System.out.println("You are now setup!");
    }
    private void whileRunning()throws IOException{

    }
    public void sendMessage(String message){
        try{
        output.writeObject(message);
        output.flush();
        }catch(Exception e5){
        e5.printStackTrace();
        }
    }
}