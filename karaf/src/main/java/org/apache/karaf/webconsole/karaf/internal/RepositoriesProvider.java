package org.apache.karaf.webconsole.karaf.internal;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.karaf.webconsole.karaf.internal.model.RepositoryModel;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;

public class RepositoriesProvider extends SortableDataProvider<Repository> {

    private final FeaturesService service;

    public RepositoriesProvider(FeaturesService service) {
        this.service = service;

    }

    public Iterator<Repository> iterator(int first, int count) {
        return Arrays.asList(service.listRepositories())
                .subList(first, first + count).iterator();
    }

    public int size() {
        return service.listRepositories().length;
    }

    public IModel<Repository> model(Repository object) {
        return new RepositoryModel(service, object);
    }
}