package jdbc.day03;

import java.util.Map;

public interface MemberDAO {
	
	// 회원가입(insert) 메소드
	int memberRegister(MemberDTO member); // 한 행을 받아야한다 ,, 리턴 타입은 int가 되어져야한다 executeUpdate가 나왔다 

	// 로그인처리(select) 메소드
	MemberDTO login(Map<String, String> paraMap);
	
	
}
