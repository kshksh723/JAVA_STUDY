package member.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
PK_USER_ID    NOT NULL VARCHAR2(15)  
USER_EMAIL    NOT NULL VARCHAR2(50)  
USER_PASSWD   NOT NULL VARCHAR2(20)  
USER_NAME     NOT NULL NVARCHAR2(10) 
USER_JUBUN    NOT NULL VARCHAR2(14)  
USER_TEL      NOT NULL VARCHAR2(13)  
RESUME_CODE            VARCHAR2(10)  
LOCATION_CODE NOT NULL VARCHAR2(10)  

 */

public class MemberDTO {
	
	
	
	// insert 용
	private String PK_USER_ID; 		// 아이디
	private String USER_EMAIL;		// 이메일
	private String USER_PASSWD;		// 비밀번호
	private String USER_NAME;		// 이름
	private String USER_JUBUN;		// 주민등록번호
	private String USER_TEL; 		// 연락처
	private String RESUME_CODE; 	// 이력서코드
	private String LOCATION_CODE;   // 지역코드
	private String USER_STATUS;     // 회원상태(탈퇴,유지)  

	/////////////////////////////////////////////////////////////////////////
	// select 용 
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////

	
	public String getPK_USER_ID() {
		return PK_USER_ID;
	}
	
	public void setPK_USER_ID(String pK_USER_ID) {
	     
        if(pK_USER_ID == null || pK_USER_ID.isBlank() ) {
           System.out.println("[경고] 아이디는 공백이 아닌 글자로 입력하셔야 합니다.\n");
        }
        else {
           
           Pattern p =Pattern.compile("^[A-Za-z0-9]{5,20}$");
   
           Matcher m = p.matcher(pK_USER_ID);
           
           
           if(m.matches()) {
              this.PK_USER_ID = pK_USER_ID;
        
           }
           else {
              System.out.println("[경고] 아이디는 영문 또는 숫자로만 이루어진 글자길이는 5~20글자만 가능합니다.\n");
           }  
        }     
	}
	
	
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		if(uSER_EMAIL == null || uSER_EMAIL.isBlank() ) {
	           System.out.println("[경고] 이메일은 공백이 아닌 글자로 입력하셔야 합니다.\n");
	        }
	        else {
	           
	           Pattern p =Pattern.compile("^[A-Za-z0-9]{5,15}$");
	   
	           Matcher m = p.matcher(uSER_EMAIL);
	           
	           
	           if(m.matches()) {
	              this.USER_EMAIL = uSER_EMAIL;
	        
	           }
	           else {
	              System.out.println("[경고] 이메일은 영문 또는 숫자로만 이루어진 글자길이는 5~15글자만 가능합니다.\n");
	           }  
	        }     
	}
	public String getUSER_PASSWD() {
		return USER_PASSWD;
	}
	public void setUSER_PASSWD(String uSER_PASSWD) {
		if(isCheckPasswd(uSER_PASSWD)) {
	         USER_PASSWD = uSER_PASSWD;
	      }
	      else {
	         USER_PASSWD = null;
	      }
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		if(uSER_NAME == null || uSER_NAME.isBlank() ) {
	           System.out.println("[경고] 이름은 공백이 아닌 글자로 입력하셔야 합니다.\n");
	        }
	        else {
	           
	           Pattern p =Pattern.compile("^[가-힣]{2,6}$");
	   
	           Matcher m = p.matcher(uSER_NAME);
	           
	           
	           if(m.matches()) {
	              this.USER_NAME = uSER_NAME;
	        
	           }
	           else {
	              System.out.println("[경고] 이름은 한글로만 이루어져야하고 글자길이는 2~6글자만 가능합니다.\n");
	           }  
	        }     
	}
	public String getUSER_JUBUN() {
		return USER_JUBUN;
	}
	public void setUSER_JUBUN(String uSER_JUBUN) {
        String jubun = "";
        if(uSER_JUBUN.length() == 13) {
          if(uSER_JUBUN.substring(6,7).equals("1")||uSER_JUBUN.substring(6,7).equals("2")) {
                 jubun = "19" + uSER_JUBUN.substring(0,6); // 19991210
           }
           else if(uSER_JUBUN.substring(6,7).equals("3")||uSER_JUBUN.substring(6,7).equals("4")) {
              jubun = "20" + uSER_JUBUN.substring(0,6);
              
           }
           else {
              USER_JUBUN = null;
           }
          
          
          if(!jubun.isBlank()) {

                 SimpleDateFormat sdformat = new SimpleDateFormat("yyyymmdd");
                 sdformat.setLenient(false);
                 
                 try {
                    Date d = sdformat.parse(jubun);
                    
                    
                    Date now = new Date(); 
                     String str_now = sdformat.format(now); 
                     now = sdformat.parse(str_now); 
                     if(d.compareTo(now) > 0) {
                        System.out.println(" 생일이 현재보다 미래입니다 다시 입력하세요.");
                        USER_JUBUN = null;
                     }
                     else {
                        USER_JUBUN = uSER_JUBUN.substring(0,6) + "-" + uSER_JUBUN.substring(6);
                     }
                    
                 }catch(ParseException e){
                    System.out.println(">> 달력에 존재하지 않는 값입니다. << ");
                    USER_JUBUN = null;
                 }
          }
          
        }
        else {
           USER_JUBUN = null;
        }

	}
	public String getUSER_TEL() {
		return USER_TEL;
	}
	public void setUSER_TEL(String uSER_TEL) {
		USER_TEL = uSER_TEL;
	}
	public String getRESUME_CODE() {
		return RESUME_CODE;
	}
	public void setRESUME_CODE(String rESUME_CODE) {
		RESUME_CODE = rESUME_CODE;
	}
	public String getLOCATION_CODE() {
		return LOCATION_CODE;
	}
	public void setLOCATION_CODE(String lOCATION_CODE) {
		LOCATION_CODE = lOCATION_CODE;
	}
	
	
	
	
	
	
	
	
	
	public static boolean isCheckPasswd(String passwd) {
	      
	      if(passwd == null) 
	         return false;

	      int length = passwd.length();
	      if(length < 8 || length > 16) {
	         return false;
	      }

	      boolean flag_upper   = false;    //  영문대문자표식을 위한 용도
	      boolean flag_lower   = false;    //  영문소문자표식을 위한 용도
	      boolean flag_number  = false;    //  숫자표식을 위한 용도
	      boolean flag_special = false;    //  특수문자표식을 위한 용도
	      
	      for(int i=0; i<length; i++) {
	         
	         char ch = passwd.charAt(i);
	         if('가' <= ch && ch <= '힣') {
	            return false;
	         }
	         
	         if(Character.isUpperCase(ch))
	            flag_upper = true;
	         
	         else if(Character.isLowerCase(ch))
	            flag_lower = true;
	         
	         else if(Character.isDigit(ch))
	            flag_number = true;
	         
	         else
	            flag_special = true;
	         
	      }//end of for-----------------------
	      
	      return flag_upper && flag_lower  && flag_number && flag_special;

	      
	         
	   }//end of public static boolean isCheckPasswd(String passwd)

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	public void setUSER_STATUS(String uSER_STATUS) {
		USER_STATUS = uSER_STATUS;
	}
	
	
	
}
