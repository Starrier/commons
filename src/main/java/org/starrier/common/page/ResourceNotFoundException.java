package org.starrier.common.page;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Starrier
 * @date 2018/6/5.
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2565431806475335331L;


    private final String resourceName;

    private final Long id;

    public ResourceNotFoundException(String resourceName, Long id) {
        this.resourceName = resourceName;
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return StringUtils.capitalize(resourceName) + " with id " + id + " is not found.";
    }

}
