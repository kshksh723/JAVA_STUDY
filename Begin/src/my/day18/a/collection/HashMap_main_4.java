package my.day18.a.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
=== Map 계열 ===
1. HashMap(single) 과 Hashtable 이 있다.
2. Map 계열은 List 계열(Vector, ArrayList, LinkedList)처럼 index 가 사용되어 저장되는 것이 아니라, 
   Map 계열은 key값과 value값을 쌍으로 사용하여 저장하는데  
   데이터 저장시 사용되는 메소드는 put(String key, Object value)메소드를 사용한다.
   이때 key값은 반드시 고유한 값을 가져야 하고, value값은 중복된 값이 와도 괜찮다.
3. Map 계열에 저장된 key값들은 순서와는 상관없이 저장된다.  
4. Map 계열에 저장된 value값을 추출하려면 key를 이용해서 가져오는데 
   value 값의 추출은 get(String key) 메소드를 사용한다. 
*/
public class HashMap_main_4 {

	public static void main(String[] args) {

/*		HashMap<Integer, Member> mbr_map = new HashMap<Integer, Member>();
		mbr_map.put(23423,new Member("youjs","Qwer1234$","유재석","7209101"));
		mbr_map.put(212323, new Member("eom","Qwer1234$","엄정화","6808152"));
		mbr_map.put(443223,new Member("kanghd","Qwer1234$","강호동","7006151"));
		
		System.out.println(mbr_map.get(212323).toString());
		System.out.println("~~~~~~~~~~~");
		System.out.println(mbr_map.get(212323));
*/
		
	//	HashMap<String, Member> mbr_map = new HashMap<String, Member>();
	// 또는
	//	HashMap<String, Member> mbr_map = new HashMap<String, Member>();// jdk 8 이후 부터 가능함
		
	//	Map<String, Member> mbr_map = new HashMap<>(); // jdk 8 이후부터 가능함
	// 또는	
		Map<String, Member> mbr_map = new HashMap<>(); // jdk 8 이후부터 가능함
		
		mbr_map.put("youjs",new Member("youjs", "Qwer1234$","유재석","7209101"));
		mbr_map.put("eom",new Member("eom","Qwer1234$","엄정화","6808152"));
		mbr_map.put("kanghd",new Member("kanghd","Qwer1234$","강호동","7006151"));
		mbr_map.put("leess",new Member("leess", "Qwer1234$","이순신","0010203"));
		mbr_map.put("kimth",new Member("kimth","Qwer1234$","김태희","0105064"));
		mbr_map.put("kangkc",new Member("kangkc","Qwer1234$","강감찬","9812301"));
		mbr_map.put("kimss",new Member("kimss","Qwer1234$","김순신","0203203"));
		
		System.out.println(mbr_map.get("kangkc")); // 강감찬
		mbr_map.put("kanghd",new Member("kangkc","Abcd0070$","강기춘","0112303"));
	// key값이 중복되면 value값은 덮어씌운다
		System.out.println(mbr_map.get("kangkc"));  // 강기춘이 나옴
	
	
	Member mem1 = new Member("parksj", "Qwer1234$", "박서준", "88032301");
	Member mem2 = mem1;
	mem2.setName("이현우");
	mbr_map.put("mem1", mem1); // 키 값이 다르면 value 값이 중복된 값이 들어와도 괜찮다
	mbr_map.put("mem2", mem2);  // 키 값이 다르면 value 값이 중복된 값이 들어와도 괜찮다
	
	System.out.println(mbr_map.get("mem1"));	// 이현우
	System.out.println(mbr_map.get("mem2")); // 이현우
	
	System.out.println("\n ~~~~~~~~~~~~~~~~~~~~ \n");
	
	
	// == mbr_map에 저장되어진 모든 key들을 읽어오는 첫번째 방법 == //
	Set<String> key_set = mbr_map.keySet();
	
	for(String key :key_set) {
		System.out.println(key);
	} // end of for
	/*
	eom
	youjs
	kimth
	kanghd
	kimss
	leess
	mem1
	 */
	System.out.println("== mbr_map 에 저장되어진 모든 Member 객체의 정보를 출력해주는 첫번째 방법 ==");
	for(String key : key_set) {
		System.out.println(mbr_map.get(key));
	} // end of for
	
	System.out.println("== [퀴즈] mbr_map 에 아래와 같이 새로운 회원을 가입하시는데 아이디가 중복이라면 '아이디가 이미 사용중입니다.' 라는 메시지를 출력해주고 아이디가 중복이 아니라면 mbr_map 에 저장하도록 하세요..");
	
	//>> 미련한 방법 <<
	String userid = "leess";
	boolean is_existence_key = false;
	
	Set<String> keySets = mbr_map.keySet();
	
	for( String key : keySets) {
		if(userid.equals(key)) {
			is_existence_key = true;
		}
		
	} // end of for
	if(!is_existence_key) {
		mbr_map.put(userid,new Member(userid,"Qwer1234$","이수성","0203203"));
		System.out.println("\n >>> 회원가입 성공 <<<");
	}
	else {
		System.out.println("\n >> " + userid + "아이디는 이미 사용중입니다. 회원가입 실패!! <<<");
	}
	
	System.out.println("\n ~~~~~~~~~~~~~~~~~~~~ \n");
	//>> 똑똑한 방법 <<
	userid = "leess";
	if(mbr_map.get(userid) == null) {
		mbr_map.put(userid,new Member(userid,"Qwer1234$","이수성","0203203"));
		System.out.println("\n >>> 회원가입 성공 <<<");
	}
	else {
		System.out.println("\n >> " + userid + "아이디는 이미 사용중입니다. 회원가입 실패!! <<<");
	}
	
	
// set도 하나의 collection ,set은 중복된 값이 못들어옴!	

	} //end of public static void main(String[] args)
	
	/*
	 * map은 왠만하면 for문 쓰지 말기
	 */
	
	

}
