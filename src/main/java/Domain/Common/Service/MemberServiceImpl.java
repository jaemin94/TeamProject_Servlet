package Domain.Common.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.MemberDao;
import Domain.Common.Dao.MemberDaoimpl;
import Domain.Common.Dto.MemberDto;
import Domain.Common.Service.Auth.Session;

public class MemberServiceImpl implements MemberService {

	
	//세션정보저장
	public Map<String,Session> sessionMap;
	
	private MemberDao dao;

	
	
	//싱글톤
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance==null)
			instance = new MemberServiceImpl();
		return instance;
	}
	
	
	public MemberServiceImpl() {
		dao=MemberDaoimpl.getInstance();
		sessionMap=new HashMap();
	}
	
	//회원 가입하기
	
	public boolean memberJoin(HttpServletRequest req) throws Exception {
		
		System.out.println("MEMBERJOIN!");
		String name = (String)req.getParameter("name");
		String adr_addr = (String)req.getParameter("adr_addr");
		String id = (String)req.getParameter("member_id");
		String pw = (String)req.getParameter("pw");
		
		int isDupulicate = dao.isDupulicate(id);
		if(isDupulicate == 0)
		{
			MemberDto dto = new MemberDto(id,pw,name,adr_addr,"Role_user");
			int result = dao.insert(dto);
			if(result>0)
				return true;
		}
		
		return false;
	}
	
	
	// 회원 전체 조회(관리자)
	public List<MemberDto> memberSearch(String sid) throws Exception{
		
		String role = sid;
		
		if(role.equals("Role_Member"))		
			return dao.select();
		return null;
	}
	
	// 회원 단건 조회(회원)
	public MemberDto memberSearchOne(String sid,String id) throws Exception{
		
		String role = sid;
//		Session session = sessionMap.get(sid);
//	
//        if(session != null)
		if(role.equals("Role_Member"))
            return dao.select(id);
		else if(role.equals("Role_user"))
		{
			return dao.select(id);
		}
		else
		{
			return null;
		}
		
	}
	
	
	 
	// 회원 단건 조회(관리자)
	public MemberDto memberSearch(String id,String sid) throws Exception {
		String role = sid;
		
		if(role.equals("Role_Member"))
			return dao.select(id);
		
		return null;
	}
	
	
	//회원 수정하기 -- 본인확인
	public boolean memberUpdate(MemberDto dto,String sid) throws Exception{
		
		Session session = sessionMap.get(sid);
		if(session!=null && session.getId().equals(dto.getId()))
		{
			int result = dao.update(dto);
			if(result>0)
				return true;
		}
		
		
		return false;
	}	
	
	//회원 삭제하기
	public boolean memberDelete(String id,String sid) throws Exception{
		
		Session session = sessionMap.get(sid);
		if(session!=null && session.getId().equals(id))
		{
			int result = dao.delete(id);
			if(result>0)
				return true;
		}

		return false;
	}
	
	
	//로그인
	public boolean login(HttpServletRequest req) throws Exception{
		String id = (String) req.getParameter("id");
		String pw = (String) req.getParameter("pw");
		
		//1 ID/PW 체크 ->Dao 전달받은 id와 일치하는 정보를 가져와서 Pw일치 확인
		MemberDto dbDto = dao.select(id);
		if(dbDto==null) {
			System.out.println("[ERROR] Login Fail... 아이디가 일치하지 않습니다");
			req.setAttribute("msg", "[ERROR] Login Fail... 아이디가 일치하지 않습니다");
			return false;
		}
		if(!pw.equals(dbDto.getPw())) {
			System.out.println("[ERROR] Login Fail... 패스워드가 일치하지 않습니다");
			req.setAttribute("msg", "[ERROR] Login Fail... 패스워드가 일치하지 않습니다");
			return false;
		}
		//2 사용자에대한 정보(Session)을 MemberService에 저장
		
		HttpSession session = req.getSession();
		session.setAttribute("ID", id);
		System.out.println("ID: " + id);
		session.setAttribute("ROLE", dbDto.getRole());
		System.out.println("Session : " + session);
		
		
		return true;
	}
	
	//로그아웃
	public void logout(String sid) {
		sessionMap.remove(sid);

	}
	

	//역할반환함수 
public String getRole(String sid) {
		
		Session session = sessionMap.get(sid);
		System.out.println("getRole's Session : " + session);
		if(session!=null) {
			return session.getRole();
			}
		
		return null;
	
	}



}
