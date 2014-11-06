package yaskoam.oit.lab2.desktop.support;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DoubleTextFieldConstraint implements ChangeListener<String> {

    private static final String PATTERN = "[0-9\\.]*";

    private TextField textField;

    public DoubleTextFieldConstraint(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends String> value, String s, String s2) {
        if (!s2.matches(PATTERN)) {
            textField.setText(s);
        }
    }
}