package yaskoam.oit.lab1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ellipse {

    private double x;
    private double y;

    private double a;
    private double b;

    public Ellipse(double x, double y, double a, double b) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
    }

    public void draw(GraphicsContext context) {
        context.setStroke(getColor());
        context.strokeOval(x - a, y - b, a * 2, b * 2);
    }

    public void move(double deltaX, double deltaY) {
        x += deltaX;
        y += deltaY;
    }

    public void resize(double deltaA, double deltaB) {
        a += deltaA;
        b += deltaB;
    }

    protected Color getColor() {
        return Color.BLACK;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
}
