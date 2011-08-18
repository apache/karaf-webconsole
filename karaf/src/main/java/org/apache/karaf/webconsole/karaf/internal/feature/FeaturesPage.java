package org.apache.karaf.webconsole.karaf.internal.feature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.CSSPackageResource;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;

/**
 * Features
 */
public class FeaturesPage extends BasePage {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    public FeaturesPage() throws Exception {
        add(CSSPackageResource.getHeaderContribution(FeaturesPage.class, "features.css"));

        // Create a form for external Submit link
        Form<?> form = new Form("form");

        Repository[] repositories = featuresService.listRepositories();
        Feature[] features;
        List<ExtendedFeature> model = new ArrayList<ExtendedFeature>();

        for (Repository r : repositories) {
            features = r.getFeatures();
            for (Feature f : features) {
                ExtendedFeature.State state =
                        featuresService.isInstalled(f) ? ExtendedFeature.State.INSTALLED : ExtendedFeature.State.UNINSTALLED;
                ExtendedFeature extendedFeature = new ExtendedFeature(state, r.getName(), f);

                // add extended feature to Wicket model
                model.add(extendedFeature);
            }
        }


        IModel state = Model.of("state");
        IModel version = Model.of("version");
        IModel name = Model.of("name");
        IModel repository = Model.of("repository");
        IModel description = Model.of("description");

        List<IColumn<ExtendedFeature>> columns = new ArrayList<IColumn<ExtendedFeature>>();
        columns.add(new PropertyColumn<ExtendedFeature>(new StringResourceModel("table.version", this, version), "version", "version"));
        columns.add(new PropertyColumn<ExtendedFeature>(new StringResourceModel("table.name", this, state), "name", "name"));
        columns.add(new PropertyColumn<ExtendedFeature>(new StringResourceModel("table.repository", this, repository), "repository", "repository"));
        columns.add(new PropertyColumn<ExtendedFeature>(new StringResourceModel("table.description", this, description), "description", "description"));
        columns.add(new PropertyColumn<ExtendedFeature>(new StringResourceModel("table.state", this, state), "state", "state"));
        columns.add(new AbstractColumn<ExtendedFeature>(new ResourceModel("table.actions")) {
            public void populateItem(Item<ICellPopulator<ExtendedFeature>> cellItem, String componentId, IModel<ExtendedFeature> model) {
               cellItem.add(new FeaturesActionsPanel(componentId, model));
            }
        });

        add(new DefaultDataTable<ExtendedFeature>("features", columns, new FeaturesProvider(model), 20));

    }


    static class FeaturesProvider extends SortableDataProvider<ExtendedFeature> {

        List<ExtendedFeature> model;

        public FeaturesProvider(List model) {
            this.model = model;
            setSort("name", true);
        }

        public Iterator<? extends ExtendedFeature> iterator(int first, int count) {
            List<ExtendedFeature> data = new ArrayList<ExtendedFeature>(model);
            Collections.sort(data, new Comparator<ExtendedFeature>() {

                public int compare(ExtendedFeature o1, ExtendedFeature o2) {
                    int dir = getSort().isAscending() ? 1 : -1;

                    if ("name".equals(getSort().getProperty())) {
                        return dir * (o1.getName().compareTo(o2.getName()));
                    } else if ("repository".equals(getSort().getProperty())) {
                        return dir * (o1.getRepository().compareTo(o2.getRepository()));
                    } else if ("state".equals(getSort().getProperty())) {
                        return dir * (o1.getState().compareTo(o2.getState()));
                    } else {
                        return dir * (o1.getName().compareTo(o2.getName()));
                    }
                }
            });
            return data.subList(first, Math.min(first + count, data.size()))
                    .iterator();
        }

        public int size() {
            return model.size();
        }

        public IModel<ExtendedFeature> model(ExtendedFeature object) {
            return Model.of(object);
        }

    }


}
