package org.starrier.common.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author  Starrier
 * @date  2018/6/5.
 */
@Getter
@Setter
public class ResourceNameConstant {

    public static final String BOOK = "book";

    public static final String ARTICLE = "article";

    public static final String COMMENT = "comment";

    /**
     * Prevent instantiation.
     *
     * And if you want to expose these,you can use annotation {@link NoArgsConstructor}
     */
    private ResourceNameConstant() {
    }

}
