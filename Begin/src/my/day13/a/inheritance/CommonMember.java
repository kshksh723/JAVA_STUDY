package my.day13.a.inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonMember {
//공통 회원
	
		// Gujikja 클래스와 company 클래스에서 공통으로 사용되어지는 field(attribute, 속성) 생성하기
		 String id;  // 아이디
		 String passwd;  // 비밀번호
		 String name;    // 성명
		 String register_day;  // 가입일자
		
		 
		 // Gujikja 및 Company 클래스의 부모 클래스인 CommonMember 클래스의 기본 생성자 
		 public CommonMember(){
		//	 System.out.println("== 부모클래스인 CommonMember 클래스의 기본 생성자 호출 == ");
			 Date now = new Date(); // 현재시각
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				register_day = df.format(now);
		 }
		 String parent_test() {
			return "부모 클래스를 만들었어요 그냥 연습이에요!!";
		 }
	}

