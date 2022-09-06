package Controller;

import java.util.ArrayList;

import Model.MemberDAO;
import Model.MemberDTO;

public class MemberCon {
	
	//컨트롤러 역할을 진행할 클래스
	
	//View ↔ DAO(DB의 로직)
	
	MemberDAO dao = new MemberDAO();
	
	int cnt = 0;
	
	public int conInsert(String id, String pw, String nick) {
		//view가 넘겨준 회원가입에 대한 정보들을 DAO로 연결해주는 메소드
		cnt = dao.insert(id, pw, nick);
		
		return cnt;
	}
	public int scoreInsert(String id, int score) {
		cnt = dao.saveRank(id, score);
		return cnt;
	}
	
	public ArrayList<MemberDTO> conRank() {
		
		ArrayList<MemberDTO> totalList = new ArrayList<>();
		
		totalList = dao.select();
		
		return totalList;
	}
	
	//로그인을 진행하기 위한 Controller 요청
	public String conLogin(String id, String pw) {
		
		String nick = dao.login(id, pw);
				
		return nick;
	}
	
	public int conDelete(String id, String pw) {
		//view가 넘겨준 회원가입에 대한 정보들을 DAO로 연결해주는 메소드
		cnt = dao.delete(id, pw);
		
		return cnt;
	}
	

}
