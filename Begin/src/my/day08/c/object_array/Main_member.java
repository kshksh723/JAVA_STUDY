package my.day08.c.object_array;

import java.util.Scanner;

import my.util.MyUtil;

public class Main_member {

	public static void main(String[] args) {
		
		//System.out.println("시작"+MyUtil.space_delete("이순신")+"끝");
		//System.out.println("시작"+MyUtil.space_delete(null)+"끝");
		// int[] arr_subject = new int[7];
		// 엔터는 null이 아니다
		/*
		 * System.out.println("시작"+"     "+ "끝"); // true
		 * System.out.println("시작"+" 중간    ".trim()+ "끝" ); //trim 왼쪽부터 양쪽에 공백을 다 제거
		 * System.out.println("시작"+" 중간    "+ "끝" );
		 * System.out.println("    ".trim().isEmpty() );
		 * System.out.println(" 하   ".trim().isEmpty() );
		 * System.out.println(" ".trim().isEmpty() );
		 * System.out.println("    ".isEmpty() ); // true
		 */	
		
		// int[] arr_subject = new int[7];
		Member[] arr_mbr = new Member[3];
		
		/*
		 * for(int i=0; i<arr_mbr.length; i++) { System.out.println(arr_mbr[i]); }
		 */// end of for
		
		Scanner sc = new Scanner(System.in);
		
		String str_menu = " ";  
		do {
			System.out.println("\n===========>> 메뉴 << ============\n"+"1. 회원 가입 2. 모든 회원 조회 3. 프로그램종료\n"
								+ "===========================");
			System.out.print("◎ 선택하세요 =>");
			
		 str_menu = MyUtil.space_delete(sc.nextLine());
		 switch (str_menu) {
		case "1": // 회원가입
			System.out.print("★ 아이디 :");
			String id = sc.nextLine();
			if(id.trim().isEmpty()) 
				
				
			System.out.print("★ 비밀번호 :");
			String passwd = sc.nextLine();
			
			System.out.print(" ★ 성명 :");
			String name = sc.nextLine();
			
			Member mbr = new Member();
			mbr.id = id;
			mbr.passwd = passwd;
			mbr.name = name;
			break;// switch를 빠져나옴

		case "2": // 모든 회원조회
			break;

		case "3": // 프로그램 종료
			
			break;
			
		default:// 메뉴에 존재하지 않는 값
			System.out.println("== [경고] 메뉴에 없는 것입니다 == \n");
			break;
		}
		} while (!("3".equals(str_menu) ));	

			
		System.out.println("\n==== 프로그램 종료 ====");
		
							
	
		

		// 문자열 비교, 3번을 do while 문으로 반복해준다
	}

}

// [경고] 메뉴에 없는 것입니다.
