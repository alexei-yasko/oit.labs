package yaskoam.oit.lab1;

import javafx.scene.paint.Color;

public class Mouth extends Ellipse {

    public Mouth(double x, double y, double a, double b) {
        super(x, y, a, b);
    }

    @Override
    protected Color getColor() {
        return Color.RED;
    }
}