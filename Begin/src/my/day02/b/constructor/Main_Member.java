package my.day02.b.constructor;

public class Main_Member {

	public static void main(String[] args) {

		Member hongkd_mbr = new Member(); // 인스턴스를 하나 만듦
		hongkd_mbr.userid = "hongkd";
		hongkd_mbr.passwd = "qwer1234";
		hongkd_mbr.name = "홍길동";
		hongkd_mbr.age = 30;
		hongkd_mbr.point = 100;

		hongkd_mbr.info_print();
		/////////////////////////////////

		Member eomjh_mbr = new Member("eomjh", "abcd", "엄정화", 27, 200);
		eomjh_mbr.info_print();

		/////////////////////////////////
		Member leess_mbr = new Member("leess", "qwer1234", "이순신", "leess@gmail.com", 29, 200);
		eomjh_mbr.info_print();

		///////////////////

		//// === 퀴즈 === ///
		/*
		 * leess_mbr.update("leess", "wxyz", "순신이", "sunsin@naver.com", 30, 500);
		 * 
		 * / >> 변경전 정보 <<< 1. 아이디 : leess 2. 비밀번호 : qwer1234 3. 성명 : 이순신 4. 이메일 :
		 * leess@gmail.com 5. 나이 : 29 6. 포인트 : 300
		 *
		 * >> 변경 후 정보 << 1. 아이디 : leess 2. 비밀번호 : wxyz 3. 성명 : 순신이 4. 이메일 :
		 * sunsin@naver.com 5. 나이 : 30 6. 포인트 : 500
		 * 
		 */

		/*
		 * // new Member("eomjh", "abcd", "엄정화", 27, 200); // 생성자를 안만들어서(정의가 안됌) 그래서 실행이
		 * 안됌 //
		 */

		// 퀴즈 //

	}

}
