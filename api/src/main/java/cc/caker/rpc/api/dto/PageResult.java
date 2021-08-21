package cc.caker.rpc.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * PageResult
 *
 * @author cakeralter
 * @date 2021/8/21
 * @since 1.0
 */
@Builder
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1525111543593964170L;
    private Boolean successful;
    private String errorCode;
    private String errorMsg;

    private Integer pageNo;
    private Integer pageSize;
    private Integer totalPage;
    private Integer totalSize;
    private List<T> data;

    public static <T> PageResult<T> ok(Integer pageNo, Integer pageSize,
                                       Integer totalSize, List<T> data) {
        return PageResult.<T>builder()
                .successful(true)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalSize(totalSize)
                .totalPage((int) Math.ceil((double) totalSize / pageSize))
                .data(data)
                .build();
    }

    public static <T> PageResult<T> empty() {
        return PageResult.<T>builder()
                .successful(true)
                .data(Collections.emptyList())
                .build();
    }

    public static <T> PageResult<T> fail(String errorCode, String errorMsg) {
        return PageResult.<T>builder()
                .successful(false)
                .errorCode(errorCode)
                .errorMsg(errorMsg)
                .build();
    }

    public static <T> PageResult<T> fail(String errorMsg) {
        return fail("500", errorMsg);
    }
}
