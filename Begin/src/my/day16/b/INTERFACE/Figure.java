package my.day16.b.INTERFACE;
//=== 인터페이스(interface)란? === // 암기 !!!
// 오로지 미완성메소드(추상메소드)와 field는 final변수로만 이루어진 것을 말한다.
// 그리고 미완성메소드(추상메소드)와 field의 접근제한자는 public 으로만 되어진다.
// 미완성메소드(추상메소드) 생성시 public abstract 은 생략되어져 있다.
// field 생성시 public final 이 생략되어져 있다.



public interface Figure { //메소드를 강제 구현할 때 interface를 씀 
	// == field ==
	// public final double pi = 3.141592;
	// 또는
	double PI = 3.141592;	// Public final이 생략되어진 것이다
	
	// == method == 
		double area(double x, double y);  //Abstract methods do not specify a body -> 미완성 메소드, {} 를 못 씀
	// 즉, public abstract double area(double x, double y); 이다 // public abstract이 생략되어진 것이다
		
		
		//정원의 면적
		double circle_area(double r);// public abstract이 생략되어진 것이다
		// 즉, public abstract double area(double x, double y);
		// 메소드의 오버로딩(overloading)
		// 타원의 면적
		double circle_area(double x, double y);// 메소드의 오버로딩(overloading)
		// 파라미터가 다름
}
