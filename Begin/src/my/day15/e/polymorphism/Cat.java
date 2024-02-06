package my.day15.e.polymorphism;

public class Cat extends Animal{
	
	// cat만 가지는 field를 정의 (추상화)
	private String color;
// cat만 가지는 method를 정의 (추상화)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		if(color != null && !color.isBlank()) {
		this.color = color;
	}
}
	
	// 메소드의 오버라이딩(메소드의 재정의) 
		@Override
		protected void view_info() {
			System.out.println("== 고양이 정보 ==== \n"
					+ "1. 성명 : " + super.getName() + "\n"
					+ "2. 생년 : " + super.getBirth_year() +"년 \n"
					+ "3. 피부색 " + color + "\n");
	
		}
		@Override
		protected void action() {
			System.out.println("[고양이는 점프를 합니다.!!!]");
}
}