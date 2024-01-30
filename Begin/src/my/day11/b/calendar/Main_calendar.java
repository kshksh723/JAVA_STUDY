package my.day11.b.calendar;

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
		
	}

}
