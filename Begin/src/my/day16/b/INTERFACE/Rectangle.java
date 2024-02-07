package my.day16.b.INTERFACE;

public class Rectangle implements Figure {
//Rectangle이라는 클래스는 Figure라는 인터페이스를 implements(구현)한다는 말이다
	
	@Override
	public double area(double x, double y) {
		
		return x*y;
	}
	@Override
	public double circle_area(double r) {
		
		return PI*r*r;
	}
	@Override
	public double circle_area(double x, double y) {
		
		return 0;
	}
	

}
