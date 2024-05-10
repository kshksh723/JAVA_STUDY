<<<<<<< HEAD
package company.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
PK_COMPANY_CODE      NOT NULL VARCHAR2(10)  
COMPANY_NAME         NOT NULL VARCHAR2(30)  
SALES                         VARCHAR2(20)  
NUMBER_OF_EMPLOYEE   NOT NULL NUMBER(5)     
HOMEPAGE                      VARCHAR2(300) 
ESTABLISHMENT_DATE   NOT NULL DATE          
BOSS_NAME            NOT NULL NVARCHAR2(5)  
FK_BUSS_TYPE_CODE    NOT NULL VARCHAR2(10)  
COMPANY_ADDRESS               VARCHAR2(100) 
FK_COMPANY_SIZE_CODE          VARCHAR2(10)  
 */


public class CompanyDTO {

	
	
	// insert 용
   private String   PK_COMPANY_CODE;       // 사업자등록번호
   private String   COMPANY_PASSWD;        // 회사비밀번호
   private String   COMPANY_NAME;          // 기업명
   private String   COMPANY_EMAIL;         // 회사이메일
   private String   COMPANY_TEL;           // 회사전화번호
   private String   SALES;                 // 매출액
   private String   NUMBER_OF_EMPLOYEE;    // 사원수
   private String   HOMEPAGE;              // 홈페이지
   private String   ESTABLISHMENT_DATE;    // 설립일      
   private String   BOSS_NAME;             // 대표자명
   private String   FK_BUSS_TYPE_CODE;     // 업종코드
   private String   COMPANY_ADDRESS;       // 기업주소
   private String   FK_COMPANY_SIZE_CODE;  // 기업규모코드

	
	/////////////////////////////////////////////////////////////////////////
	// select 용
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////


	public String getPK_COMPANY_CODE() {
		return PK_COMPANY_CODE;
	}


	public void setPK_COMPANY_CODE(String pK_COMPANY_CODE) {
		if(pK_COMPANY_CODE == null || pK_COMPANY_CODE.isBlank() || pK_COMPANY_CODE.length() != 10) {
	           System.out.println("<경고> 아이디는 공백이 아닌 숫자 10개를 입력하세요. \n");
	        }
	        else if(Pattern.matches("^[0-9]*$", pK_COMPANY_CODE)){
	          
	           this.PK_COMPANY_CODE= pK_COMPANY_CODE;

	        }     
	        else {
	        	pK_COMPANY_CODE = null;
	        	System.out.println("<경고> 아이디는 공백이 아닌 숫자 10개를 입력하세요. \n");
	        }
	}


	public String getCOMPANY_PASSWD() {
		return COMPANY_PASSWD;
	}


