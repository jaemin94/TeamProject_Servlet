package Domain.Common.Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import Domain.Common.Dto.MemberDto;

public interface MemberService {

	//회원 가입하기
	boolean memberJoin(HttpServletRequest req) throws Exception;

	List<MemberDto> memberSearch(String sid) throws Exception;

	MemberDto memberSearchOne(String sid, String id) throws Exception;

	MemberDto memberSearch(String id, String sid) throws Exception;

	//회원 수정하기 -- 본인확인
	boolean memberUpdate(MemberDto dto, String sid) throws Exception;

	//회원 삭제하기
	boolean memberDelete(String id, String sid) throws Exception;

	//로그인
	boolean login(HttpServletRequest req) throws Exception;

	//로그아웃
	void logout(String sid);

	//역할반환함수 
	String getRole(String sid);
	


}