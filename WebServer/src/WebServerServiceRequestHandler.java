import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequest;

/**
 * Created by IntelliJ IDEA.
 * User: sagar
 * Date: 2/25/12
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebServerServiceRequestHandler extends SimpleChannelUpstreamHandler {

    private volatile HttpRequest request;

    public WebServerServiceRequestHandler(WebServer server) {
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        super.messageReceived(ctx, e);    //To change body of overridden methods use File | Settings | File Templates.

        request = (HttpRequest) e.getMessage();
        
        System.out.println("Sagar Bhosale is great!! Request: "+request);
        handleRequest(request);
    }

    private void handleRequest(HttpRequest request) {

        
    }
}
