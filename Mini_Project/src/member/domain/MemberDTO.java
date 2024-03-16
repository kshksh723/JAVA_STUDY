package member.domain;

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

	/////////////////////////////////////////////////////////////////////////
	// select 용 
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////

	
	public String getPK_USER_ID() {
		return PK_USER_ID;
	}
	public void setPK_USER_ID(String pK_USER_ID) {
		PK_USER_ID = pK_USER_ID;
	}
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}
	public String getUSER_PASSWD() {
		return USER_PASSWD;
	}
	public void setUSER_PASSWD(String uSER_PASSWD) {
		USER_PASSWD = uSER_PASSWD;
	}
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getUSER_JUBUN() {
		return USER_JUBUN;
	}
	public void setUSER_JUBUN(String uSER_JUBUN) {
		USER_JUBUN = uSER_JUBUN;
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
	
	public void setUserseq(int int1) {
		// TODO Auto-generated method stub
		
	}
	public void setUserid(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setName(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setMobile(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setPoint(int int1) {
		// TODO Auto-generated method stub
		
	}
	public void setRegisterday(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCOMPANY_NAME(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
