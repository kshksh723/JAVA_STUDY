/*
 * package my.day11.b.calendar;
 * 
 * import java.text.ParseException; import java.text.SimpleDateFormat; import
 * java.util.Calendar; import java.util.Date;
 * 
 * public class Main_calendar {
 * 
 * public static void main(String[] args) { //자바에서 날짜를 나타낼때는 Date, Calendar를
 * 사용한다
 * 
 * //==1. Date 클래스르 사용하여 현재시각 나타내기 === Date now = new Date(); //현재시각, util로 써야 함
 * System.out.println(">> 현재시각 1 => " + now);
 * 
 * SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * //import 해야함
 * 
 * 
 * System.out.println(">> 현재시각 2 => " + sdformat.format(now));
 * 
 * <참고> 날짜 타입 format 설명 -------------------------------------------------- %tF
 * 날짜를 yyyy-mm-dd 형식으로 포맷해주는 것 %tT 날짜의 시각을 HH:MM:SS 형식으로 포맷해주는 것 %tp 오전, 오후를 출력
 * %tA 요일명 출력 %tY 4자리 년도만 출력 %tB 월의 이름(January, February ...)출력 %tm 월의 01,02,03
 * ~~ 10,11,12 로 출력 %td 일수를 1~31 로 출력 %te %td와 동일함 %tk 시간을 0~23 으로 출력 %tl 시간을
 * 1~12 으로 출력 %tM 분을 00~59 으로 출력 %tS 초를 00~59 으로 출력 %tZ 타임존을 출력(한국은 KST로 나옴).
 * 
 * String currentTime = String.format("%tF %tp %tT %tA %tZ", now, now, now, now,
 * now); //"%t" time 시간 System.out.println(">> 현재시각 3 => " + currentTime);
 * 
 * 
 * == 입력한 날짜가 실제로 유효한 날짜 인지 검사하기 == sdformat = new SimpleDateFormat("yyyyMMdd");
 * 
 * sdformat.setLenient(false); // false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는
 * 값일 경우 오류가 발생한다. // 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다. // ==
 * 문자열을 날짜 형태로 변환하기 ==/
 * 
 * try { /// Date d = sdformat.parse("19980430"); Date d =
 * sdformat.parse("19980431"); System.out.println(d); } catch (ParseException e)
 * { // TODO Auto-generated catch block // e.printStackTrace(); //어디가 오류인지 알려줌,
 * 하지만 볼 필요 없음! System.out.println(">> 달력에 존재하지 않는 값입니다 <<"); } //날짜가 잘못되어지면
 * Unhandled exception type ParseException이렇게 됌 surrond ex 누르면 됌 // 올바르면 Mon Dec
 * 06 00:00:00 GMT+09:00 1999 이렇게 나옴
 * 
 * == 2개의 날짜를 비교하여 2개의 날짜가 같은 날짜인지 아니면 2개의 날짜중 어떤 날짜가 이전날짜 인지 이후날짜 인지 알아오기 ==
 * 
 * try { String str_date1 = "19950704"; String str_date2 = "19980301";
 * 
 * Date date1 = sdformat.parse(str_date1); Date date2 =
 * sdformat.parse(str_date2);
 * 
 * // === 날짜 비교하기 === //
 * 
 * >> Date 클래스에서 제공하는 compareTo() 메소드 <<
 * 
 * date1.compareTo(date2);
 * 
 * date1 날짜와 date2 날짜가 동일하면 0을 반환하고, date1 날짜가 date2 날짜 보다 이전 날짜인 경우 0보다 작은 값을
 * 반환하고, 반대로 date1 날짜가 date2 날짜 보다 이후 날짜인 경우 경우 0보다 큰 값을 반환한다.
 * 
 * if(date1.compareTo(date2) < 0) { System.out.println(str_date1 +" 이 " +
 * str_date2 +" 보다 이전날짜 입니다."); } else if(date1.compareTo(date2) == 0) {
 * System.out.println(str_date1 +" 와 " + str_date2 +" 은 동일한 날짜 입니다."); } else {
 * System.out.println(str_date1 +" 이 " + str_date2 +" 보다 이후날짜 입니다."); } //
 * 19950704 이 19980301 보다 이전날짜 입니다.
 * 
 * >> Date 클래스에서 제공하는 Date.before 메소드 << date1.before(date2);
 * 
 * date1 날짜가 date2 날짜 보다 이전 날짜인 경우 true를 반환하고, 동일한 날짜이거나 이후 날짜인 경우 false를 반환한다.
 * 
 * >> Date 클래스에서 제공하는 Date.after 메소드 << date1.after(date2);
 * 
 * date1 날짜가 date2 날짜 보다 이후 날짜인 경우 true를 반환하고, 동일한 날짜이거나 이전 날짜인 경우 false를 반환한다.
 * 
 * if(date2.after(date1)) { System.out.println(str_date2 +" 이 " + str_date1
 * +" 보다 이후날짜 입니다."); } else { System.out.println(str_date2 +" 와 " + str_date1
 * +" 은 동일한 날짜 이거나 "+str_date2+" 이 "+str_date1+" 보다 이전날짜 입니다."); } // 19980301 이
 * 19950704 보다 이후날짜 입니다. if(date1.before(date2)) { System.out.println(str_date1
 * +" 이 " + str_date2 +" 보다 이전날짜 입니다."); } else { System.out.println(str_date1
 * +" 와 " + str_date2
 * +" 은 동일한 날짜 이거나 "+str_date1+" 이 "+str_date2+" 보다 이후날짜 입니다."); } // 19950704 이
 * 19980301 보다 이전날짜 입니다. }catch (ParseException e) { // e.printStackTrace()
 * System.out.println(">> 달력에 존재하지 않는 값입니다.<<"); }
 * System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
 * 
 * 
 * Date 클래스 보다 조금 더 향상시켜서 나온 것이 Calendar 클래스이다. 간단한 날짜표현에는 Date 클래스를 사용하는 것이 더
 * 나을 수 있으나, 두 개 날짜사이의 날짜연산을 할 경우에는 메소드기능이 더 많이 추가된 Calendar 클래스를 사용하는 것이 나을 수
 * 있다.
 * 
 * Calendar currentDate = Calendar.getInstance(); // calendar 를 import 눌러야함 //
 * 현재날짜와 시간을 얻어온다.
 * 
 * int year = currentDate.get(Calendar.YEAR);
 * 
 * int month = currentDate.get(Calendar.MONTH); // 0 ~ 11
 * 
 * String strMonth = month<10?"0"+month:String.valueOf(month);
 * 
 * 주의해야할 것은 get(Calendar.MONTH)로 얻어오는 값의 범위가 1~12가 아닌, 0~11 이라는 것이다. 그래서
 * get(Calendar.MONTH)로 얻어오는 값이 0이면 1월을 의미하고 11이면 12월을 의미한다. 그러므로 현재월을 표현하려면 (
 * currentDate.get(Calendar.MONTH) + 1 ) 으로 해야한다.
 * 
 * int month2 = currentDate.get(Calendar.MONTH)+1; // 1~12 String strMonth2 =
 * month2<10?"0"+month2:String.valueOf(month2);
 * 
 * System.out.println("올 해의 년도 : " + year); System.out.println("월(0~11, 0:1월): "
 * + strMonth); System.out.println("월(0~12, 1:1월): " + strMonth2);
 * 
 * System.out.println("이 해의 몇 째 주: " + currentDate.get(Calendar.WEEK_OF_YEAR));
 * // 이 해의 몇 째 주: 5 오늘 날짜부터 WEEK_OF_YEAR, 올해의 첫~다섯째주
 * System.out.println("이 달의 몇 째 주: " + currentDate.get(Calendar.WEEK_OF_MONTH));
 * 이 달의 몇 째 주: 5
 * 
 * 
 * 
 * // 아래의 Calendar.DATE 와 Calendar.DAY_OF_MONTH 는 같은 것이다. int day =
 * currentDate.get(Calendar.DATE); String strDay =
 * day<10?"0"+day:String.valueOf(day);
 * 
 * System.out.println("이 달의 몇 일: " + currentDate.get(Calendar.DATE));
 * System.out.println("이 달의 몇 일: " + currentDate.get(Calendar.DAY_OF_MONTH));
 * System.out.println("이 달의 몇 일: " + strDay); System.out.println("이 해의 몇 일: " +
 * currentDate.get(Calendar.DAY_OF_YEAR));
 * 
 * 
 * 
 * // 1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일
 * System.out.println("요일(1~7, 1:일요일): " +
 * currentDate.get(Calendar.DAY_OF_WEEK)); System.out.println("이 달의 몇 째 주: " +
 * currentDate.get(Calendar.DAY_OF_WEEK_IN_MONTH));
 * 
 * String dayname = ""; switch (currentDate.get(Calendar.DAY_OF_WEEK)) { case 1:
 * dayname = "일"; break;
 * 
 * case 2: dayname = "월"; break;
 * 
 * case 3: dayname = "화"; break;
 * 
 * case 4: dayname = "수"; break;
 * 
 * case 5: dayname = "목"; break;
 * 
 * case 6: dayname = "금"; break;
 * 
 * case 7: dayname = "토"; break; }
 * 
 * System.out.println("오늘은 " + dayname + "요일 입니다.");
 * System.out.println("오전_오후(0:오전, 1:오후): " + currentDate.get(Calendar.AM_PM));
 * System.out.println("시간(0~11): " + currentDate.get(Calendar.HOUR));
 * System.out.println("시간(0~23): " + currentDate.get(Calendar.HOUR_OF_DAY));
 * System.out.println("분(0~59): " + currentDate.get(Calendar.MINUTE));
 * System.out.println("초(0~59): " + currentDate.get(Calendar.SECOND));
 * System.out.println("1000분의 1초(0~999): " +
 * currentDate.get(Calendar.MILLISECOND));
 * 
 * 
 * // 천분의 1초를 시간으로 표시하기 위해 3600000으로 나누었다.(1시간 = 60 * 60초) --> 타임존
 * System.out.println("TimeZone(-12~+12): " +
 * (currentDate.get(Calendar.ZONE_OFFSET)/(60*60*1000)));
 * 
 * // 이 달의 마지막 일을 찾는다. System.out.println("이 달의 마지막 날: " +
 * currentDate.getActualMaximum(Calendar.DATE) );
 * 
 * System.out.println("");
 * 
 * String today = year+"-"+strMonth2+"-"+strDay; today += " " +
 * currentDate.get(Calendar.HOUR_OF_DAY) +":"+ (
 * currentDate.get(Calendar.MINUTE)<10?"0"+currentDate.get(Calendar.MINUTE):
 * currentDate.get(Calendar.MINUTE) ) +":"+ (
 * currentDate.get(Calendar.SECOND)<10?"0"+currentDate.get(Calendar.SECOND):
 * currentDate.get(Calendar.SECOND) );
 * 
 * switch (currentDate.get(Calendar.DAY_OF_WEEK)) { case 1: today += " 일요일";
 * break; case 2: today += " 월요일"; break; case 3: today += " 화요일"; break; case
 * 4: today += " 수요일"; break; case 5: today += " 목요일"; break; case 6: today +=
 * " 금요일"; break; case 7: today += " 토요일"; break; }
 * 
 * System.out.println("현재는 " + today + " 입니다.");
 * 
 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 * 
 * // === 어제시각 나타내기 === // currentDate.add(Calendar.DATE, -1);
 * System.out.println("어제시각 => " + dateFormat.format(currentDate.getTime()));
 * 
 * // === 현재시각 나타내기 === // currentDate = Calendar.getInstance();
 * System.out.println("현재시각 => " + dateFormat.format(currentDate.getTime())); //
 * 현재시각 => 2023-07-03 10:56:31
 * 
 * // === 내일시각 나타내기 === // currentDate.add(Calendar.DATE, 1);
 * System.out.println("내일시각 => " + dateFormat.format(currentDate.getTime()));
 * 
 * currentDate = Calendar.getInstance();
 * 
 * System.out.println("현재시각 : " + dateFormat.format(currentDate.getTime()));
 * 
 * System.out.println("현재시각으로부터 1개월 2일 3시간 4분 5초 후");
 * currentDate.add(Calendar.MONTH, 1); currentDate.add(Calendar.DATE, 2);
 * currentDate.add(Calendar.HOUR, 3); currentDate.add(Calendar.MINUTE, 4);
 * currentDate.add(Calendar.SECOND, 5);
 * System.out.println(dateFormat.format(currentDate.getTime())); //2024-03-02
 * 17:43:53
 * 
 * 
 * } }
 */
