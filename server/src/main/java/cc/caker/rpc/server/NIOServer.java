package cc.caker.rpc.server;

import cc.caker.rpc.common.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * NIOServer
 *
 * @author cakeralter
 * @date 2021/8/21
 * @since 1.0
 */
@Slf4j
public class NIOServer {

    private static final Map<String, Object> INSTANCE_MAP = new HashMap<>();

    public static void register(String serviceName, Object instance) {
        INSTANCE_MAP.put(serviceName, instance);
    }

    public void start() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
             Selector selector = Selector.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress("localhost", 9199));
            // 注册ServerSocketChannel
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int events = selector.select();
                if (events <= 0) {
                    continue;
                }

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();

                    if (selectionKey.isAcceptable()) {
                        try (ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                             SocketChannel socketChannel = channel.accept()) {
                            // 注册客户端Channel
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (selectionKey.isReadable()) {
                        try (SocketChannel channel = (SocketChannel) selectionKey.channel()) {
                            ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                            channel.read(requestBuffer);
                            try (ByteArrayInputStream bais = new ByteArrayInputStream(requestBuffer.array());
                                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                                Object obj = ois.readObject();
                                if (obj instanceof Request) {
                                    Request request = (Request) obj;
                                    System.out.println(obj);
                                }
                            }
                            requestBuffer.flip();
                        } catch (Exception ex) {
                            log.error("read error, ex={}", ExceptionUtils.getFullStackTrace(ex));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            log.error("An error occurred, ex={}", ExceptionUtils.getFullStackTrace(ex));
        }
    }
}
