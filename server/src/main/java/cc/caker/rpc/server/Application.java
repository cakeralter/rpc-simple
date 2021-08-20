package cc.caker.rpc.server;

import cc.caker.rpc.core.annotation.Provider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.File;
import java.net.URL;

/**
 * Application
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
@Slf4j
public class Application {

    public static void main(String[] args) {
        findServices();
        RpcServer rpcServer = new RpcServer();
        rpcServer.start();
    }

    private static void findServices() {
        File locate = new File(Application.class.getResource("").getPath());
        findClasses(locate);
    }

    private static void findClasses(File dir) {
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                registerService(file);
            } else {
                findClasses(file);
            }
        }
    }

    private static void registerService(File file) {
        URL url = Application.class.getClassLoader().getResource("");
        String className = file.getPath()
                .substring(url.getPath().length())
                .replace("/", ".")
                .replace(".class", "");
        
        try {
            Class<?> clazz = Class.forName(className);
            Provider annotation = clazz.getAnnotation(Provider.class);
            if (annotation == null) {
                return;
            }
            String serviceName = annotation.service().getName();
            RpcServer.register(serviceName, clazz.newInstance());
            log.info("register service: {}", serviceName);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            log.error("ex={}", ExceptionUtils.getFullStackTrace(ex));
        }
    }
}
