package cool.yunlong.cloud.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 封装统一的响应结果
 *
 * @author yunlong
 * @since 2022/4/21 23:30
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestResponse<T> implements Serializable {

    private Integer code;

    private String message;

    private T response;

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;

    }
}

