package my.day15.a.overriding;

public class Child extends Parent {
	
	// 자식에서 바꾼다 -> 메소드의 오버라이딩(Overriding)
	// 리모델링이라고 생각
	// 메소드의 재정의 == 메소드의 오버라이딩 
	// 메소드의 오버라이딩(overriding) 시 접근제한자는 부모클래스에서 정의해둔 메소드의 접근제한자와 같거나 또는 허용이 더 큰 것을 해야 한다
	// 또한 메소드의 오버라이딩(overriding) 시 껍데기 (String info() )(리턴타입 메소드명 파라미터)는 똑같아야 한다
	/*
	 * { return " 아이디 : " + this.id + " \n " + "비밀번호 : " + super.passwd;
	 * 
	 * } -> 알맹이(내용몰)은 새롭게 정의해서 만들면 된다
	 */
	// 면접 볼 때 오버로딩, 오버라이딩을 물어봄...,.
	//
	@Override	//@를 애노테이션(어노테이션)이라고 부른다
	public String info() {
		return " 아이디 : " + this.id + " \n "
				+ "비밀번호 : " + super.passwd;
		
	}
	

}
