package org.apache.karaf.webconsole.osgi.core.manifest;

import org.apache.felix.utils.version.VersionRange;

public class ImportPackage extends Package {

	private static final long serialVersionUID = 1L;
	private VersionRange versionRange;
	private String provider;

	public ImportPackage(String name) {
		super(name);
	}

	public void setVersionRange(VersionRange versionRange) {
		this.versionRange = versionRange;
	}

	public VersionRange getVersionRange() {
		return versionRange;
	}
}
