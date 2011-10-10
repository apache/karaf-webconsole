package org.apache.karaf.webconsole.itest;

import static junit.framework.Assert.assertTrue;
import static org.openengsb.labs.paxexam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.CoreOptions.maven;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

@RunWith(JUnit4TestRunner.class)
public class FeaturesIntegrationTest {

    @Inject
    private FeaturesService features;

    @Configuration
    public Option[] config() {
        return new Option[] {
            karafDistributionConfiguration().frameworkUrl(
                maven().groupId("org.apache.karaf").artifactId("apache-karaf").type("zip").version("2.2.0")
            ),
        };
    }

    @Test
    public void someTest() throws Exception {
        features.addRepository(new URI(maven("org.apache.karaf.webconsole", "features").version("0.3.0-SNAPSHOT").classifier("features").type("xml").getURL()));

        features.installFeature("webconsole-wicket");

        List<String> installed = new ArrayList<String>();
        for (Feature feature : features.listInstalledFeatures()) {
            installed.add(feature.getName());
        }

        assertTrue("Webconsole feature should be installed", installed.contains("webconsole-wicket"));
    }
}
