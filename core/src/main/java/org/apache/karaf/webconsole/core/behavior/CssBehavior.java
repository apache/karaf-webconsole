package org.apache.karaf.webconsole.core.behavior;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.request.resource.CssResourceReference;

public class CssBehavior extends Behavior {

    private static final long serialVersionUID = 1L;

    private final CssResourceReference cssResourceReference;

    public CssBehavior(Class<?> pageClass, String resource) {
        this(new CssResourceReference(pageClass, resource));
    }

    public CssBehavior(CssResourceReference resourceReference) {
        this.cssResourceReference = resourceReference;
    }

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        response.renderCSSReference(cssResourceReference);
    }

}
