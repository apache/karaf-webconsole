package org.apache.karaf.webconsole.osgi.log;

import java.io.Serializable;

public class Options implements Serializable {

    private Priority priority = Priority.Any;

    private String bundleNameFragment;

    private String messageFragment;

    private Long dateFrom = null;

    private Long dateTo = null;

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getBundleNameFragment() {
        return bundleNameFragment;
    }

    public void setBundleNameFragment(String bundleNameFragment) {
        this.bundleNameFragment = bundleNameFragment;
    }

    public String getMessageFragment() {
        return messageFragment;
    }

    public void setMessageFragment(String messageFragment) {
        this.messageFragment = messageFragment;
    }

    public Long getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Long dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Long getDateTo() {
        return dateTo;
    }

    public void setDateTo(Long dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "Options [priority=" + priority + ", bundleNameFragment="
                + bundleNameFragment + ", messageFragment=" + messageFragment
                + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
    }

    

}
