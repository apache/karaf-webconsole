package org.apache.karaf.webconsole.karaf.internal.repository;

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
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;

import java.io.Serializable;
import java.net.URI;
import java.util.*;

@PaxWicketMountPoint(mountPoint = "/karaf/repositories")
public class RepositoriesPage extends BasePage {

	@PaxWicketBean(name = "featuresService")
	private FeaturesService featuresService;

	public RepositoriesPage() {
		List<ExtendedRepository> repos = new LinkedList<ExtendedRepository>();
		for (Repository repo : featuresService.listRepositories()) {
			repos.add(new ExtendedRepository(repo));
		}

		IColumn[] columns = new IColumn[] {
			new PropertyColumn<Repository>(Model.of("name"), "name", "name"),
			new PropertyColumn<Repository>(Model.of("URI"), "URI", "URI"),
			new PropertyColumn<Repository>(Model.of("valid"), "valid", "valid"),
		};

        add(new DefaultDataTable<ExtendedRepository>("repositories", columns, new RepositoriesProvider(repos), 20));
	}

    static class RepositoriesProvider extends SortableDataProvider<ExtendedRepository> {

       List<ExtendedRepository> model;

       public RepositoriesProvider(List model) {
           this.model = model;
           setSort("name", true);
       }

		public Iterator<? extends ExtendedRepository> iterator(int first, int count) {
			List<ExtendedRepository> data = new ArrayList<ExtendedRepository>(model);
			Collections.sort(data, new Comparator<ExtendedRepository>() {

                public int compare(ExtendedRepository o1, ExtendedRepository o2) {
                    int dir = getSort().isAscending() ? 1 : -1;

                    if ("name".equals(getSort().getProperty())) {
                        return dir * (o1.getName().compareTo(o2.getName()));
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

        public IModel<ExtendedRepository> model(ExtendedRepository object) {
            return Model.of(object);
        }
    }

	class ExtendedRepository implements Repository, Serializable {

		private final Repository repository;

		public ExtendedRepository(Repository r) {
			this.repository = r;
		}

		public String getName() {
			return repository.getName();
		}

		public URI getURI() {
			return repository.getURI();
		}

		public URI[] getRepositories() throws Exception {
			return repository.getRepositories();
		}

		public Feature[] getFeatures() throws Exception {
			return repository.getFeatures();
		}

		public boolean isValid() {
			return repository.isValid();
		}
    }
}
