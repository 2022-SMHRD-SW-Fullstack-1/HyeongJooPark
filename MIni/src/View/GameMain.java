package View;

import java.util.ArrayList;
import java.util.Scanner;

import Controller.MemberCon;
import Controller.MusicController;
import Model.MemberDTO;

public class GameMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		//사용자에게 보여지는 화면 작성

		//Controller에게 접근을 위한 객체 생성
		MemberCon memcon = new MemberCon();
		MusicController mc = new MusicController();

		int result = 0;

		while(true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹보기 [4]종료 >> ");
			int input = sc.nextInt();

			if(input == 1) {
				System.out.println("======회원가입======");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호(숫자로만입력) : ");
				String pw = sc.next();
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
					
					//로그인 후 화면 
					System.out.print("[1]게임시작 [2]게임설명보기 >> ");
					int inputLog = sc.nextInt();
					
					if(inputLog == 1) {
						//게임화면
						
						int score = 0; //누적 점수
						int count = 3; //남은 생명
						int round = 1; //몇번째 문제인지
						System.out.println("========난이도 선택========");
						System.out.print("[1]Easy [2]Normal [3]Hard >> ");
						mc.selectLevel(sc.nextInt());
						mc.GenerateRandomVariable();
						while(true) {
							if(round<=3) {
								mc.play();
								mc.stop();				
								System.out.println("========"+(round)+"번째 문제========");
								System.out.println("패스를 원할 시 p를 입력하세요");
								System.out.print("정답 입력 >>");
								String ans = sc.next();
								if(ans.equals(mc.answer())) {
									score = mc.score(ans);
									round++;
									System.out.println("정답입니다!");
									System.out.println("현재 획득한 점수 : "+score);
									mc.next();
									continue;
								}else if(ans.equals("p")) {
									mc.next();
									round++;
									continue;
								}else {
									System.out.println("틀렸습니다!");
									count--;
									System.out.println("남은 횟수 : "+count+"/3");	
								}
								if(count==0) {
									System.out.println("====================");
									System.out.println("You're dead");
								}
							}
							System.out.println("====================");
							result = memcon.scoreInsert(id, score); //Controller에 전송
							if(result > 0) {
								System.out.println("점수 저장 성공");
							}else {
								System.out.println("점수 저장 실패");
							}
							//랭크 보여줄 자리
							if(round>3) {
								System.out.println("게임 종료");
								System.out.println("다시 도전하시겠습니까?");
								System.out.print("[1]Yes [2]No");
								int retry = sc.nextInt();
								if(retry==1) {
									count=3;
									score=0;
									round=1;
									mc.GenerateRandomVariable();
									continue;
								}			
								break;
							}
						}
						System.out.println("초기화면으로 돌아갑니다");
						
						
						
					}else if(inputLog == 2) {
						//게임설명화면
						
						
						
					}
				}else {
					System.out.println("로그인에 실패 하셨습니다");
				}

			}else if(input == 3) {
				ArrayList<MemberDTO> resultList = new ArrayList<>();
				
				resultList = memcon.conRank(); //Controller에 기능 요청
	
				//ArrayList 출력
				for(int i=0; i<resultList.size(); i++) {
					System.out.println((i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
							+ resultList.get(i).getScore() + " / " + resultList.get(i).getDate());
				}
			}else if(input == 4) {
				break;
			}

		}

	}

}
