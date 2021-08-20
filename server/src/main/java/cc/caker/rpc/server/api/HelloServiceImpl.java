package cc.caker.rpc.server.api;

import cc.caker.rpc.api.HelloService;
import cc.caker.rpc.core.annotation.Provider;
import lombok.extern.slf4j.Slf4j;

/**
 * HelloServiceImpl
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
@Slf4j
@Provider(service = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name, String msg) {
        log.info("HelloServiceImpl::hello, name={}, msg={}", name, msg);
        return "Hello " + name + ", i'm wang!";
    }
}
