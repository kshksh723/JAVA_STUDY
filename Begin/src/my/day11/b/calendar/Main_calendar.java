package my.day11.b.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main_calendar {

	public static void main(String[] args) {
		//자바에서 날짜를 나타낼때는 Date, Calendar를 사용한다
		
		//==1. Date 클래스르 사용하여 현재시각 나타내기 ===
		Date now = new Date(); //현재시각,  util로 써야 함 
		System.out.println(">> 현재시각 1 => " + now);
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //import 해야함
		
		
		System.out.println(">> 현재시각 2 => " + sdformat.format(now)); 
		/*
	       <참고>
	         날짜 타입 format         설명
	        --------------------------------------------------
	           %tF              날짜를 yyyy-mm-dd 형식으로 포맷해주는 것
	         %tT              날짜의 시각을 HH:MM:SS 형식으로 포맷해주는 것  
	           %tp              오전, 오후를 출력
	         %tA              요일명 출력
	         %tY              4자리 년도만 출력
	           %tB              월의 이름(January, February ...)출력
	         %tm              월의 01,02,03 ~~ 10,11,12 로 출력
	         %td              일수를 1~31 로 출력
	         %te              %td와 동일함
	         %tk              시간을 0~23 으로 출력
	           %tl              시간을 1~12 으로 출력
	         %tM              분을 00~59 으로 출력
	           %tS              초를 00~59 으로 출력
	         %tZ              타임존을 출력(한국은 KST로 나옴).
	      */
		String  currentTime = String.format("%tF %tp %tT %tA %tZ", now, now, now, now, now); //"%t" time 시간 
		System.out.println(">> 현재시각 3 => " + currentTime);
		
		
		/* == 입력한 날짜가 실제로 유효한 날짜 인지 검사하기 == */
		 sdformat = new SimpleDateFormat("yyyyMMdd");
		 
		 sdformat.setLenient(false);
		// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는 값일 경우 오류가 발생한다.
		// 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다. 
		 // == 문자열을 날짜 형태로 변환하기 ==/
		 
		 try {
			/// Date d = sdformat.parse("19980430");
			Date d = sdformat.parse("19980431");
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace(); //어디가 오류인지 알려줌, 하지만 볼 필요 없음!
			System.out.println(">> 달력에 존재하지 않는 값입니다 <<");
		} //날짜가 잘못되어지면 Unhandled exception type ParseException이렇게 됌 surrond ex 누르면 됌
		 // 올바르면 Mon Dec 06 00:00:00 GMT+09:00 1999 이렇게 나옴
		 
		 /* == 2개의 날짜를 비교하여 2개의 날짜가 같은 날짜인지 아니면 2개의 날짜중 어떤 날짜가 이전날짜 인지 이후날짜 인지 알아오기 == */
		 
	}

}
