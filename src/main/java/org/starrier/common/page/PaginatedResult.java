package org.starrier.common.page;

import java.io.Serializable;

/**
 * @author Starrier
 * @date 2019/1/9.
 */
public class PaginatedResult implements Serializable {

    private static final long serialVersionUID = 6191745064790884707L;

    private PaginatedResult(){}

    /**
     * Current page number
     */
    private int currentPage;

    /**
     * Number of total pages
     */
    private int totalPage;

    /**
     * Paginated resources
     */
    private Object data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PaginatedResult{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", data=" + data +
                '}';
    }
}