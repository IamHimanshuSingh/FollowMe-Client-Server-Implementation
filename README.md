
**A Simple Client Server Implementation of Follow Me**

Steps to Run Server:
1)  Compile Using : *javac Server.java*
    Run Using Command: *java Server.java -p <port>*
2)  Server will start listening on Port passed as argument
3)  Also Server will now wait for some client to connect
4)  Once Connected, You can now Play the Game
5)  In Every Turn, You Send an Instruction/Key and X seconds to client
6)  Client needs to reply within X seconds 
7)  If Client Replies within X seconds: Check the reply and match with request
    => Upon Match : Update Client's Score to currentScore+1
    => Else Decrement by 1


Steps to Run Client:
1)  Compile Using : *javac Client.java*
    Run Using Command: *java Client.java -h <host-ip> -p <port>*
2)  Client will connect to Server
3)  Now wait for instructions from Server
4)  Once you get instrucions, it will contain key and time in seconds.
5)  You need to reply with the same Key within X seconds


 

