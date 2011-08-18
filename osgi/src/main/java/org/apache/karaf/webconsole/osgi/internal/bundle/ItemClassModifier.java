package org.apache.karaf.webconsole.osgi.internal.bundle;

import java.util.List;

import org.osgi.framework.Bundle;

public interface ItemClassModifier {

    List<String> getCssClasses(Bundle bundle);

}