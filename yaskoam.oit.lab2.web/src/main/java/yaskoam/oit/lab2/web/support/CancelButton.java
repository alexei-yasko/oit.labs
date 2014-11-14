package yaskoam.oit.lab2.web.support;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;

public class CancelButton extends Button {

    private Page previousPage;

    public CancelButton(String id, Page previousPage) {
        super(id);

        this.previousPage = previousPage;
        setDefaultFormProcessing(false);
    }

    @Override
    public void onSubmit() {
        setResponsePage(previousPage);
    }
}