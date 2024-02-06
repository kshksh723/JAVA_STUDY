package my.day15.b.overloading;

public class Child extends Parent {
	
	// field 
 //name field
	String name;
	
	
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
	
	
	
	// === 메소드의 오버로딩(overloading)  ==// , 중복 되었느냐를 확인해야함, 
	// => 메소드의 이름은 같지만 파라미터가 다르면 동일한 메소드로 보지 않는다
	// => 접근제한자 및 리턴타입은  메소드의 오버로딩(overloading)에 관여하지 않는다
	// info(int n)라고 적어야 빨간줄이 안나옴
	public void info(int n) {
		System.out.println();
	}
	// 변수는 관계없음, public void info(int a, String str) 이렇게 적으면 빨간줄 안나옴 이게 메소드의 오버로딩
	
	public void info(short m) {
		System.out.println("");
		
	}
	public void info(int a, String str) {
		System.out.println("");
	}
	
	public void info( String s, int b) {
		System.out.println("");
		
	}
	
	public String info( String st, int c, byte d) { 
		//return 타입은 관여하지 않는다, 중복되었는지 아닌지 오로지 메소드의 이름은 똑같지만 파라미터를 본다, 파라미터의 존재 유무를 알고 순서를 뒤바낀 것
		return "";
		
	}
	
	protected void info( int c, String st, byte d) {
		System.out.println("");
		
	}
	
	// 파라미터는 갯수가 다르던지, 타입이 달라지면 된다, info() 가 중복된 걸로 안봄 
	/* 	public void info(int b, String s) {
		System.out.println();
		
	}
	public void info(int a, String str) {
		System.out.println();
	}   -> 이름이 똑 같다고 하더라도 파라미터만 다르면 됨, 위치만 바꾸ㅣ면 된다*/
	/*public void info( String s, int b) {
		System.out.println();
		
	}
	
	public void info( String s, int b) {
		return "";
		
	}
	-> 중복되서 오류 남 */
	
	
	// 생성자의 오버로딩(overloading) ..
	public Child() {
	
	}
	
	public Child(String id, String passwd){
		this.id = id;
		this.passwd = passwd;
		
	} 
//	public Child(String id, String passwd, String name){
//		this(id, passwd); // <- 가 먼저 나와야함, 나중에나오면 오류 남
//		this.name = name;
//	} 
//	-> 놓침 이 부분..
	/*
	 * public Child(String id, String passwd, String name){ this(id, passwd); // <-
	 * 가 먼저 나와야함, 나중에나오면 오류 남 this.name = name; } => 생성자가 먼저 위치하지 않았으므로 오류 발생
	 */
	/*
	 * public Child() {
	 * 
	 * } -> 기본 생성자
	 */
	
	/*
	 * public Child() {
	 * 
	 * }
	 * 
	 * public Child(String id, String passwd, String name){
	 * 
	 * }
	 -> 파라미터가 달라서 빨간줄이 안뜸 
	 
	 *	public Child(String id, String passwd){
		
	} 
	public Child(String id, String passwd, String name){
		
	}  -> 중복아님
	 *
	 */
}
