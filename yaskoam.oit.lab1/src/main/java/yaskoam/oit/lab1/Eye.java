package yaskoam.oit.lab1;

import javafx.scene.paint.Color;

public class Eye extends Ellipse {

    public Eye(double x, double y, double a, double b) {
        super(x, y, a, b);
    }

    @Override
    protected Color getColor() {
        return Color.BLUE;
    }
}