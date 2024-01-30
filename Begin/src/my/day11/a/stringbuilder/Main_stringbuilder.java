package my.day11.a.stringbuilder;

public class Main_stringbuilder {

	public static void main(String[] args) {
		String name = "일순신";    // 메모리상에  name 1개 소모
        name += ",이순신";  	// 메모리상에  name 1개 소모
         name += ",삼순신"; 	// 메모리상에  name 1개 소모
         name += ",사순신"; 	// 메모리상에  name 1개 소모
         name += ",오순신";  	// 메모리상에  name 1개 소모
         name += ",육순신";  	// 메모리상에  name 1개 소모
         name += ",칠순신";  	// 메모리상에  name 1개 소모
         name += ",팔순신";  	// 메모리상에  name 1개 소모 
         name += ",구순신";  	// 메모리상에  name 1개 소모
         
         // 누적되어진 메모리상의 name은 9개 소모된다고 한다. 
  
         System.out.println(name);
         
         name = "끝";
         System.out.println(name);
         
         
  
  System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
  	// 권장사항
  	StringBuffer sb_1 = new StringBuffer();
 // StringBuffer 는 Multi thread 를 지원해준다.
    // 그래서 Multi thread 로 움직이는 게임에는 StringBuffer 를 사용한다.
    // StringBuffer 는 StringBuilder 보다 무겁고 동작속도가 떨어지지만
    // Multi thread 를 지원해주므로 게임에서 사용하는 것이 적합하다.
    sb_1.append("일순신");
    sb_1.append(",이순신");
    sb_1.append(",삼순신");
    sb_1.append(",사순신");
    sb_1.append(",오순신");
    sb_1.append(",육순신");
    sb_1.append(",칠순신");
    sb_1.append(",팔순신");
    sb_1.append(",구순신");
  
  
  
  
	}

}
