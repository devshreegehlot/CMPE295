import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelException;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import java.net.InetSocketAddress;

import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: sagar
 * Date: 2/25/12
 * Time: 6:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebServer {

    private void startWebServer() {
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(),10));

        // Set up the default event pipeline.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new HttpRequestDecoder());
                pipeline.addLast("encoder", new HttpResponseEncoder());
                pipeline.addLast("handler",
                        new WebServerServiceRequestHandler(WebServer.this));
                return pipeline;
            }
        });
        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        try {
            // Bind and start to accept incoming connections.
            bootstrap.bind(new InetSocketAddress(8081));
        } catch (ChannelException e) {
           System.out.println("Unable to start web server. Port " + "8081"+ " is unavailable. Skype running?");
            System.exit(1);
        }
    }

    public static void main(String args[])
    {
        WebServer server = new WebServer();
        server.startWebServer();
    }
}
