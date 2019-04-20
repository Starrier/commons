package org.starrier.common.pageHelper;


import lombok.Getter;
import lombok.Setter;

/**
 * @author Starrier
 * @date 2018/6/5.
 */
@Getter
@Setter
public class PageConstant {

    /**
     * Default page number
     */
    public static final int PAGE = 1;

    /**
     * Default size of per page
     */
    public static final int PER_PAGE = 10;

    /**
     * Prevent instantiation.
     *
     * And if you need expose, {@link lombok.NoArgsConstructor} instead of if.
     */
    private PageConstant() {
    }

}
