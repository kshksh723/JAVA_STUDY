package my.day15.d.abstract_class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import my.util.MyUtil;

/*
	==== *** 객체지향 프로그래밍(Object Oriented Programming)이란? *** ==== 

	※ 객체지향(중심)프로그래밍(OOP) 이란?
      -- field(= attribute, property, 속성) 와 method(= operation, function, 기능, 행위) 로 구성된 클래스를 통해 객체(= instance, 인스턴스)를 만들고, 
         데이터처리와 같은 상호작용을 만들어진 객체를 가지고 프로그래밍화 하는것을 OOP 라고 부른다.

    예를 들어, 
    쇼핑몰 프로그램에 있어서 어떤 회원이 어떤 제품을 주문하는것을 프로그래밍 하려고 한다. 
    주문이라는 데이터 처리를 위해서는 어떤 회원이 어떤 제품을 언제 몇 개를 주문했다라는 정보를 담아야 한다. 
    이것을 위해서 "주문" 이라는 클래스를 만드는데 이 "주문" 클래스의 field 로 "회원" 클래스와 "제품" 클래스가 들어가게 된다. 
    또한 "주문" 클래스의 method 로 주문결과를 화면에 보여주는 기능이 들어가야 할 것이다. 
    이와같이 "회원" 클래스, "제품" 클래스, "주문" 클래스를 통해 객체를 만들고 객체들간의 상호작용을 논리적으로 프로그래밍 하는것을 OOP라고 부른다.


    ※ OOP 의 4가지 특징(!!!! 필수암기 !!!!)
    --> 1. 상속성(Inheritance) : 클래스의 재사용과 소스 코드의 중복제거를 목적으로 하는 기술 
    --> 2. 추상화(Abstraction) : 프로그램이 필요로 하는 실제 데이터들을 모델링하는 기술 
    --> 3. 캡슐화(EnCapsulation == 은닉화) : 객체지향의 가장 중요한 데이터 보호 기술   
    --> 4. 다형성(Polymorphism) : 상속을 이용하여 여러 클래스 타입을 하나의 클래스 타입으로 다루는 기술
 
 
    구인구직 어플리케이션 프로그래밍 제작시 
    필요한 요소(부품)가 무엇이 있는지 생각해보자.
    아주 다양하고, 많은 요소들이 필요할 것이다.

    예를 들어,
    구직자, 구인회사, 채용.... 등등등 많이 있다.
    이러한 요소(부품)들이 바로 객체인데 이 객체를 만드는 설계도면이 클래스이다.

    위와(구인구직 어플리케이션 프로그래밍 제작) 같이 현실세계에 존재하는
    다양한 객체들을 각각 하나하나 추상화 시켜서 클래스로 옮기는 것을
    객체지향 프로그래밍(OOP)이라고 한다.

    여기서 추상화(abstraction)란?
    --> 일반적인 의미의 추상화란 어떤 물체(object)에서 주된 특징을 부각시켜 표현하고, 
        나머지 부분은 과감하게 생략하는 것을 말한다.
        추상화의 반대는 아주 시시콜콜한것(세세한 것)까지 전부 몽땅 다 묘사하는 것을 말하는데 이것을 정밀화 라고 말한다.
 
    객체지향 프로그래밍(OOP)에서 사용되는 추상화도 이와 비슷하다.
    어떤 하나의 물체를 대표하는 속성(명사, 멤버변수)과 기능(동사, 메소드)을 추출해내는 것을 
    객체지향 프로그래밍(OOP)에서 "추상화" 라고 한다. 

    >>> 예를 들어, 구인구직 어플리케이션 프로그래밍 제작시
        필요한 부품(요소)중 하나인 "구직자" 에 대해서 알아보자.
        "구직자" 가 가지고 있는 속성(명사, 멤버변수)과 행동양식(기능, 동사, 메소드)을 뽑아보자.

    1). 속성(property, attribute, field)은 멤버변수가 되고,
    2). 행동양식(기능)은 메소드가 되는 것이다.

    Gujikja(클래스)
    |
    |-- 속성(property, attribute, field) : 아이디, 암호, 성명, 연락처, 이메일, 주소, 학력, 희망구직직종, 희망연봉.....  
    |-- 행동양식(기능, 메소드) : 구직자로 접속(로그인)할수 있는 기능, 구직자정보를 조회해주는 기능, 구직자정보를 변경해주는 기능, ....... 

*/	

public class Gujikja extends CommonMember {
//  Gujikja 클래스는 CommonMember 클래스에 생성되어진 field 및 method 및 기본생성자를 상속 받아온다. 
//  CommonMember 클래스는 Gujikja 클래스의 부모클래스 가 되어지고,
//  Gujikja 클래스는 CommonMember 클래스의 자식클래스 가 되어진다.	
	
/*
    ---------------------------------------------------------------------------------------------------------------------------
	접근제한자(접근지정자, accessmodifier)   자기자신클래스내부      동일패키지에있는다른클래스      다른패키지에있는하위(자식)클래스       그외의영역  
	--------------------------------------------------------------------------------------------------------------------------- 
	public                                    O                    O                         O                        O  
	protected                                 O                    O                         O                        X
	없음(default)                              O                    O                         X                        X
	private                                   O                    X                         X                        X
*/	
	
	
	// field 생성
	// field 의 캡슐화(EnCapsulation == 은닉화)
	