	public void setCOMPANY_PASSWD(String cOMPANY_PASSWD) {
		 // 8 ~ 16 글자의 영문 대소문자, 숫자, 특수문자만 가능
	      
	      if(isCheckPasswd(cOMPANY_PASSWD)) {
	         COMPANY_PASSWD = cOMPANY_PASSWD;
	      }
	      else {
	         cOMPANY_PASSWD = null;
	         System.out.println("<경고> 비밀번호는 공백을 제외한 8~16자리의 영문 대소문자, 숫자, 특수문자를 모두 사용하여 입력하세요.\n");
	      }
	}


	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}


	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}


	public String getCOMPANY_EMAIL() {
		return COMPANY_EMAIL;
	}


	public void setCOMPANY_EMAIL(String cOMPANY_EMAIL) {
		COMPANY_EMAIL = cOMPANY_EMAIL;
	}


	public String getCOMPANY_TEL() {
		return COMPANY_TEL;
	}


	public void setCOMPANY_TEL(String cOMPANY_TEL) {
		COMPANY_TEL = cOMPANY_TEL;
	}


	public String getSALES() {
		return SALES;
	}


	public void setSALES(String sALES) {
		SALES = sALES;
	}


	public String getNUMBER_OF_EMPLOYEE() {
		return NUMBER_OF_EMPLOYEE;
	}


	public void setNUMBER_OF_EMPLOYEE(String nUMBER_OF_EMPLOYEE) {
		NUMBER_OF_EMPLOYEE = nUMBER_OF_EMPLOYEE;
	}


	public String getHOMEPAGE() {
		return HOMEPAGE;
	}


	public void setHOMEPAGE(String hOMEPAGE) {
		HOMEPAGE = hOMEPAGE;
	}


	public String getESTABLISHMENT_DATE() {
		return ESTABLISHMENT_DATE;
	}


	public void setESTABLISHMENT_DATE(String eSTABLISHMENT_DATE) {
		if(!eSTABLISHMENT_DATE.isBlank()) {

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyymmdd");
            sdformat.setLenient(false);
            
            try {
               Date d = sdformat.parse(eSTABLISHMENT_DATE);

               Date now = new Date(); 
                String str_now = sdformat.format(now); 
                now = sdformat.parse(str_now); 
                
                if(d.compareTo(now) > 0) {
                   ESTABLISHMENT_DATE = null;
                   
                }
                else {
                   ESTABLISHMENT_DATE = eSTABLISHMENT_DATE;
                }
               
            }catch(ParseException e){
               System.out.println("<경고> 달력에 존재하지 않는 값입니다. ");
            }
           
         }
       
       else {
          ESTABLISHMENT_DATE = null;
       }
	}


	public String getBOSS_NAME() {
		return BOSS_NAME;
	}


	public void setBOSS_NAME(String bOSS_NAME) {
		if(bOSS_NAME == null || bOSS_NAME.isBlank() ) {
	           System.out.println("<경고> 대표자명은 공백이 아닌 글자로 입력하셔야 합니다.\n");
	        }
	        else {
	           
	           Pattern p =Pattern.compile("^[가-힣]{2,6}$");
	   
	           Matcher m = p.matcher(bOSS_NAME);
	           
	           
	           if(m.matches()) {
	              this.BOSS_NAME = bOSS_NAME;
	        
	           }
	           else {
	              System.out.println("<경고> 대표자명은 한글로만 이루어져야하고 글자길이는 2~6글자만 가능합니다.");
	              BOSS_NAME = null;
	           }  
	        }
	}


	public String getFK_BUSS_TYPE_CODE() {
		return FK_BUSS_TYPE_CODE;
	}


	public void setFK_BUSS_TYPE_CODE(String fK_BUSS_TYPE_CODE) {
		FK_BUSS_TYPE_CODE = fK_BUSS_TYPE_CODE;
	}


	public String getCOMPANY_ADDRESS() {
		return COMPANY_ADDRESS;
	}


	public void setCOMPANY_ADDRESS(String cOMPANY_ADDRESS) {
		COMPANY_ADDRESS = cOMPANY_ADDRESS;
	}


	public String getFK_COMPANY_SIZE_CODE() {
		return FK_COMPANY_SIZE_CODE;
	}


	public void setFK_COMPANY_SIZE_CODE(String fK_COMPANY_SIZE_CODE) {
		FK_COMPANY_SIZE_CODE = fK_COMPANY_SIZE_CODE;
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
	
	
}
=======
package company.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
PK_COMPANY_CODE      NOT NULL VARCHAR2(10)  
COMPANY_NAME         NOT NULL VARCHAR2(30)  
SALES                         VARCHAR2(20)  
NUMBER_OF_EMPLOYEE   NOT NULL NUMBER(5)     
HOMEPAGE                      VARCHAR2(300) 
ESTABLISHMENT_DATE   NOT NULL DATE          
BOSS_NAME            NOT NULL NVARCHAR2(5)  
FK_BUSS_TYPE_CODE    NOT NULL VARCHAR2(10)  
COMPANY_ADDRESS               VARCHAR2(100) 
FK_COMPANY_SIZE_CODE          VARCHAR2(10)  
 */


public class CompanyDTO {

	
	
	// insert 용
   private String   PK_COMPANY_CODE;       // 사업자등록번호
   private String   COMPANY_PASSWD;        // 회사비밀번호
   private String   COMPANY_NAME;          // 기업명
   private String   COMPANY_EMAIL;         // 회사이메일
   private String   COMPANY_TEL;           // 회사전화번호
   private String   SALES;                 // 매출액
   private String   NUMBER_OF_EMPLOYEE;    // 사원수
   private String   HOMEPAGE;              // 홈페이지
   private String   ESTABLISHMENT_DATE;    // 설립일      
   private String   BOSS_NAME;             // 대표자명
   private String   FK_BUSS_TYPE_CODE;     // 업종코드
   private String   COMPANY_ADDRESS;       // 기업주소
   private String   FK_COMPANY_SIZE_CODE;  // 기업규모코드

	
	/////////////////////////////////////////////////////////////////////////
	// select 용
	
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////


	public String getPK_COMPANY_CODE() {
		return PK_COMPANY_CODE;
	}


	public void setPK_COMPANY_CODE(String pK_COMPANY_CODE) {
		if(pK_COMPANY_CODE == null || pK_COMPANY_CODE.isBlank() || pK_COMPANY_CODE.length() != 10) {
	           System.out.println("<경고> 아이디는 공백이 아닌 숫자 10개를 입력하세요. \n");
	        }
	        else if(Pattern.matches("^[0-9]*$", pK_COMPANY_CODE)){
	          
	           this.PK_COMPANY_CODE= pK_COMPANY_CODE;

	        }     
	        else {
	        	pK_COMPANY_CODE = null;
	        	System.out.println("<경고> 아이디는 공백이 아닌 숫자 10개를 입력하세요. \n");
	        }
	}


