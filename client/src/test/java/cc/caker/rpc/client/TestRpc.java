package cc.caker.rpc.client;

import cc.caker.rpc.api.HelloService;
import org.junit.Test;

/**
 * TestRpc
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
public class TestRpc {

    @Test
    public void testServer() {
        ProxyFactory factory = new ProxyFactory();
        HelloService helloService = factory.getInstance(HelloService.class);
        String result = helloService.hello("luqian", "haosao");
        System.out.println(result);
    }
}
