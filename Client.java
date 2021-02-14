import java.net.*;
import java.io.*;

public class Client
{
    private Socket socket = null;
    private DataInputStream clientData = null;
    private DataOutputStream out = null;
    private DataInputStream serverData = null; 
    public Client(String address, int port)
    {
        try 
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            
            clientData = new DataInputStream(System.in);
            serverData = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            String keyFromServer = "", timeX = "" ,myReply = "";
            while(socket != null)
            {
                //Read instruction from Server
                //Respond within X seconds time
                System.out.println("---------------------------------------------------------------");
                keyFromServer = serverData.readUTF();
                timeX = serverData.readUTF();
                System.out.println("Key from Server, Reply within X seconds : (Key, X) => " + keyFromServer + "," + timeX);
                myReply = clientData.readLine();
                out.writeUTF(myReply);
                System.out.println("---------------------------------------------------------------");
            }
        }
        catch(EOFException e)
        {
            System.out.println("GAME OVER!!!");
            System.out.println(e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try
        {
            clientData.close();
            serverData.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        if(args.length != 4)
        {
            System.out.println("Error: Please run again as-> java Client.java -h <host-ip> -p <port>");
            return;
        }
        String host = "";
        int port = 0;
        int i = 0;
        while(i < args.length)
        {
            if(args[i].equals("-h")){
                host = args[i+1];
            }
            else if(args[i].equals("-p")){
                port = Integer.parseInt(args[i+1]);
            }
            else{
                System.out.println("Error. Usage : java Client.java -h <host-ip> -p <port>");
                return;
            }
            i += 2;
        }
        Client client = new Client(host, port);
    } 
}
