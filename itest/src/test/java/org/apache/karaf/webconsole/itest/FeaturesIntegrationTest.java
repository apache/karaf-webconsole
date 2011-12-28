/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.karaf.webconsole.itest;

import static junit.framework.Assert.assertTrue;
import static org.openengsb.labs.paxexam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;
import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.CoreOptions.systemProperty;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.karaf.features.Feature;
import org.apache.karaf.features.FeaturesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import org.ops4j.pax.exam.junit.JUnit4TestRunner;

@RunWith(JUnit4TestRunner.class)
public class FeaturesIntegrationTest {

    @Inject
    private FeaturesService features;

    private String featuresVersion;

    @Configuration
    public Option[] config() {
        String karafVersion = MavenUtils.getArtifactVersion("org.apache.karaf", "karaf");
        String webconsoleVersion = MavenUtils.getArtifactVersion("org.apache.karaf.webconsole", "features");
        return new Option[] {
            karafDistributionConfiguration().frameworkUrl(
                maven().groupId("org.apache.karaf").artifactId("apache-karaf").version(karafVersion).type("zip")
            ),

            // use system property to provide project version for tests
            systemProperty("webconsole-version").value(webconsoleVersion)
        };
    }

    @Before
    public void setUp() {
        featuresVersion = System.getProperty("webconsole-version");
    }

    @Test
    public void someTest() throws Exception {
        String url = maven("org.apache.karaf.webconsole", "features").version(featuresVersion).classifier("features").type("xml").getURL();

        features.addRepository(new URI(url));

        features.installFeature("webconsole-wicket");

        List<String> installed = new ArrayList<String>();
        for (Feature feature : features.listInstalledFeatures()) {
            installed.add(feature.getName());
        }

        assertIstalled("webconsole-wicket");
        assertIstalled("webconsole-core");
        assertIstalled("webconsole-karaf");
        assertIstalled("webconsole-osgi");
        assertIstalled("webconsole-config");
        assertIstalled("webconsole-log");
        assertIstalled("webconsole-event");
        assertIstalled("webconsole-blueprint");
        assertIstalled("webconsole-scr");
    }

    private void assertIstalled(String featureName) throws Exception {
        Feature feature = features.getFeature(featureName);

        assertTrue("Feature " + featureName + " should be installed", features.isInstalled(feature));
    }
}
