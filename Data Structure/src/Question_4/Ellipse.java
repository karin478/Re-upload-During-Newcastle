package Question_4;

public class Ellipse extends Shape{
    private double major_axis;
    private double minor_axis;

    public Ellipse(double x, double y) {
        super(x, y);
        this.major_axis = x;
        this.minor_axis = y;
    }

    public double getMajor_axis() {
        return major_axis;
    }

    public void setMajor_axis(double major_axis) {
        this.major_axis = major_axis;
    }

    public double getMinor_axis() {
        return minor_axis;
    }

    public void setMinor_axis(double minor_axis) {
        this.minor_axis = minor_axis;
    }

    @Override
    public double area() {
        double result = Math.PI * major_axis * minor_axis;
        return result;
    }
}
