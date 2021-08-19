package cc.caker.rpc.api;

/**
 * HelloService
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
public interface HelloService {

    /**
     * hello
     *
     * @param name
     * @param msg
     * @return
     */
    String hello(String name, String msg);
}
