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
package org.apache.karaf.webconsole.core.preferences.util;

import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * Preferences based on JDK mechanism.
 */
public class JdkPreferences implements Preferences {

    private java.util.prefs.Preferences prefs;

    public JdkPreferences(java.util.prefs.Preferences preferences) {
        this.prefs = preferences;
    }

    public void put(String key, String value) {
        prefs.put(key, value);
    }

    public String get(String key, String def) {
        return prefs.get(key, def);
    }

    public void remove(String key) {
        prefs.remove(key);
    }

    public void clear() throws BackingStoreException {
        try {
            prefs.clear();
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to clear", e);
        }
    }

    public void putInt(String key, int value) {
        prefs.putInt(key, value);
    }

    public int getInt(String key, int def) {
        return prefs.getInt(key, def);
    }

    public void putLong(String key, long value) {
        prefs.putLong(key, value);
    }

    public long getLong(String key, long def) {
        return prefs.getLong(key, def);
    }

    public void putBoolean(String key, boolean value) {
        prefs.putBoolean(key, value);
    }

    public boolean getBoolean(String key, boolean def) {
        return prefs.getBoolean(key, def);
    }

    public void putFloat(String key, float value) {
        prefs.putFloat(key, value);
    }

    public float getFloat(String key, float def) {
        return prefs.getFloat(key, def);
    }

    public void putDouble(String key, double value) {
        prefs.putDouble(key, value);
    }

    public double getDouble(String key, double def) {
        return prefs.getDouble(key, def);
    }

    public void putByteArray(String key, byte[] value) {
        prefs.putByteArray(key, value);
    }

    public byte[] getByteArray(String key, byte[] def) {
        return prefs.getByteArray(key, def);
    }

    public String[] childrenNames() throws BackingStoreException {
        try {
            return prefs.childrenNames();
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to get children names", e);
        }
    }

    public Preferences parent() {
        return new JdkPreferences(prefs.parent());
    }

    public Preferences node(String pathName) {
        return new JdkPreferences(prefs.node(pathName));
    }

    public boolean nodeExists(String pathName) throws BackingStoreException {
        try {
            return prefs.nodeExists(pathName);
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to check node", e);
        }
    }

    public void removeNode() throws BackingStoreException {
        try {
            prefs.removeNode();
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to remove node", e);
        }
    }

    public String name() {
        return prefs.name();
    }

    public String absolutePath() {
        return prefs.absolutePath();
    }

    public void flush() throws BackingStoreException {
        try {
            prefs.flush();
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to flush", e);
        }
    }

    public void sync() throws BackingStoreException {
        try {
            prefs.sync();
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to sync", e);
        }
    }

    public String[] keys() throws BackingStoreException {
        try {
            return prefs.keys();
        } catch (java.util.prefs.BackingStoreException e) {
            throw new BackingStoreException("Unable to get keys", e);
        }
    }

}
