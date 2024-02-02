//package my.day13.a.inheritance;
//
//public class Ctrl_company extends Ctrl_Common_ {
//
//	// 구인회사 (company) 신규 회원가입시
//	// company 클래스의 field의 요구사항에 모두 맞으면
//	// company[] cp_arr에 저장시켜주는 메소드 생성하기 ==
//	public static void main(String[] args) {
//		if(Company.count < cp_arr.lengt) {
//			Company cp = new Company
//					
//					
//		do {
//			System.out.println("2. 비밀번호 : ");
//			String passwd = sc.nextLine();
//			cp.setPasswd(passwd);
//		}while(!(cp.getName() != null ));
//					
//			
//		do {
//			System.out.println("4. 사업자 등록번호 : ");
//			String business_number = sc.nextLine();
//			
//			cp.setBusiness_number(business_number);
//		} while(!cp.getBusiness_number() != null)); // 값을 안넣어주면 default가 null값
//
//		/////////////////////////////////////////////////
//			cp_arr[Company.count++]=cp;
//		System.out.println(">>구인회사 회원가입 성공 !! << \n");
//		
//		else {
//			System.out.println(">> 정원 "+ cp_arr.length + "개가 꽉차서 구인회사 회원가입이 ");
//		}
//		
//		
//	}
//
//
package my.day13.a.inheritance;

import java.util.Scanner;

public class Ctrl_company extends Ctrl_common {

	// == 구인회사(Company) 신규 회원가입시
	//    Company 클래스의 field 의 요구사항에 모두 맞으면
	//    Company[] cp_arr 에 저장시켜주는 메소드 생성하기 ==
	public void register(Scanner sc, Company[] cp_arr) {
		
		if(Company.count < cp_arr.length) { // 지금까지 생성된 구인회사 회원수가 cp_arr.length(==>정원) 보다 적을 경우에만 신규회원을 받아들인다.
		    
			Company cp = new Company();
			
			// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
			outer:
			do {
				System.out.print("1.아이디 : ");
				String id = sc.nextLine();
				
				// == 중복 아이디 검사하기 시작 == //
				for(int i=0; i<Company.count; i++) {
					
					if( id.equals(cp_arr[i].getId()) ) {
						System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
						continue outer;
					}
					
				}// end of for-------------------
				// == 중복 아이디 검사하기 끝 == //
				
				cp.setId(id);
				
			} while(!(cp.getId() != null));
			
			
			// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을때 까지 반복해야 한다.
			do {
				System.out.print("2.비밀번호 : ");
				String passwd = sc.nextLine();
				
				cp.setPasswd(passwd);
				
			} while(!(cp.getPasswd() != null));
			
			
			// 회사명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
		    // 회사명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
			do {
				System.out.print("3.회사명 : ");
				String name = sc.nextLine();
				
				cp.setName(name);
				
			} while(!(cp.getName() != null));
			
			
			// 사업자등록번호는 필수 입력사항이면서 사업자등록번호 조건에 맞을때 까지 반복해야 한다.
			do {
				System.out.print("4.사업자등록번호 : ");
				String business_number = sc.nextLine();
				
				cp.setBusiness_number(business_number);
				
			} while(!(cp.getBusiness_number() != null));
			
			////////////////////////////////////////////////
			
			cp_arr[Company.count++] = cp;
			
			System.out.println(">> 구인회사 회원가입 성공 !! <<\n");
			
		}
		else { // 지금까지 생성된 구인회사 회원수가 cp_arr.length(==>정원) 와 같거나 큰 경우에만 신규회원을 받아들이면 안된다.
			System.out.println(">> 정원 "+ cp_arr.length + "개가 꽉차서 구인회사 회원가입이 불가합니다.!! <<\n");
		}		
		
	}// end of public void register(Scanner sc, Company[] cp_arr)-----------

}
