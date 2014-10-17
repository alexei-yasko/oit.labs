package yaskoam.oit.lab1;

import javafx.scene.canvas.GraphicsContext;

public class Face extends Ellipse {

    private Eye eye1;
    private Eye eye2;

    private Mouth mouth;

    private int eyesMovementDirection = 1;
    private int mouthMovementDirection = 1;

    public Face(double x, double y, double a, double b) {
        super(x, y, a, b);

        eye1 = new Eye(calculateEye1X(), calculateEyeY(), calculateEyeA(), calculateEyeB());
        eye2 = new Eye(calculateEye2X(), calculateEyeY(), calculateEyeA(), calculateEyeB());

        mouth = new Mouth(calculateMouthX(), calculateMouthY(), calculateMouthA(), calculateMouthB());
    }

    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);

        eye1.draw(context);
        eye2.draw(context);

        mouth.draw(context);
    }

    public void moveEyes() {
        eyesMovementDirection =
            getX() - getA() * 0.6 >= eye1.getX() ? eyesMovementDirection * -1 : eyesMovementDirection;

        eyesMovementDirection =
            getX() + getA() * 0.6 <= eye2.getX() ? eyesMovementDirection * -1 : eyesMovementDirection;

        eye1.move(4 * eyesMovementDirection, 0);
        eye2.move(4 * eyesMovementDirection, 0);
    }

    public void resizeMouth() {
        mouthMovementDirection = getB() * 0.3 <= mouth.getB() ? mouthMovementDirection * -1 : mouthMovementDirection;
        mouthMovementDirection = 0 >= mouth.getB() ? mouthMovementDirection * -1 : mouthMovementDirection;

        mouth.resize(0, 4 * mouthMovementDirection);
    }

    private double calculateEye1X() {
        return getX() - getA() * 0.4;
    }

    private double calculateEye2X() {
        return getX() + getA() * 0.4;
    }

    private double calculateEyeY() {
        return getY() - getB() * 0.5;
    }

    private double calculateEyeA() {
        return getA() * 0.15;
    }

    private double calculateEyeB() {
        return getB() * 0.15;
    }

    private double calculateMouthX() {
        return getX();
    }

    private double calculateMouthY() {
        return getY() + getA() * 0.3;
    }

    private double calculateMouthA() {
        return getA() * 0.3;
    }

    private double calculateMouthB() {
        return getB() * 0.2;
    }
}