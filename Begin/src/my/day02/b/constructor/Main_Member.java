package my.day02.b.constructor;

public class Main_Member {

	public static void main(String[] args) {
	
		Member hongkd_mbr =  new Member(); //인스턴스를 하나 만듦
		hongkd_mbr.userid = "hongkd";
		hongkd_mbr.passwd = "qwer1234";
		hongkd_mbr.name =  "홍길동";
		hongkd_mbr.age = 30;
		hongkd_mbr.point = 100;

		hongkd_mbr.info_print();
		/////////////////////////////////
		
		Member eomjh_mbr = new Member("eomjh", "abcd", "엄정화", 27, 200);
		eomjh_mbr.info_print(); 
		/*
		 * // new Member("eomjh", "abcd", "엄정화", 27, 200); // 생성자를 안만들어서(정의가 안됌) 그래서 실행이
		 * 안됌 //
		 */		
		
		
	}

}
