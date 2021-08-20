package cc.caker.rpc.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Provider
 *
 * @author cakeralter
 * @date 2021/8/20
 * @since 1.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Provider {

    /**
     * 服务接口
     *
     * @return
     */
    Class<?> service();

    /**
     * 服务group
     *
     * @return
     */
    String group() default "RPC";

    /**
     * 服务版本
     *
     * @return
     */
    String version() default "1.0.0";

    /**
     * 默认超时
     *
     * @return
     */
    long timeout() default 30000;
}
