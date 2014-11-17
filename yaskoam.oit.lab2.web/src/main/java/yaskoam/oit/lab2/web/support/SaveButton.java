package yaskoam.oit.lab2.web.support;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.Button;

public class SaveButton extends Button {

    private Class<? extends Page> previousPage;

    public SaveButton(String id, Class<? extends Page> previousPage) {
        super(id);
        this.previousPage = previousPage;
    }

    @Override
    public void onSubmit() {
        setResponsePage(previousPage);
    }
}