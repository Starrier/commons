package org.starrier.common.page;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Starrier
 * @date 2018/6/5.
 */
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2565431806475335331L;

    private final String resourceName;

    private final Long id;

    @Override
    public String getMessage() {
        return StringUtils.capitalize(resourceName) + " with id " + id + " is not found.";
    }

}
