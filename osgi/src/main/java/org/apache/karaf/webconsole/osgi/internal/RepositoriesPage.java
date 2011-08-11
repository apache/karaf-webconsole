package org.apache.karaf.webconsole.osgi.internal;

import java.io.Serializable;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.core.BasePage;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigationToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.apache.wicket.model.Model;
import org.ops4j.pax.wicket.api.PaxWicketBean;

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

		ListDataProvider provider = new ListDataProvider(repos);
		DataTable<Repository> dataTable = new DataTable<Repository>("repositories", columns, provider, 10);
		dataTable.addTopToolbar(new NavigationToolbar(dataTable));
		add(dataTable);
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
