package org.starrier.common.page;


import java.io.Serializable;

/**
 * Controller 参数校验，错误返回封装
 *
 * @author Starrier
 * @date 2019/1/31.
 */
public class ParameterInvalidItem implements Serializable {

    private String fieldName;

    private String message;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
