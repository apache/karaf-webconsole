package org.apache.karaf.webconsole.karaf.internal.repository;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.form.LabelBorder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

public class AddRepositoryForm extends Form<Repository> {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    private TextField<String> uri;

    public AddRepositoryForm(String id) {
        super(id);

        uri = new TextField<String>("uri", Model.of("mvn:")) {
            @Override
            public boolean isRequired() {
                return true;
            }
        };

        LabelBorder border = new LabelBorder("border");
        border.add(new Label("label", "Repository URI"));
        border.add(uri);

        add(border);

        add(new SubmitLink("submit"));
    }

    @Override
    protected void onSubmit() {
        String add = uri.getModelObject();
        try {
            URI uri = new URI(add);
            featuresService.addRepository(uri);

            getSession().info("New repository with uri " + uri + " added");
            getRequestCycle().setResponsePage(RepositoriesPage.class);
        } catch (URISyntaxException e) {
            error("Cannot parse give uri " + e.getMessage());
        } catch (Exception e) {
            error("FeaturesService reported an error " + e.getMessage());
        }
    }

}
