package org.apache.karaf.webconsole.osgi.core.manifest;

import org.osgi.framework.Version;

public class ExportPackage extends Package {

	private static final long serialVersionUID = 1L;
	private Version version;
	private String uses;

	public ExportPackage(String name) {
		super(name);
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public Version getVersion() {
		return version;
	}

	public void setUses(String uses) {
		this.uses = uses;
	}

	public String getUses() {
		return uses;
	}

}
