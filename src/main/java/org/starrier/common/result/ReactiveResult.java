package org.starrier.common.result;

import com.google.common.collect.Maps;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author starrier
 * @date 2021/2/8
 */
public class ReactiveResult {

    private static final long serialVersionUID = -1709587390161841001L;

    private Integer code;

    private String message;

    private String url;

    private Object data;

    private ReactiveResult() {

    }

    public ReactiveResult(Integer errorCode, String errMessage) {
        this.code = errorCode;
        this.message = errMessage;
    }

    private ReactiveResult(ReactiveResult.Builder builder) {
        this.code = builder.code;
        this.data = builder.data;
        this.message = builder.message;
        this.url = builder.url;
    }

    public static ReactiveResult success() {
        ReactiveResult ReactiveResult = new ReactiveResult();
        ReactiveResult.setReactiveResultCode(ResultCode.SUCCESS);
        return ReactiveResult;
    }

    /**
     * Success.
     *
     * @param data Object
     * @return {@link ReactiveResult}
     */
    public static Mono<ReactiveResult> success(Object data) {
        ReactiveResult reactiveResult = new ReactiveResult();
        reactiveResult.setReactiveResultCode(ResultCode.SUCCESS);
        reactiveResult.setData(data);
        return Mono.just(reactiveResult);
    }

    public static Mono<ReactiveResult> success(Object data, String message) {
        ReactiveResult reactiveResult = new ReactiveResult();
        reactiveResult.setData(data);
        reactiveResult.setMessage(message);
        return Mono.just(reactiveResult);
    }

    public static Mono<ReactiveResult> error(ResultCode ReactiveResultCode) {
        ReactiveResult reactiveResult = new ReactiveResult();
        reactiveResult.setReactiveResultCode(ReactiveResultCode);
        return Mono.just(reactiveResult);
    }

    public static Mono<ReactiveResult> error(String errorMessage) {
        ReactiveResult reactiveResult = new ReactiveResult();
        reactiveResult.setReactiveResultCode(ResultCode.ERROR);
        reactiveResult.setMessage(errorMessage);
        return Mono.just(reactiveResult);
    }

    public static Mono<ReactiveResult> error(ResultCode ReactiveResultCode, Object data) {
        ReactiveResult reactiveResult = new ReactiveResult();
        reactiveResult.setReactiveResultCode(ReactiveResultCode);
        reactiveResult.setData(data);
        return Mono.just(reactiveResult);
    }

    public static ReactiveResult.Builder builder() {
        return new ReactiveResult.Builder();
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

    public Integer getCode() {
        return code;
    }

    public void setReactiveResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }

    public Map<String, Object> simple() {
        Map<String, Object> simple = Maps.newHashMapWithExpectedSize(5);
        this.data = simple;
        return simple;
    }

    public static class Builder {
        private Integer code;
        private String message;
        private String url;
        private Object data;

        public ReactiveResult.Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public ReactiveResult.Builder data(Object data) {
            this.data = data;
            return this;
        }

        public ReactiveResult.Builder message(String message) {
            this.message = message;
            return this;
        }

        public ReactiveResult.Builder url(String url) {
            this.url = url;
            return this;
        }

        public ReactiveResult build() {
            return new ReactiveResult(this);
        }
    }

}
