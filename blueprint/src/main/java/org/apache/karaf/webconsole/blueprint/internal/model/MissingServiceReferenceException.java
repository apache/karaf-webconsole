package org.apache.karaf.webconsole.blueprint.internal.model;

import java.util.Arrays;

public class MissingServiceReferenceException extends RuntimeException {

    public MissingServiceReferenceException(String symbolicName, String[] classes) {
        super("Bundle " + symbolicName + " doesn't register any service with classes " + Arrays.toString(classes));
    }

}
