package my.day16.c.INTERFACE_inheritance;

public class Main {

	public static void main(String[] args) {
		Child cd = new Child();
		cd.work();
		cd.cook();
		cd.play();
		
		
		System.out.println("\n----------------------------------- \n");
		
		 Inter_father father = new Child();
		 father.work();
		 
		 
		 Inter_mother  mother= new Child();
		 mother.cook();
		 
		 
		 System.out.println("\n----------------------------------- \n");
		 
		 Inter_child child = new Child();
		 child.work();
		 child.cook();
		 child.play();
			/*
			 * 보람차게 일합니다 !! 맛있는 요리를 합니다 !! 재미나게 놀아요 !!
			 * 
			 * -----------------------------------
			 * 
			 * 보람차게 일합니다 !! 맛있는 요리를 합니다 !!
			 * 
			 * -----------------------------------
			 * 
			 * 보람차게 일합니다 !! 맛있는 요리를 합니다 !! 재미나게 놀아요 !!
			 */
			
	} // end of main()
	
}
