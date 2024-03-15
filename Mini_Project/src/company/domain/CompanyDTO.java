package company.domain;

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
	private String   PK_COMPANY_CODE;		// 기업코드
	private String   COMPANY_NAME;			// 기업명
	private String   SALES;					// 매출액
	private String 	 NUMBER_OF_EMPLOYEE; 	// 사원수
	private String   HOMEPAGE; 				// 홈페이지
	private String   ESTABLISHMENT_DATE; 	// 설립일      
	private String   BOSS_NAME; 			// 대표자명
	private String   FK_BUSS_TYPE_CODE; 	// 업종코드
	private String   COMPANY_ADDRESS; 		// 기업주소
	private String   FK_COMPANY_SIZE_CODE;  // 기업규모코드
	
	/////////////////////////////////////////////////////////////////////////
	// select 용
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////
	public String getPK_COMPANY_CODE() {
		return PK_COMPANY_CODE;
	}
	public void setPK_COMPANY_CODE(String pK_COMPANY_CODE) {
		PK_COMPANY_CODE = pK_COMPANY_CODE;
	}
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
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
		ESTABLISHMENT_DATE = eSTABLISHMENT_DATE;
	}
	public String getBOSS_NAME() {
		return BOSS_NAME;
	}
	public void setBOSS_NAME(String bOSS_NAME) {
		BOSS_NAME = bOSS_NAME;
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
	
	
	
	
	
	
	
	
	
}
