package org.apache.karaf.webconsole.osgi.core.manifest;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.felix.utils.manifest.Clause;
import org.apache.felix.utils.manifest.Parser;
import org.osgi.framework.Bundle;

public abstract class ManifestUtil {

    public static Clause[] getHeader(Bundle bundle, String header) throws IOException {
        return getHeader(bundle.getResource("META-INF/MANIFEST.MF").openStream(), header);
    }

    public static Clause[] getHeader(InputStream stream, String header) throws IOException {
        Manifest manifest = new Manifest(stream);
		Attributes mainAttributes = manifest.getMainAttributes();
		String value = mainAttributes.getValue(header);
        return Parser.parseHeader(value);
    }

}
