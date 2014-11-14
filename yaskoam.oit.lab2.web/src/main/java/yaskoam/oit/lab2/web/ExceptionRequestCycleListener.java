package yaskoam.oit.lab2.web;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionRequestCycleListener extends AbstractRequestCycleListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionRequestCycleListener.class);

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex) {
        LOGGER.error("Error!", ex);
        return super.onException(cycle, ex);
    }
}