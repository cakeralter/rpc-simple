package test.client;

import cc.caker.rpc.client.HelloApplication;
import cc.caker.rpc.client.impl.HelloApplicationImpl;
import org.junit.Test;

/**
 * HelloApplicationTest
 *
 * @author cakeralter
 * @date 2021/8/22
 * @since 1.0
 */
public class HelloApplicationTest {

    @Test
    public void testHello() {
        HelloApplication helloApplication = new HelloApplicationImpl();
        String result = helloApplication.hello("luqian", "shuangma");
        System.out.println(result);
    }
}