	private String jubun;   // 주민번호인데 앞자리 6자리에 + 성별을 나타내는 1자리까지만 입력한다. 
	                        // 예: "9506201"  "9607202"   "0006203"  "0007204"  "1106203" 
	
	
	static int count;     // Gujikja 객체(인스턴스)의 개수를 알아오려는 용도 
	
	// 기본생성자 생략됨!!
/*	
	public Gujikja() {
	//	super(); // Gujikja 클래스의 부모클래스인 CommonMember 클래스의 기본생성자 이다.
	}
*/	
	// 메소드의 오버라이딩(overriding), 메소드의 재정의
		@Override
		public void setName(String name) {
			// 성명은 공백이 없는 한글로만 이루어져야 하며 최소 2글자 이상 최대 6글자로만 되어져야 한다.
			
			// == 1. 정규표현식(Regular Expression) 패턴을 작성한다. == //
			Pattern p = Pattern.compile("^[가-힣]{2,6}$");
			
			// == 2. 문자열이 주어진 정규식 패턴과 일치하는지 판별하는 객체를 생성한다. == //
			Matcher m = p.matcher(name);
						
			// == 3. 판별하도록 한다. == //
			if(m.matches()) {
				super.setName(name);
			}
			else {
				 System.out.println("[경고] 성명은 공백이 없는 한글로만 이루어져야 하며 최소 2글자 이상 최대 6글자로만 되어져야 합니다.\n");
			}
						
		}
    // === getter , setter === // 
	public void setJubun(String jubun) {
		
		if(MyUtil.isCheckJubun(jubun)) {
			this.jubun = jubun;
		}
		else {
			System.out.println("[경고] 주민번호 앞에서 부터 7자리 까지 올바르게 입력하세요!!\n");
		}
		
	}

	
	public String getJubun() {
		return jubun;
	}
	
	
	// === 구직자의 만나이을 알려주는 메소드 생성하기 === //
	int getAge() {
		
		int age = 0;
		
		// 구직자의 올해 생일이 현재날짜와 같거나 현재날짜 보다 이전(과거)이라면
		// 나이 = 현재년도 - 구직자의 태어난년도 
		
		// 구직자의 올해 생일이 현재날짜 보다 이후(미래)이라면 
		// 나이 = 현재년도 - 구직자의 태어난년도 - 1  
		
		Date now = new Date(); // 현재시각
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyyMMdd");
		String str_now = sdfmt.format(now); // "20240131"  // 오늘
		
		// 구직자의 올해생일
		String str_now_birthday = str_now.substring(0, 4) + jubun.substring(2, 6); 
		                       // "2024" + "1020"  ==> "20241020"
		
		// 현재년도
		int now_year = Integer.parseInt(str_now.substring(0, 4)); 
		
		// 구직자의 태어난년도
		int centry = ("1".equals(jubun.substring(jubun.length()-1)) || "2".equals(jubun.substring(jubun.length()-1)) )?1900:2000;
		int birth_year = centry + Integer.parseInt(jubun.substring(0, 2));
		
		try {
			now = sdfmt.parse(str_now); // 오늘의 자정     오늘의 0시0분0초
			Date now_birthday = sdfmt.parse(str_now_birthday); // 올해생일의 자정  올해생일의 0시0분0초 
			
			if(now_birthday.compareTo(now) > 0) { // 구직자의 올해생일이 현재일 보다 뒤에 있는 경우(미래)
				age = now_year - birth_year - 1; 
			}
			else { // 구직자의 올해생일이 현재일 이거나 현재일 보다 이전에 있는 경우(과거)
				age = now_year - birth_year;
			}
			
		} catch (ParseException e) {
			
		}
		
		return age;
	}// end of int getAge()-----------------------------
	
	
	// === 성별을 구해주는 메소드 === //
	public String getGender() {
	
		if("1".equals(jubun.substring(jubun.length()-1)) ||
		   "3".equals(jubun.substring(jubun.length()-1)) ) {
		    return "남";	
		}
		
		else if("2".equals(jubun.substring(jubun.length()-1)) ||
			    "4".equals(jubun.substring(jubun.length()-1)) ) {
			return "여";	
		}
		
		else {
			return "";
		}
		
	}// end of String getGender()-------------------------------
	
	
	// === 구직자의 정보를 한줄로 출력해주는 메소드 생성하기 ===
	String get_gujikja_Info() {
		
		// eomjh   qWe******    엄정화    961020   여     27        2024-01-31 10:30:40
		
		StringBuilder sb = new StringBuilder();
		sb.append(super.getId() +"\t");
		
		sb.append(super.getPasswd().substring(0, 3) + "*".repeat(this.getPasswd().length() - 3) +"\t");
		
		sb.append(getName()+"\t");
		
		sb.append(jubun.substring(0, jubun.length()-1) +"\t");
		
		sb.append(getGender()+"\t"); // 성별
		
		sb.append(getAge()+"\t"); // 만나이
		
		sb.append(super.getRegister_day());
		
		return sb.toString();
		
	} // end of String getInfo()------------------- 
	//////////////////////////////////////////////////////
	
	
	
	
	//////////////////////////////////////////////////////
	void test() {
		System.out.println(super.parent_test());
		// 또는
		System.out.println(this.parent_test());
		// 또는
		System.out.println(parent_test());
	}
	
	
}







