
# A Simple Client Server Implementation of Follow Me

**Steps to Run Server**:
1)  Compile Using : *javac Server.java*
2)  Run Using Command: *'java Server.java -p <port>'*
3)  Server will start listening on Port passed as argument
4)  Also Server will now wait for some client to connect
5)  Once Connected, You can now Play the Game
6)  In Every Turn, You Send an Instruction/Key and X seconds to client
7)  Client needs to reply within X seconds 
8)  If Client Replies within X seconds: Check the reply and match with request.
9) Upon Match, Update Client's Score to currentScore+1
10) Else Decrement by 1


**Steps to Run Client**:
1)  Compile Using :*javac Client.java*
2)  Run Using Command: *'java Client.java -h <host-ip> -p <port>'*
3)  Client will connect to Server
4)  Now wait for instructions from Server
5)  Once you get instrucions, it will contain key and time in seconds.
6)  You need to reply with the same Key within X seconds
