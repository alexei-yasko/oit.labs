package yaskoam.oit.lab2.web;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import yaskoam.oit.lab2.web.car.CarsPage;
import yaskoam.oit.lab2.web.driver.DriversPage;
import yaskoam.oit.lab2.web.transportation.TransportationsPage;

public class Header extends Panel {

    public Header(String id) {
        super(id);

        add(new BookmarkablePageLink("transportationsLink", TransportationsPage.class));
        add(new BookmarkablePageLink("driversLink", DriversPage.class));
        add(new BookmarkablePageLink("carsLink", CarsPage.class));
    }
}
