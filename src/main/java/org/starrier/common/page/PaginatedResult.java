package org.starrier.common.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Starrier
 * @date 2019/1/9.
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginatedResult implements Serializable {

    private static final long serialVersionUID = 6191745064790884707L;
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

}