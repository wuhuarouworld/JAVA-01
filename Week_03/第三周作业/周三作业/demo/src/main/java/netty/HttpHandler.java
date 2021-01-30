package netty;

import com.sun.javafx.fxml.builder.URLBuilder;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutorGroup;
import okhttp3.*;

import java.nio.charset.StandardCharsets;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri(); //接收到的请求
            if (uri.contains("/test")) {
                handleTest(request,ctx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handleTest(FullHttpRequest request, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;

        try {
            String value = null;
            OkHttpClient httpClient = new OkHttpClient.Builder().build();
            Request request1 = new Request.Builder().url("http://localhost:8802").build();
            try (Response response1 = httpClient.newCall(request1).execute()) {
                ResponseBody body = response1.body();
                if (response1.isSuccessful()) {
                    value = body.toString();
                }
             }
            response = new DefaultFullHttpResponse(HTTP_1_1,OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-type","application/json");
            response.headers().set("Content-Length",response.content().readableBytes());

        } catch (Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1,NO_CONTENT);
        } finally {
            if (request != null) {
                if (!HttpUtil.isKeepAlive(request)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }
    }


}
