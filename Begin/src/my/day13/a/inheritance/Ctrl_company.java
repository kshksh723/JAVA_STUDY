package my.day13.a.inheritance;

public class Ctrl_company extends Ctrl_Common_ {

	// 구인회사 (company) 신규 회원가입시
	// company 클래스의 field의 요구사항에 모두 맞으면
	// company[] cp_arr에 저장시켜주는 메소드 생성하기 ==
	public static void main(String[] args) {
		if(Company.count < cp_arr.lengt) {
			Company cp = new Company
					
					
		do {
			System.out.println("2. 비밀번호 : ");
			String passwd = sc.nextLine();
			cp.setPasswd(passwd);
		}while(!(cp.getName() != null ));
					
			
		do {
			System.out.println("4. 사업자 등록번호 : ");
			String business_number = sc.nextLine();
			
			cp.setBusiness_number(business_number);
		} while(!cp.getBusiness_number() != null)); // 값을 안넣어주면 default가 null값

		/////////////////////////////////////////////////
		cp_arr[Company.count++]=cp;
		
		
	}

}
