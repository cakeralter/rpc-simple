package cc.caker.rpc.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Request
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
@Data
public class Request implements Serializable {

    private static final long serialVersionUID = 4525577033064684500L;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 参数类型
     */
    private Class<?>[] paramTypes;
    /**
     * 参数
     */
    private Object[] params;
    /**
     * group
     */
    private String group = "RPC";
    /**
     * version
     */
    private String version = "1.0.0";
}
