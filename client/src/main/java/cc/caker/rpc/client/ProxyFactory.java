package cc.caker.rpc.client;

import cc.caker.rpc.common.model.Request;
import cc.caker.rpc.common.model.Response;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * ProxyFactory
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
public class ProxyFactory implements InvocationHandler {

    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("这是一个代理方法。。。。。");

        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Request request = new Request();
        request.setClassName(className);
        request.setMethodName(methodName);
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);

        // BIO & Jdk序列化
        try (Socket socket = new Socket("localhost", 9199);
             OutputStream os = socket.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(os);
             InputStream is = socket.getInputStream();
             ObjectInputStream ois = new ObjectInputStream(is)) {
            oos.writeObject(request);

            Object result = ois.readObject();
            if (result instanceof Response) {
                Response response = (Response) result;
                return response.getData();
            }

            throw new RuntimeException("invoke rpc fail");
        }
    }
}
