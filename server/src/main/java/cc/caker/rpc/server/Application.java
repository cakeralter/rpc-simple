package cc.caker.rpc.server;

import cc.caker.rpc.server.api.HelloServiceImpl;

/**
 * Application
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
public class Application {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.regist("cc.caker.rpc.api.HelloService", new HelloServiceImpl());
        rpcServer.start();
    }
}
