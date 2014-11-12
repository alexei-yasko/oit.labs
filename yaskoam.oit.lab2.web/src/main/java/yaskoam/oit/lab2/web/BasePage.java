package yaskoam.oit.lab2.web;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.request.IRequestHandler;

import yaskoam.oit.lab2.service.TransportService;

public class BasePage<T> extends GenericWebPage<T> {

    public BasePage() {
        add(new Header("header"));

//        add(new RuntimeExceptionHandler() {
//                @Override
//                public IRequestHandler handleRuntimeException(Component component, Exception ex) {
//                    // just an example, you really can do anything you want here.
//                    // show a feedback message...
//                    MyPage.this.error("something went wrong");
//                    // then hide the affected component(s) so the error doesn't happen again...
//                    myComponentWithErrorInModel.setVisible(false); // ...
//                    // ...then finally just re-render this page:
//                    return new RenderPageRequestHandler(new PageProvider(MyPage.this));
//                }
//            }
//
//        )
    }

    public TransportService getTransportService() {
        return TransportationsApplication.get().getTransportService();
    }

//    private abstract class RuntimeExceptionHandler extends Behavior {
//
//        public abstract IRequestHandler handleRuntimeException(Component component, Exception ex);
//    }
}