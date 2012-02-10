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
package org.apache.karaf.webconsole.core.brand;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.behavior.IBehavior;
import org.apache.wicket.markup.html.image.Image;

/**
 * Interface which allows to customize look and feel.
 */
public interface BrandProvider {

    /**
     * Header image customization.
     * 
     * @param imageId Identifier of image element.
     * @return 
     */
    Image getHeaderImage(String imageId);

    /**
     * List of generic behaviours to apply on every page.
     * 
     * @return Brand specific behaviors, like CSS or JavaScript references.
     */
    List<IBehavior> getBehaviors();

    /**
     * Page specific hook which allows to modify or replace elements on singular
     * page.
     * 
     * @param page Page to modify.
     */
    void modify(Page page);
}
