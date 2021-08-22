package cc.caker.rpc.client;

/**
 * TestService
 *
 * @author cakeralter
 * @date 2021/8/22
 * @since 1.0
 */
public interface HelloApplication {

    /**
     * 测试hello
     *
     * @param name
     * @param msg
     * @return
     */
    String hello(String name, String msg);
}
