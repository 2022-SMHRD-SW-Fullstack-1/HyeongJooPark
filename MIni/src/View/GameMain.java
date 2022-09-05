package View;

import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		//사용자에게 보여지는 화면 작성
		
		//Controller에게 접근을 위한 객체 생성
		MemberCon memcon = new MemberCon();
		
		int result = 0;
		
		while(true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹보기 [4]종료 >> ");
			int input = sc.nextInt();
			
			if(input == 1) {
				System.out.println("======회원가입======");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호(숫자로만입력) : ");
				int pw = sc.nextInt();
				System.out.print("닉네임 : ");
				String nick = sc.next();			
				
				//INSERT 작업
				result = memcon.conInsert(id, pw, nick); //Controller에 전송
				if(result > 0) {
					System.out.println("가입 성공");
				}else {
					System.out.println("가입 실패");
				}
				//MemberDTO → id, pw, nick → (DB) 회원가입
				
			}else if(input == 2) {
				//로그인
				System.out.print("아이디 : ");
				String id = sc.next();
				
				System.out.println("비밀번호 : ");
				String pw = sc.next();
				
				//로그인 성공시 회원의 닉네임 출력하기
				String nick = memcon.conLogin(id, pw);
				
				if(nick != null) {
					System.out.println("환영합니다! " + nick + " 님");					
				}else {
					System.out.println("로그인에 실패 하셨습니다");
				}
				
				//로그인 후 화면 
				System.out.print("[1]게임시작 [2]게임설명보기 >> ");
				int inputLog = sc.nextInt();
				
				if(inputLog == 1) {
					//게임화면
					
					
					
				}else if(inputLog == 2) {
					//게임설명화면
					
					
					
				}
				
			}else if(input == 3) {
				//랭킹보기 rownum으로 전체회원 점수 10위까지 조회
				memcon.conRank();
				
			}else if(input == 4) {
				break;
			}
					
		}
		
	}

}
