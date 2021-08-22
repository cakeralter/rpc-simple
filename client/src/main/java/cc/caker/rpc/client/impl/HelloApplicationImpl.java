package cc.caker.rpc.client.impl;

import cc.caker.rpc.api.HelloService;
import cc.caker.rpc.client.HelloApplication;
import cc.caker.rpc.client.factory.ProxyFactory;

/**
 * TestServiceImpl
 *
 * @author cakeralter
 * @date 2021/8/22
 * @since 1.0
 */
public class HelloApplicationImpl implements HelloApplication {

    @Override
    public String hello(String name, String msg) {
        ProxyFactory factory = new ProxyFactory();
        HelloService helloService = factory.getInstance(HelloService.class);
        return helloService.hello(name, msg);
    }
}
