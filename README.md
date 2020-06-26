# SComunication
A Simple Network Library
## Using
### MultiThreadServer
#### Create your server
```java
//extends MultiThreadServer
public class EchoServer extends MultiThreadServer {

    public EchoServer(int threadNumber, int port) throws IOException {
        //must called in first line
        super(threadNumber, port);
    }
    
    //Receive new data
    @Override
    public void onRead(SocketChannel channel,ByteBuffer data)
    {
        writeTo(channel, data);
    }

    //Write completely
    @Override
    public void onWriteCompletely(SocketChannel channel)
    {
    }

    //Framework error
    @Override
    public void onError(Exception err)
    {
    }

    //Client error
    @Override
    public void onError(SocketChannel channel,Exception err)
    {
    }

    //Server error
    @Override
    public void onError(ServerSocketChannel channel,Exception err)
    {
    }

    //New client connected
    @Override
    public void onAccept(SocketChannel channel)
    {
        //must called in last line
        super.onAccept(channel);
    }

    //Channel closed
    @Override
    public void onClose(SocketChannel channel)
    {
        //close channel safely
        closeChannel(channel);
    }
}
```
#### Run your server in Main function
```java
public class App {
    public static void main(String[] args) {
        try 
        {
            IServer server = new EchoServer(Runtime.getRuntime().availableProcessors(), 8080);
            System.out.printf("Server listening on %d with %d threads\n",8080,Runtime.getRuntime().availableProcessors());
            Runtime.getRuntime().addShutdownHook(new ServerCloseHookThread(server,()->
            {
                System.out.println("Server closed");
            }));
            server.run();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
```
### SingleThreadServer
#### Create your server
```java
//extends SingleThreadServer
public class EchoServer extends SingleThreadServer {

    public EchoServer(int port) throws IOException {
        //must called in first line
        super(port);
    }
    
    //Receive new data
    @Override
    public void onRead(SocketChannel channel,ByteBuffer data)
    {
        writeTo(channel, data);
    }

    //Write completely
    @Override
    public void onWriteCompletely(SocketChannel channel)
    {
    }

    //Framework error
    @Override
    public void onError(Exception err)
    {
    }

    //Client error
    @Override
    public void onError(SocketChannel channel,Exception err)
    {
    }

    //Server error
    @Override
    public void onError(ServerSocketChannel channel,Exception err)
    {
    }

    //New client connected
    @Override
    public void onAccept(SocketChannel channel)
    {
        //must called in last line
        super.onAccept(channel);
    }

    //Channel closed
    @Override
    public void onClose(SocketChannel channel)
    {
        //close channel safely
        closeChannel(channel);
    }
}
```
#### Run your server in Main function
```java
public class App {
    public static void main(String[] args) {
        try 
        {
            IServer server = new EchoServer(8080);
            System.out.printf("Server listening on %d with single thread\n",8080);
            Runtime.getRuntime().addShutdownHook(new ServerCloseHookThread(server,()->
            {
                System.out.println("Server closed");
            }));
            server.run();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}
```