package org.apache.karaf.webconsole.karaf.internal.model;

import java.net.URI;

import org.apache.karaf.features.FeaturesService;
import org.apache.karaf.features.Repository;
import org.apache.wicket.model.LoadableDetachableModel;

public class RepositoryModel extends LoadableDetachableModel<Repository> {

    private final FeaturesService service;
    private URI uri;

    public RepositoryModel(FeaturesService service, Repository object) {
        this.service = service;
        this.uri = object.getURI();
    }

    @Override
    protected Repository load() {
        Repository[] repositories = service.listRepositories();
        for (Repository repo : repositories) {
            if (uri.equals(repo.getURI())) {
                return repo;
            }
        }

        throw new RepositoryNotFoundException(uri);
    }

}
