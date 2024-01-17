package my.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {
	
	// === 현재 시각을 출력해주는  static 메소드를 생성한다. === //
	public static void current_time_print() {
		// static이 없으면 instance 메소드
		
		Date now = new Date(); // 현재 시각
		
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("== 현재시각 : " + sdfmt.format(now));
		
		
	}
}
