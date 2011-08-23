package org.apache.karaf.webconsole.karaf.internal.model;

import java.net.URI;

public class RepositoryNotFoundException extends RuntimeException {

    public RepositoryNotFoundException(URI uri) {
        super(uri.toString());
    }

}
