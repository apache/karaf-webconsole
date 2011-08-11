package org.apache.karaf.webconsole.osgi.internal;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

import java.util.*;

/**
 * Features
 */
public class FeaturesPage extends BasePage {

    @PaxWicketBean(name = "featuresService")
    private FeaturesService featuresService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     *
     * @param parameters Page parameters
     */
    public FeaturesPage() throws Exception {

        Repository[] repositories = featuresService.listRepositories();
        Feature[] features;
        List<ExtendedFeature> model = new ArrayList<ExtendedFeature>();

        for (Repository r : repositories) {
            features = r.getFeatures();
            for (Feature f : features) {
                    ExtendedFeature.State state =
                        featuresService.isInstalled(f) ? ExtendedFeature.State.INSTALLED : ExtendedFeature.State.UNINSTALLED;
                    ExtendedFeature extendedFeature = new ExtendedFeature(  state, r.getName(), f );

                    // add extended feature to Wicket model
                    model.add(extendedFeature);
            }
        }

/*        add(new ListView<ExtendedFeature>("features", model) {
            @Override
            protected void populateItem(ListItem<ExtendedFeature> item) {
                final ExtendedFeature feature = item.getModelObject();
                item.add(new Label("state", feature.getState().toString()));
                item.add(new Label("version", feature.getVersion()));
                item.add(new Label("name", feature.getName()));
                item.add(new Label("repository", feature.getRepository()));
                item.add(new Label("description", feature.getDescription()));

                item.add(new Link("featuresDetailsPageLink") {
                    @Override
                    public void onClick() {
                        PageParameters params = new PageParameters();
                        params.put("featureId", feature.getId());
                    }
                });
            }
        });*/

        List<IColumn<ExtendedFeature>> columns = new ArrayList<IColumn<ExtendedFeature>>();
		columns.add(new PropertyColumn<ExtendedFeature>(Model.of("state"), "state",
				"state"));
		columns.add(new PropertyColumn<ExtendedFeature>(Model.of("version"), "version",
				"version"));
        columns.add(new PropertyColumn<ExtendedFeature>(Model.of("name"), "name",
				"name"));
        columns.add(new PropertyColumn<ExtendedFeature>(Model.of("repository"), "repository",
				"repository"));
        columns.add(new PropertyColumn<ExtendedFeature>(Model.of("description"), "description",
				"description"));

		add(new DefaultDataTable<ExtendedFeature>("features", columns,
				new FeaturesProvider(model), 20));

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
