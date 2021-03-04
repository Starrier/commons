package org.starrier.common.page;


/**
 * @author Starrier
 * @date 2018/6/5.
 */
public class PageConstant {

    /**
     * Default page number
     */
    public static final int PAGE = 1;

    /**
     * Default size of per page
     */
    public static final int PER_PAGE = 10;

    private PageConstant() {
    }

    public static int getPAGE() {
        return PAGE;
    }

    public static int getPerPage() {
        return PER_PAGE;
    }
}
