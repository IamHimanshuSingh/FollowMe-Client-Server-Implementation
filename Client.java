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
                /*String serverGarbageData = "SomeInitialData";
                while(!serverGarbageData.equals(""))
                {
                    serverGarbageData = serverData.readUTF();
                }*/
                System.out.println("---------------------------------------------------------------");
            }
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
        Client client = new Client("127.0.0.1", 8888);
    } 
}
