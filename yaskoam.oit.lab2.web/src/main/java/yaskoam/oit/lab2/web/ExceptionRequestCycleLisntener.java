package yaskoam.oit.lab2.web;

import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;

/**
 * @author Q-YAA
 */
public class ExceptionRequestCycleLisntener extends AbstractRequestCycleListener {

    @Override
    public IRequestHandler onException(RequestCycle cycle, Exception ex) {

        return super.onException(cycle, ex);
    }
}
