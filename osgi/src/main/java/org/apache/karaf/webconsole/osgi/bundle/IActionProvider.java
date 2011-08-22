package org.apache.karaf.webconsole.osgi.bundle;

import org.apache.wicket.Component;
import org.osgi.framework.Bundle;

public interface IActionProvider {

    Component create(String componentId, Bundle object);

}
