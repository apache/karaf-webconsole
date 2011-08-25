package org.apache.karaf.webconsole.osgi.bundle;

import java.io.Serializable;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.osgi.framework.Bundle;

public interface IColumnProvider extends Serializable {

    IColumn<Bundle> getColumn();

}
