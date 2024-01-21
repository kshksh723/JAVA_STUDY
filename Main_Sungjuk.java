package my.day02.a.dataType;

public class Main_Sungjuk {

	public static void main(String[] args) {
		
		Sungjuk lss_sungjuk = new Sungjuk();
		lss_sungjuk.hakbun = "091234";
		lss_sungjuk.name = "이순신";
		lss_sungjuk.kor = 68;
		lss_sungjuk.eng = 96;
		lss_sungjuk.math = 100;
		
		Sungjuk eom_sungjuk = new Sungjuk();
		eom_sungjuk.hakbun = "109876";
		eom_sungjuk.name = "엄정화";
		eom_sungjuk.kor = 78;
		eom_sungjuk.eng = 88;
		eom_sungjuk.math = 95;
		
		lss_sungjuk.sungjuk_print();
		/*
		    === 이순신님의 성적결과 ===
		    1.학번 : 091234
		    2.성명 : 이순신
		    3.국어 : 68
		    4.영어 : 96
		    5.수학 : 100 
		  
		*/
		
		eom_sungjuk.sungjuk_print();
		/*
	        === 엄정화님의 성적결과 ===
	        1.학번 : 109876
		    2.성명 : 엄정화
		    3.국어 : 78
		    4.영어 : 88
		    5.수학 : 95 
	    */
		

	}

}
