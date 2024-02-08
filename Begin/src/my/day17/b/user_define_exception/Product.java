package my.day17.b.user_define_exception;

public interface Product {
	
	// 주문받기 1
	void order(int jumun_su) throws Jango_lack_Exception;
	
	
	// 주문받기 2
	void jumun(int jumun_su) throws Jango_lack_Exception;
}
