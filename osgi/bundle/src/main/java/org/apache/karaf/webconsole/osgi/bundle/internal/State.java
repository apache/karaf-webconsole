package org.apache.karaf.webconsole.osgi.bundle.internal;

public enum State {
    UNINSTALLED(1),
    INSTALLED(2),
    RESOLVED(4),
    STARTING(8),
    STOPPING(16),
    ACTIVE(32),

    UNKNOWN(-1);

    private final int mask;

    private State(int mask) {
        this.mask = mask;
        
    }

    public static State of(int state) {
        for (State enumeration : values()) {
            if (enumeration.mask == state) {
                return enumeration;
            }
        }
        return UNKNOWN;
    }
    
}
