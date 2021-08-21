package cc.caker.rpc.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Result
 *
 * @author cakeralter
 * @date 2021/8/21
 * @since 1.0
 */
@Builder
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = -6707374450216469145L;
    private Boolean successful;
    private T data;
    private String errorCode;
    private String errorMsg;

    public static <T> Result<T> ok(T data) {
        return new ResultBuilder<T>()
                .successful(true)
                .data(data)
                .build();
    }

    public static <T> Result<T> fail(String errorCode, String errorMsg) {
        return new ResultBuilder<T>()
                .successful(false)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    public static <T> Result<T> fail(String errorMsg) {
        return fail("500", errorMsg);
    }
}
