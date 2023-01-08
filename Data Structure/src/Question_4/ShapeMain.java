package Question_4;

public class ShapeMain {
    public static void main(String[] args) {
        Rectangle r = new Rectangle(3,10);
        Ellipse e = new Ellipse(3,3);
        Triangle t = new Triangle(4,4,90);
        System.out.println("Rectangle area is:  "+ r.area());
        System.out.println("Ellipse area is:  " +e.area());
        System.out.println("Triangle area is:  "+t.area());



    }



}
