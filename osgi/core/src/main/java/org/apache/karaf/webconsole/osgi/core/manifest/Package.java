package org.apache.karaf.webconsole.osgi.core.manifest;

import java.io.Serializable;
import java.util.Map;

import org.osgi.framework.Version;

public class Package implements Serializable {

	private String name;
	private Map<String, String> attributes;

	public Package(String name) {
		this.name = name;
	}


	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

}
