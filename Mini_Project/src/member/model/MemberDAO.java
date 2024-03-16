package member.model;

import java.util.Map;

import member.domain.MemberDTO;

public interface MemberDAO {

	MemberDTO login(Map<String, String> paraMap); // 로그인 처리를 하게 해주는 메소드



	



}
