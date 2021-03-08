package org.starrier.common.result;

import com.google.common.collect.Maps;

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
public class Result implements Serializable {

    private static final long serialVersionUID = -1709587390161841001L;

    private Integer code;

    private String message;

    private String url;

    private Object data;

    private Result() {

    }

    public Result(Integer errorCode, String errMessage) {
        this.code = errorCode;
        this.message = errMessage;
    }

    private Result(Builder builder) {
        this.code = builder.code;
        this.data = builder.data;
        this.message = builder.message;
        this.url = builder.url;
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

    public static Result success(Object data, String message) {
        Result result = new Result();
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static Result error(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result error(String errorMessage) {
        Result result = new Result();
        result.setResultCode(ResultCode.ERROR);
        result.setMessage(errorMessage);
        return result;
    }

    public static Result error(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getData() {
        return data;
    }

    private void setData(Object data) {
        this.data = data;
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

    private static class Builder {
        private Integer code;
        private String message;
        private String url;
        private Object data;

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder data(Object data) {
            this.data = data;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Result build() {
            return new Result(this);
        }
    }
}
