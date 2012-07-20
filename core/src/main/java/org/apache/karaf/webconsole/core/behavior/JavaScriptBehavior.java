package org.apache.karaf.webconsole.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class JavaScriptBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private final JavaScriptResourceReference javaScriptResourceReference;

    public JavaScriptBehavior(Class<?> pageClass, String resource) {
        this(new JavaScriptResourceReference(pageClass, resource));
    }

    public JavaScriptBehavior(JavaScriptResourceReference resourceReference) {
        this.javaScriptResourceReference = resourceReference;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        response.renderCSSReference(javaScriptResourceReference);
    }

}
