package org.starrier.common.pageHelper;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Controller 参数校验，错误返回封装
 *
 * @author Starrier
 * @date 2019/1/31.
 */
@Getter
@Setter
@NoArgsConstructor
public class ParameterInvalidItem implements Serializable {

    private String fieldName;

    private String message;

}
