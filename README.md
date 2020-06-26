# SComunication
A Simple Network Library
## Using
### MultiThreadServer
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
### SingleThreadServer
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