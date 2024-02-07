package my.day16.d.INTERFACE_inheritance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtil;


// 미완성 클래스 (== 추상클래스 == abstract class
public class CommonMember {
//public  abstract  class CommonMember
	// Gujikja 클래스와 Company 클래스에서 공통으로 사용되어지는 field(속성) 생성하기
	
	private int type;			 // Gujikja와 company 를 구분짓는 용도, type 값이 1이라면 gujikja로 보고, type 값이 2이라면 company라고 본다
	private String id;           // 아이디
	private String passwd;       // 비밀번호
	private String name;         // 성명 또는 회사명
	private String register_day; // 가입일자
	
	static int count;	// gujikja 객체 및 company 객체가 생성되어진 개수를 알아오려는 용도이다
	
	
	// Gujikja 및 Company 클래스의 부모클래스인 CommonMember 클래스의 기본생성자 
	public CommonMember(){
		// System.out.println("== 부모클래스인 CommonMember 클래스의 기본생성자 호출 ==");
		
		Date now = new Date(); // 현재시각
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		
		register_day = df.format(now);
	}
	
	
	// 캡슐화(EnCapsulation == 은닉화) 되어진 field 를 메소드를 통해 접근하도록 만들기 //
	// == getter, setter 하기 == // 
	public int getType() {
		return type;
	}


	public void setType(int type) {
		if(type == 1 || type == 2) {
			this.type = type;
			// type의 값이 1이라면 Gujikja로 보고 
			//  type의 값이 2이라면 company 라 보겠다 
		}
		this.type = type;
	}
	
	
	
	public void setId(String id) {
		
		if( id == null || id.isBlank() ) {
			System.out.println("[경고] 아이디는 공백이 아닌 글자로 입력하셔야 합니다.\n");
		}
		else {
			
			// userid 는 첫글자는 반드시 영문(대.소문자)자로 시작해야 하고 그나머지 글자는 
			// 영문자 또는 숫자로만 이루어져야 한다.!!
			// 그리고 userid 의 길이는 최소 3글자, 최대 10글자로 제한한다.
			
			// >> 정규표현식(Regular Expression)이란? <<
			// ==> 특정한 규칙을 가진 문자열의 집합을 표현하기 위해 쓰이는 형식언어
			
			// == 1. 정규표현식(Regular Expression) 패턴을 작성한다. == //
			Pattern p = Pattern.compile("^[A-Za-z][A-Za-z0-9]{1,9}$");
			// ^ 은 시작을 의미한다.
			// $ 는 끝을 의미한다.
			// [] 는 글자 1개가 들어오는 것을 의미한다.
			// [A-Za-z] 는 글자 1개가 들어오는데 영문자만 가능하다.
			// [A-Za-z0-9] 는 글자 1개가 들어오는데 영문자 또는 숫자만 가능하다.
			// [A-Za-z0-9]{1,9} 은 [A-Za-z0-9] 이 최소 1번 반복, 최대 9번 반복해야 한다.
			
			// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
			Matcher m = p.matcher(id);
			
			// == 3. 판별하도록 한다. == //
			if(m.matches()) {
				this.id = id;
			}
			else {
				System.out.println("[경고] 아이디는 첫글자는 영문이어야 하고 나머지는 영문 또는 숫자로만 이루어진 글자길이는 3~10글자만 가능합니다.\n");
			}
		}
	}
	
	
	


	public String getId() {
		
		return id;
	}
		
	
	public void setPasswd(String passwd) {
		
		// 비밀번호는 영대문자, 영소문자, 숫자, 특수문자가 혼합된 최소 8글자 이상 최대 15글자 이하이어야 한다.
		if( MyUtil.isCheckPasswd(passwd) ) {
			this.passwd = passwd;	
		}
		else {
			System.out.println("[경고] 비밀번호는 영대문자, 영소문자, 숫자, 특수문자가 혼합된 최소 8글자 이상 최대 15글자 이하이어야 합니다.\n");
		}
	}

	
	public String getPasswd() {
		return passwd;
	}
	

	
	
	public void setName(String name) {
		this.name = name;
					
	}
	
		
	public String getName() {
		return name;
	}
	
	
	public String getRegister_day() {
		return register_day;
	}


	///////////////////////////////////////////////////
	
	
	// public abstract String  getInfo(); 
	//{ //미완성 메소드
	//	return "1. 아이디 : " + id + ", 2. 비밀번호 :" + passwd;
	

	 // 미완성 메소드(== 추상메소드 abstract method) -> 해당클래스가 미완성 클래스가 되어야함
	
	
	///////////////////////////////////////////////////////
	String parent_test() {
		return "부모클래스에서 만들었어요. 그냥 연습이에요!!";
	}
	
	
	
	
	
	
}
