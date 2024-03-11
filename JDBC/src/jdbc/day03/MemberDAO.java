package jdbc.day03;

import java.util.List;
import java.util.Map;
//import java.util.Scanner;

public interface MemberDAO {
	
	// 회원가입(insert) 메소드
	int memberRegister(MemberDTO member); // 한 행을 받아야한다 ,, 리턴 타입은 int가 되어져야한다 executeUpdate가 나왔다 

	// 로그인처리(select) 메소드
	MemberDTO login(Map<String, String> paraMap);

	// 회원탈퇴 
	int memberDelete(int userseq);

	//모든 회원을 조회해주는 메소드 
	List<MemberDTO> showAllMember();
	
	
	
}
