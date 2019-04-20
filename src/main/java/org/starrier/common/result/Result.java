package org.starrier.common.result;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>Api Response Code Wrapper</p>
 * Custom return type classes.
 *
 * @author Starrier
 * @date 2018/11/11.
 * @see Result is the enhanced and custom version of  response.
 */
@AllArgsConstructor
@Builder
@Accessors(chain = true)
@Setter
@Getter
public class Result implements Serializable {

    private static final long serialVersionUID = -1709587390161841001L;

    private Integer code;

    private String message;

    private String url;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object data;

    private Result() {
    }


    public Result(Integer errorCode, String errMessage) {
        this.code = errorCode;
        this.message = errMessage;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }

    public Map<String, Object> simple() {
        Map<String, Object> simple = Maps.newHashMapWithExpectedSize(5);
        this.data = simple;
        return simple;
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * Success.
     *
     * @param data Object
     * @return {@link Result}
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result error(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

}
