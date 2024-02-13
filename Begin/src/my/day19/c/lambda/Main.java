package my.day19.c.lambda;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("== 람다식(Lambda)을 사용하지 않은 것[무명(익명)클래스를 사용한 것");

		Functional_Area_1 area = new Functional_Area_1() {

			@Override
			public void area(double x, double y, int type) {
				// TODO Auto-generated method stub
				
					
					switch (type) {
						case 1:
							System.out.println("가로 " + x + ", 세로 " + y +"인 사각형 면적은 :" + (x*y));
							break;
						case 2:
							System.out.println("밑변 " + x + ", 높이 " + y +"인 삼각형 면적은 :" + (x*y*0.5));
					
							break;
						case 3:
							System.out.println("장축의 반지름 " + x + ", 단축의 반지름 " + y + "인 타원의 면적은 : " + (3.142*x*y));
							break;
							
							default:
								System.out.println("세번째 파라미터는 1 또는 2또는 3만 가능합니다");
								break;
					
					
					} // end of switch (type)
			}
			
		}; // end of
	}

}
