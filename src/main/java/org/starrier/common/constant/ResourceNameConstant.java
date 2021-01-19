package org.starrier.common.constant;

/**
 * @author Starrier
 * @date 2018/6/5.
 */
public class ResourceNameConstant {

    public static final String BOOK = "book";

    public static final String ARTICLE = "article";

    public static final String COMMENT = "comment";

    /**
     * Prevent instantiation.
     * <p>
     * And if you want to expose these,you can use annotation {@link NoArgsConstructor}
     */
    private ResourceNameConstant() {
    }

    public static String getBOOK() {
        return BOOK;
    }

    public static String getARTICLE() {
        return ARTICLE;
    }

    public static String getCOMMENT() {
        return COMMENT;
    }
}
