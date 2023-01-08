package Question_4;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(double x, double y) {
        super(x, y);
        this.width = x;
        this.height = y;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double area() {
        double result = width * height;
        return result;
    }
}
