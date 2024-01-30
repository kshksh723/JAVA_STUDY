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

	}

}
