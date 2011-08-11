package org.apache.karaf.webconsole.core;

import java.io.Serializable;
import java.util.Map;

/**
 * Since the ConsoleTab is used directly in wicket please make sure that the implementing object is serializable
 */
public interface ConsoleTab extends Serializable {

    String getLabel();

    Class getModuleHomePage();

    Map<String, Class> getItems();

}
