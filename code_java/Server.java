import java.net.*;
import java.io.*;

public class Server
{
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream clientData = null;
    private DataInputStream inputKey = null;
    private DataInputStream inputX = null;
    private DataOutputStream serverData = null;
    private int gameScore, noResponseEvents;
    
    /*
    Function Name : checkResponseAndUpdateScore
    Description: 
                Validates the Client's Response along with checking if it has been received within given timeX
    Arguments:
                responseTime
                timeX
                serverInstruction   
                clientResponse
    */

    public void checkResponseAndUpdateScore(long responseTime, String timeX, String serverInstruction, String clientResponse)
    {
        if(responseTime > Integer.parseInt(timeX))
        {
           System.out.println("Oops!!!..Late response from Client: Timeout");
           noResponseEvents++;
        }
        else if(clientResponse.equals(serverInstruction))
        {
           System.out.println("Great!!!. Keys Match(Score+1)");
           gameScore++;
           noResponseEvents = 0;
        }
        else if(!clientResponse.equals(serverInstruction))
        {
           System.out.println("OOPS !!!. Keys do not Match");
           gameScore--;
           noResponseEvents = 0;
        }
    }

    /*
    Function Name : playFollowMe
    Description:
        => Contains Main Logic of Follow me Game
    Arguments:
        =>  timeX
        =>  serverInstruction
        =>  clientResponse
    */

    public void playFollowMe (String timeX, String serverInstruction, String clientResponse)
    {
         while(gameScore < 10 && gameScore > -3 && noResponseEvents < 3)
         {
             try
             {
                 //Read inputKey from Server
                 //Send it to client along with X seconds Time window
                 System.out.println("------------------------------------------------------");
                 System.out.println("Enter Key and time X seconds(Should be a Number): ");
                 serverInstruction = inputKey.readLine();
                 timeX = inputX.readLine();
                 System.out.println("Sending Data to Client... ");
                 serverData.writeUTF(serverInstruction);
                 serverData.writeUTF(timeX);
                 System.out.println("Sent!!!. Waiting for Client's response...");

                 //Calculate Client's response time
                 long startTime = System.currentTimeMillis();
                 clientResponse = clientData.readUTF();
                 long responseTime = (System.currentTimeMillis() - startTime)/1000;
                 System.out.println("Response Time : " + responseTime + " seconds");
                 System.out.println("Clients Reply : " + clientResponse);
                 //Validate the client's response and Update score accordingly
                 checkResponseAndUpdateScore(responseTime, timeX, serverInstruction, clientResponse);
             }
             catch(IOException i)
             {
                 System.out.println(i);
             }
             /*String clientGarbageData = "SomeInitialData";
             while(!clientGarbageData.equals(""))
             {
                 clientGarbageData = clientData.readUTF();
             }*/
             System.out.println("Current Score : " + gameScore);
             System.out.println("------------------------------------------------------\n");
         }
    }
    /*
    Server Contructor() :
    Description:
        => Opens a Socket to list at a Port
        => Accepts connection from Client on another socket
        => Calls playFollowMe game function
    */
    public Server(int port)
    {
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server Started..Listening on Port : " + port);
            System.out.println("Waiting for a client..");

            socket = server.accept();
            System.out.println("Client Accepted!!!");

            clientData = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            inputKey = new DataInputStream(System.in);
            inputX = new DataInputStream(System.in);
            serverData = new DataOutputStream(socket.getOutputStream());
            gameScore = 0;
            noResponseEvents = 0;
            String serverInstruction = "", clientResponse = "", timeX = "";

            //Main Logic Function Call
            playFollowMe(timeX, serverInstruction, clientResponse);

            //Closing Sockets and Streams
            System.out.println("Closing Connection"); 

            socket.close();
            serverData.close();
            clientData.close();
            inputKey.close();
            inputX.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    public static void main(String args[])
    {
        Server server = new Server(8888);
    }
}
