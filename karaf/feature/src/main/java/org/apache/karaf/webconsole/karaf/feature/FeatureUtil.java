package org.apache.karaf.webconsole.karaf.feature;

import java.util.HashSet;
import java.util.Set;

import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.url.URLConstants;
import org.osgi.service.url.URLStreamHandlerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class to work with feature related tasks.
 */
public class FeatureUtil {

    private static Logger logger = LoggerFactory.getLogger(FeatureUtil.class);

    public static Set<String> getAllowedProtocols(BundleContext context) {
        Set<String> protocols = new HashSet<String>();

        try {
            ServiceReference[] references = context.getAllServiceReferences(URLStreamHandlerService.class.getName(),
                "(!(" + URLConstants.URL_HANDLER_PROTOCOL + "=*))");
            for (ServiceReference reference : references) {
                Object property = reference.getProperty(URLConstants.URL_HANDLER_PROTOCOL);
                if (property != null) {
                    protocols.add((String) property);
                }
            }
        } catch (InvalidSyntaxException e) {
            logger.error("Exception during scanning the services", e);
        }

        return protocols;
    }

}
