package org.apache.karaf.webconsole.osgi.log;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.karaf.webconsole.core.table.PropertyColumnExt;
import org.apache.karaf.webconsole.osgi.framework.OsgiPage;
import org.apache.karaf.webconsole.osgi.log.search.BundleMatcher;
import org.apache.karaf.webconsole.osgi.log.search.DateFromMatcher;
import org.apache.karaf.webconsole.osgi.log.search.DateToMatcher;
import org.apache.karaf.webconsole.osgi.log.search.Matcher;
import org.apache.karaf.webconsole.osgi.log.search.MessageMatcher;
import org.apache.karaf.webconsole.osgi.log.search.PriorityMatcher;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DefaultDataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.ops4j.pax.wicket.api.PaxWicketBean;
import org.ops4j.pax.wicket.api.PaxWicketMountPoint;
import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogReaderService;

@PaxWicketMountPoint(mountPoint = "/osgi/log")
public class LogsPage extends OsgiPage {

    @PaxWicketBean(name = "logReader")
    private LogReaderService logReader;

    private Options options = new Options();

    public LogsPage() {
        CompoundPropertyModel<Options> model = new CompoundPropertyModel<Options>(new PropertyModel<Options>(this, "options"));
        setDefaultModel(model);

        @SuppressWarnings("unchecked")
        IColumn<LogEntry>[] columns = new IColumn[] {
            new AbstractColumn<LogEntry>(Model.of("time")) {
                public void populateItem(Item<ICellPopulator<LogEntry>> cellItem, String componentId, IModel<LogEntry> rowModel) {
                    long time = rowModel.getObject().getTime();
                    DateFormat format = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL);
                    cellItem.add(new Label(componentId, format.format(new Date(time))));
                }
            },
            new AbstractColumn<LogEntry>(Model.of("level")) {
                public void populateItem(Item<ICellPopulator<LogEntry>> cellItem, String componentId, IModel<LogEntry> rowModel) {
                    cellItem.add(new Label(componentId, Priority.valueOf(rowModel.getObject()).name()));
                }
            },
            new PropertyColumnExt<LogEntry>("Bundle", "bundle.symbolicName"),
            new PropertyColumnExt<LogEntry>("Version", "bundle.version"),
            new PropertyColumnExt<LogEntry>("Message", "message"),
            new PropertyColumnExt<LogEntry>("Exception", "exception"),
        };

        OptionsForm form = new OptionsForm("filters", model);

        List<Matcher> matchers = Arrays.asList(
            new PriorityMatcher(),
            new MessageMatcher(),
            new BundleMatcher(),
            new DateFromMatcher(),
            new DateToMatcher()
        );

        LogEntriesDataProvider provider = new LogEntriesDataProvider(logReader, options, matchers);
        DefaultDataTable<LogEntry> table = new DefaultDataTable<LogEntry>("logs", columns, provider, 20);

        add(table);
        add(form);
    }
}
