package Question_4;

public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double angle_between;

    public Triangle(double x, double y,double angle_between) {
        super(x, y);
        this.side1 = x;
        this.side2 = y;
        this.angle_between = angle_between;
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getAngle_between() {
        return angle_between;
    }

    public void setAngle_between(double angle_between) {
        this.angle_between = angle_between;
    }

    @Override
    public double area() {
        double result = side1 * side2 * Math.sin(Math.toRadians(angle_between)) * 0.5;
        return result;
    }
}
