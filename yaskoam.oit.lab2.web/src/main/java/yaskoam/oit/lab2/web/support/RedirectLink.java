package yaskoam.oit.lab2.web.support;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

public class RedirectLink extends Link {

    private Page page;

    public RedirectLink(String id, Page page) {
        super(id);
        this.page = page;
    }

    @Override
    public void onClick() {
        setResponsePage(page);
    }
}