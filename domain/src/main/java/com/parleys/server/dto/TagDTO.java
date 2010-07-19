package com.parleys.server.dto;

import java.io.Serializable;

/**
 * @author Stephan Janssen
 */
public class TagDTO implements Serializable {

    private static final long serialVersionUID = 3335009892839190816L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