package my.day11.b.calendar.copy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main_calendar {

	public static void main(String[] args) {
		
		// 자바에서 날짜를 나타낼때는 Date, Calendar 를 사용한다.
		
		// === 1. Date 클래스를 사용하여 현재시각 나타내기 ===
		Date now = new Date(); // 현재시각 
		
		System.out.println(">> 현재시각 1 => " + now);
		// >> 현재시각 1 => Tue Jan 30 12:27:29 GMT+09:00 2024
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println(">> 현재시각 2 => " + sdformat.format(now) );
		// >> 현재시각 2 => 2024-01-30 12:29:14
		
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
		String currentTime = String.format("%tF %tp %tT %tA %tZ", now, now, now, now, now); 
		System.out.println(">> 현재시각 3 => " + currentTime);
		// >> 현재시각 3 => 2024-01-30 오후 12:34:38 화요일 GMT+09:00
		
		
		/* == 입력한 날짜가 실제로 유효한 날짜 인지 검사하기 == */
		sdformat = new SimpleDateFormat("yyyyMMdd");
		
		sdformat.setLenient(false);
		// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는 값일 경우 오류가 발생한다.
		// 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다. 
				
		// === 문자열을 날짜 형태로 변환하기 === //
		try {
		 //	 Date d = sdformat.parse("19980430");
		 	 Date d = sdformat.parse("19980431");
			 System.out.println(d);
		 //  Thu Apr 30 00:00:00 GMT+09:00 1998	 
		} catch (ParseException e) {
		 //	e.printStackTrace();
			System.out.println(">> 달력에 존재하지 않는 값입니다. <<");
		}
		

		/* == 2개의 날짜를 비교하여 2개의 날짜가 같은 날짜인지 아니면 2개의 날짜중 어떤 날짜가 이전날짜 인지 이후날짜 인지 알아오기 == */ 
		try {
			String str_date1 = "19950704";
			String str_date2 = "19980301";
		
			Date date1 = sdformat.parse(str_date1);
			Date date2 = sdformat.parse(str_date2);
			
			// === 날짜 비교하기 === //
			/* 
			   >> Date 클래스에서 제공하는 compareTo() 메소드 <<
			   
			      date1.compareTo(date2);
			   
	              date1 날짜와 date2 날짜가 동일하면 0을 반환하고,
	              date1 날짜가 date2 날짜 보다 이전 날짜인 경우 0보다 작은 값을 반환하고, 
	              반대로 date1 날짜가 date2 날짜 보다 이후 날짜인 경우 경우 0보다 큰 값을 반환한다. 
			*/
			 if(date1.compareTo(date2) < 0) {
				 System.out.println(str_date1 +" 이 " + str_date2 +" 보다 이전날짜 입니다.");  
			 }
			 else if(date1.compareTo(date2) == 0) {
				 System.out.println(str_date1 +" 와 " + str_date2 +" 은 동일한 날짜 입니다."); 
			 }
			 else {
				 System.out.println(str_date1 +" 이 " + str_date2 +" 보다 이후날짜 입니다."); 
			 }
			 // 19950704 이 19980301 보다 이전날짜 입니다.
			 
			 /*
			   >> Date 클래스에서 제공하는 Date.before 메소드 <<
	           date1.before(date2);
	         
	           date1 날짜가 date2 날짜 보다 이전 날짜인 경우 true를 반환하고, 
	           동일한 날짜이거나 이후 날짜인 경우 false를 반환한다.
			 */
			 if(date1.before(date2)) {
				 System.out.println(str_date1 +" 이 " + str_date2 +" 보다 이전날짜 입니다.");  
			 }
			 else {
				 System.out.println(str_date1 +" 와 " + str_date2 +" 은 동일한 날짜 이거나 "+str_date1+" 이 "+str_date2+" 보다 이후날짜 입니다."); 
			 }
			 // 19950704 이 19980301 보다 이전날짜 입니다.
			 
			 
			 /*
			   >> Date 클래스에서 제공하는 Date.after 메소드 <<
	              date1.after(date2);
	             
	              date1 날짜가 date2 날짜 보다 이후 날짜인 경우 true를 반환하고, 
	              동일한 날짜이거나 이전 날짜인 경우 false를 반환한다.
			 */
			 if(date2.after(date1)) {
				 System.out.println(str_date2 +" 이 " + str_date1 +" 보다 이후날짜 입니다.");  
			 }
			 else {
				 System.out.println(str_date2 +" 와 " + str_date1 +" 은 동일한 날짜 이거나 "+str_date2+" 이 "+str_date1+" 보다 이전날짜 입니다."); 
			 }
			 // 19980301 이 19950704 보다 이후날짜 입니다.
			 
		} catch (ParseException e) {
		 //	e.printStackTrace();
			System.out.println(">> 달력에 존재하지 않는 값입니다. <<");
		}
		
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");	
		
		/* 
		   Date 클래스 보다 조금 더 향상시켜서 나온 것이 Calendar 클래스이다.
		   간단한 날짜표현에는 Date 클래스를 사용하는 것이 더 나을 수 있으나,
		   두 개 날짜사이의 날짜연산을 할 경우에는 메소드기능이 더 많이 추가된 
		   Calendar 클래스를 사용하는 것이 나을 수 있다.
		*/   
		Calendar currentDate = Calendar.getInstance(); 
		// 현재날짜와 시간을 얻어온다.
		
		int year = currentDate.get(Calendar.YEAR);
		
		int month = currentDate.get(Calendar.MONTH); // 0 ~ 11 
		
		String strMonth = month<10?"0"+month:String.valueOf(month);
		/* 
		   주의해야할 것은 get(Calendar.MONTH)로 얻어오는 값의 범위가 1~12가 아닌, 0~11 이라는 것이다. 
		   그래서 get(Calendar.MONTH)로 얻어오는 값이 0이면 1월을 의미하고 11이면 12월을 의미한다.
		   그러므로 현재월을 표현하려면 ( currentDate.get(Calendar.MONTH) + 1 ) 으로 해야한다. 
		   괄호없이 currentDate.get(Calendar.MONTH) + 1로 하면 이상한 값이 나온다. (괄호유무조심!!) 
		*/
		
		int month2 = currentDate.get(Calendar.MONTH)+1; // 1 ~ 12 
		String strMonth2 = month2<10?"0"+month2:String.valueOf(month2);
		
		System.out.println("올 해의 년도 : " + year);
	    System.out.println("월(0~11, 0:1월): " + strMonth);
	    System.out.println("월(1~12, 1:1월): " + strMonth2);
		
	    System.out.println("이 해의 몇 째 주: " + currentDate.get(Calendar.WEEK_OF_YEAR));
	    System.out.println("이 달의 몇 째 주: " + currentDate.get(Calendar.WEEK_OF_MONTH));
	 
	     // 아래의 Calendar.DATE 와 Calendar.DAY_OF_MONTH 는 같은 것이다.
		 int day = currentDate.get(Calendar.DATE);
		 String strDay = day<10?"0"+day:String.valueOf(day);
		 
		 System.out.println("이 달의 몇 일: " + currentDate.get(Calendar.DATE));
		 System.out.println("이 달의 몇 일: " + currentDate.get(Calendar.DAY_OF_MONTH));
		 System.out.println("이 달의 몇 일: " + strDay);
		 System.out.println("이 해의 몇 일: " + currentDate.get(Calendar.DAY_OF_YEAR));
	    
		 // 1:일요일, 2:월요일, 3:화요일, 4:수요일, 5:목요일, 6:금요일, 7:토요일
		 System.out.println("요일(1~7, 1:일요일): " + currentDate.get(Calendar.DAY_OF_WEEK)); 
		 System.out.println("이 달의 몇 째 주: " + currentDate.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		 
		 String dayname = "";
		 switch (currentDate.get(Calendar.DAY_OF_WEEK)) {
				case 1:
					dayname = "일";
					break;
		
				case 2:
					dayname = "월";
					break;
					
				case 3:
					dayname = "화";
					break;	
					
				case 4:
					dayname = "수";
					break;	
					
				case 5:
					dayname = "목";
					break;	
					
				case 6:
					dayname = "금";
					break;
					
				case 7:
					dayname = "토";
					break;	
			}
		 
		 System.out.println("오늘은 " + dayname + "요일 입니다.");
	     // 오늘은 화요일 입니다.
		 
		 System.out.println("오전_오후(0:오전, 1:오후): " + currentDate.get(Calendar.AM_PM));
		 System.out.println("시간(0~11): " + currentDate.get(Calendar.HOUR));
		 System.out.println("시간(0~23): " + currentDate.get(Calendar.HOUR_OF_DAY));
		 System.out.println("분(0~59): " + currentDate.get(Calendar.MINUTE));
		 System.out.println("초(0~59): " + currentDate.get(Calendar.SECOND));
		 System.out.println("1000분의 1초(0~999): " + currentDate.get(Calendar.MILLISECOND));
		 
		// 천분의 1초를 시간으로 표시하기 위해 3600000으로 나누었다.(1시간 = 60 * 60초)
		 System.out.println("TimeZone(-12~+12): " + (currentDate.get(Calendar.ZONE_OFFSET)/(60*60*1000))); 
		 
		 // 이 달의 마지막 일을 찾는다.
		 System.out.println("이 달의 마지막 날: " + currentDate.getActualMaximum(Calendar.DATE) ); 
		
		 System.out.println("");
			
		 String today = year+"-"+strMonth2+"-"+strDay;
		 today += " " + currentDate.get(Calendar.HOUR_OF_DAY) +":"+ ( currentDate.get(Calendar.MINUTE)<10?"0"+currentDate.get(Calendar.MINUTE):currentDate.get(Calendar.MINUTE) ) +":"+ ( currentDate.get(Calendar.SECOND)<10?"0"+currentDate.get(Calendar.SECOND):currentDate.get(Calendar.SECOND) );         
		 
		 switch (currentDate.get(Calendar.DAY_OF_WEEK)) {
				case 1:
					today += " 일요일";
					break;
				case 2:
					today += " 월요일";
					break;	
				case 3:
					today += " 화요일";
					break;
				case 4:
					today += " 수요일";
					break;
				case 5:
					today += " 목요일";
					break;
				case 6:
					today += " 금요일";
					break;	
				case 7:
					today += " 토요일";
					break;
			}
		 
		 System.out.println("현재는 " + today + " 입니다.");
		 // 현재는 2024-01-30 14:30:16 화요일 입니다.
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 // === 어제시각 나타내기 === //
		 currentDate.add(Calendar.DATE, -1); 
		 System.out.println("어제시각 => " + dateFormat.format(currentDate.getTime()));
		 // 어제시각 => 2024-01-29 14:33:15	 
			 
		 // === 현재시각 나타내기 === //
		 currentDate = Calendar.getInstance(); 
		 System.out.println("현재시각 => " + dateFormat.format(currentDate.getTime()));
		 // 현재시각 => 2024-01-30 14:34:07
		 
		 // === 내일시각 나타내기 === //
		 currentDate.add(Calendar.DATE, 1); 
		 System.out.println("내일시각 => " + dateFormat.format(currentDate.getTime()));
		 // 내일시각 => 2024-01-31 14:34:51
		 
		 currentDate = Calendar.getInstance();
			
		 System.out.println("현재시각 : " + dateFormat.format(currentDate.getTime())); 
		 // 현재시각 : 2024-01-30 14:38:16
		 
		 System.out.println("현재시각으로 부터 1개월 2일 3시간 4분 5초 후");
		 currentDate.add(Calendar.MONTH, 1);
		 currentDate.add(Calendar.DATE, 2);
		 currentDate.add(Calendar.HOUR, 3);
		 currentDate.add(Calendar.MINUTE, 4);
		 currentDate.add(Calendar.SECOND, 5);
		 System.out.println(dateFormat.format(currentDate.getTime())); 
		 // 2024-03-02 17:42:21
		 
	}// end of main()-----------------------------

}
