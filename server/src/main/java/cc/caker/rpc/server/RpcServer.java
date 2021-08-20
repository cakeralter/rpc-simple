package cc.caker.rpc.server;

import cc.caker.rpc.common.model.Request;
import cc.caker.rpc.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * RpcServer
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
@Slf4j
public class RpcServer {

    private static final Map<String, Object> INSTANCE_MAP = new HashMap<>();

    public static void register(String serviceName, Object instance) {
        INSTANCE_MAP.put(serviceName, instance);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(9199);) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStream is = socket.getInputStream();
                     ObjectInputStream ois = new ObjectInputStream(is);
                     OutputStream os = socket.getOutputStream();
                     ObjectOutputStream oos = new ObjectOutputStream(os)) {
                    Object obj = ois.readObject();
                    if (obj instanceof Request) {
                        Request request = (Request) obj;
                        String className = request.getClassName();
                        String methodName = request.getMethodName();
                        Class<?>[] paramTypes = request.getParamTypes();
                        Object[] params = request.getParams();

                        Object instance = INSTANCE_MAP.get(className);
                        Method method = instance.getClass().getMethod(methodName, paramTypes);
                        Object result = method.invoke(instance, params);

                        oos.writeObject(Response.ok(result));
                    } else {
                        oos.writeObject(Response.fail("fail to invoke method"));
                    }
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    log.error("RpcServer error, ex={}", ExceptionUtils.getFullStackTrace(e));
                }
            }
        } catch (IOException ex) {
            log.error("RpcServer error, ex={}", ExceptionUtils.getFullStackTrace(ex));
        }
    }
}
