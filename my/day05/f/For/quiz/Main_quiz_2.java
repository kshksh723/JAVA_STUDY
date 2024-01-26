package my.day05.f.For.quiz;

public class Main_quiz_2 {

	public static void main(String[] args) {
		
		// for 문을 사용하여 알파벳 소문자를 아래처럼 출력하세요.
		// a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z    
        
		String result = ""; // 문자열 누적용
        
		for(int i=0; i<'z'-'a'+1; i++) {
			char ch = (char)('a'+i);  // (char)(97+0) ==> 'a'
			                          // (char)(97+1) ==> 'b'
                                      // (char)(97+2) ==> 'c'
		/*	
			String add = "";
			if(i<'z'-'a')
			   add = ",";
		*/
		//  또는
			String add = (i<'z'-'a')?",":"";	
			result += ch+add;
		}// end of for------------------
        
		System.out.println(result);
		// a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		// for 문을 사용하여 알파벳 소문자를 아래처럼 출력하세요.
		/*
		   a,b,c,d,e,f,g,h,i,j,k,l,m
		   n,o,p,q,r,s,t,u,v,w,x,y,z
		*/
		result = ""; // 문자열 누적용
		
		for(int i=0; i<'z'-'a'+1; i++) {
			char ch = (char)('a'+i);  
			
		/*	
			String add = "";
			if(ch=='m' || ch=='z')
				add = "\n";
			else
			    add = ",";
		*/
		//  또는
			String add = (ch=='m' || ch=='z')?"\n":",";	
			result += ch+add;
		}// end of for------------------
		
		System.out.println(result);
		/*
		   a,b,c,d,e,f,g,h,i,j,k,l,m
		   n,o,p,q,r,s,t,u,v,w,x,y,z
		*/
		
		

	}

}
