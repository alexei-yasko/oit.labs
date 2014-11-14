package yaskoam.oit.lab2.web.support;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;

public class SaveButton extends Button {

    private Page previousPage;

    public SaveButton(String id, Page previousPage) {
        super(id);
        this.previousPage = previousPage;
    }

    @Override
    public void onSubmit() {
        setResponsePage(previousPage);
    }
}