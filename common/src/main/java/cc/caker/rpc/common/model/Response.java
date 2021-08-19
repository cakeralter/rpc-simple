package cc.caker.rpc.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Response
 *
 * @author cakeralter
 * @date 2021/8/18
 * @since 1.0
 */
@Builder
@Accessors(chain = true)
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 8771770247244996002L;
    private Boolean successful;
    private T data;
    private String errorCode;
    private String errorMsg;

    public static <T> Response<T> ok(T data) {
        return Response.<T>builder()
                .successful(true)
                .data(data)
                .build();
    }

    public static <T> Response<T> fail(String errorCode, String errorMsg) {
        return Response.<T>builder()
                .successful(false)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    public static <T> Response<T> fail(String errorMsg) {
        return Response.<T>builder()
                .successful(false)
                .errorCode("500")
                .errorMsg(errorMsg)
                .build();
    }
}