	public String getCOMPANY_PASSWD() {
		return COMPANY_PASSWD;
	}


	public void setCOMPANY_PASSWD(String cOMPANY_PASSWD) {
		 // 8 ~ 16 글자의 영문 대소문자, 숫자, 특수문자만 가능
	      
	      if(isCheckPasswd(cOMPANY_PASSWD)) {
	         COMPANY_PASSWD = cOMPANY_PASSWD;
	      }
	      else {
	         cOMPANY_PASSWD = null;
	         System.out.println("<경고> 비밀번호는 공백을 제외한 8~16자리의 영문 대소문자, 숫자, 특수문자를 모두 사용하여 입력하세요.\n");
	      }
	}


	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}


	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}


	public String getCOMPANY_EMAIL() {
		return COMPANY_EMAIL;
	}


	public void setCOMPANY_EMAIL(String cOMPANY_EMAIL) {
		COMPANY_EMAIL = cOMPANY_EMAIL;
	}


	public String getCOMPANY_TEL() {
		return COMPANY_TEL;
	}


	public void setCOMPANY_TEL(String cOMPANY_TEL) {
		COMPANY_TEL = cOMPANY_TEL;
	}


	public String getSALES() {
		return SALES;
	}


	public void setSALES(String sALES) {
		SALES = sALES;
	}


	public String getNUMBER_OF_EMPLOYEE() {
		return NUMBER_OF_EMPLOYEE;
	}


	public void setNUMBER_OF_EMPLOYEE(String nUMBER_OF_EMPLOYEE) {
		NUMBER_OF_EMPLOYEE = nUMBER_OF_EMPLOYEE;
	}


	public String getHOMEPAGE() {
		return HOMEPAGE;
	}


	public void setHOMEPAGE(String hOMEPAGE) {
		HOMEPAGE = hOMEPAGE;
	}


	public String getESTABLISHMENT_DATE() {
		return ESTABLISHMENT_DATE;
	}


	public void setESTABLISHMENT_DATE(String eSTABLISHMENT_DATE) {
		if(!eSTABLISHMENT_DATE.isBlank()) {

            SimpleDateFormat sdformat = new SimpleDateFormat("yyyymmdd");
            sdformat.setLenient(false);
            
            try {
               Date d = sdformat.parse(eSTABLISHMENT_DATE);

               Date now = new Date(); 
                String str_now = sdformat.format(now); 
                now = sdformat.parse(str_now); 
                
                if(d.compareTo(now) > 0) {
                   ESTABLISHMENT_DATE = null;
                   
                }
                else {
                   ESTABLISHMENT_DATE = eSTABLISHMENT_DATE;
                }
               
            }catch(ParseException e){
               System.out.println("<경고> 달력에 존재하지 않는 값입니다. ");
            }
           
         }
       
       else {
          ESTABLISHMENT_DATE = null;
       }
	}


	public String getBOSS_NAME() {
		return BOSS_NAME;
	}


	public void setBOSS_NAME(String bOSS_NAME) {
		if(bOSS_NAME == null || bOSS_NAME.isBlank() ) {
	           System.out.println("<경고> 대표자명은 공백이 아닌 글자로 입력하셔야 합니다.\n");
	        }
	        else {
	           
	           Pattern p =Pattern.compile("^[가-힣]{2,6}$");
	   
	           Matcher m = p.matcher(bOSS_NAME);
	           
	           
	           if(m.matches()) {
	              this.BOSS_NAME = bOSS_NAME;
	        
	           }
	           else {
	              System.out.println("<경고> 대표자명은 한글로만 이루어져야하고 글자길이는 2~6글자만 가능합니다.");
	              BOSS_NAME = null;
	           }  
	        }
	}


	public String getFK_BUSS_TYPE_CODE() {
		return FK_BUSS_TYPE_CODE;
	}


	public void setFK_BUSS_TYPE_CODE(String fK_BUSS_TYPE_CODE) {
		FK_BUSS_TYPE_CODE = fK_BUSS_TYPE_CODE;
	}


	public String getCOMPANY_ADDRESS() {
		return COMPANY_ADDRESS;
	}


	public void setCOMPANY_ADDRESS(String cOMPANY_ADDRESS) {
		COMPANY_ADDRESS = cOMPANY_ADDRESS;
	}


	public String getFK_COMPANY_SIZE_CODE() {
		return FK_COMPANY_SIZE_CODE;
	}


	public void setFK_COMPANY_SIZE_CODE(String fK_COMPANY_SIZE_CODE) {
		FK_COMPANY_SIZE_CODE = fK_COMPANY_SIZE_CODE;
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
	
	
}
>>>>>>> c9027385ac9e12d196bf66c374e5852e058ed599
