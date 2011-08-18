package org.apache.karaf.webconsole.core.widget;

import org.apache.wicket.markup.html.panel.Panel;

/**
 * Widget extension point which may be used in many places. Services registered
 * in OSGi should provide information where it should be put using "intention"
 * property.
 */
public interface WidgetProvider {

    /**
     * Create new panel with given id. This method will be called during page
     * or parent component rendering - normally in container dispatching thread.
     * 
     * @param id Panel id.
     * @return Panel to be put as widget.
     */
    Panel getWidgetPanel(String id);

}
