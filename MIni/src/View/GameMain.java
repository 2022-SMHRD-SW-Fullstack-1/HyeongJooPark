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
		int result = 0;	//sql 전송 체크

		while(true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹보기 [4]탈퇴 [5]종료 >> ");
			int input = sc.nextInt();

			if(input == 1) {
				System.out.println("=========회원가입=========");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();
				System.out.print("닉네임 : ");
				String nick = sc.next();			

				//INSERT 작업
				result = memcon.conInsert(id, pw, nick); //Controller에 전송
				if(result > 0) {
					System.out.println("회원 가입에 성공했습니다");
					result = 0;
				}else {
					System.out.println("가입 실패");
				}
				//MemberDTO → id, pw, nick → (DB) 회원가입

			}else if(input == 2) {
				//로그인
				System.out.print("아이디 : ");
				String id = sc.next();

				System.out.print("비밀번호 : ");
				String pw = sc.next();

				//로그인 성공시 회원의 닉네임 출력하기
				String nick = memcon.conLogin(id, pw);

				if(nick != null) {
					//로그인 후 화면 
					System.out.println("== 환영합니다! " + nick + "님 ==");
					System.out.println("\r\n"
							+ " _    _  _____  _      _____  _____ ___  ___ _____  _ \r\n"
							+ "| |  | ||  ___|| |    /  __ \\|  _  ||  \\/  ||  ___|| |\r\n"
							+ "| |  | || |__  | |    | /  \\/| | | || .  . || |__  | |\r\n"
							+ "| |/\\| ||  __| | |    | |    | | | || |\\/| ||  __| | |\r\n"
							+ "\\  /\\  /| |___ | |____| \\__/\\\\ \\_/ /| |  | || |___ |_|\r\n"
							+ " \\/  \\/ \\____/ \\_____/ \\____/ \\___/ \\_|  |_/\\____/ (_)\r\n"
							+ "                                                      \r\n"
							+ "                                                      \r\n"
							+ "");
					int question = 10; // 문제의 수
					
					while(true) {
						System.out.println("== 처음 오신 분이라면 게임설명보기를 선택해주세요 ==");
						System.out.print("[1]게임시작 [2]게임설명보기 [3]랭킹보기 >> ");
						int inputLog = sc.nextInt();

						if(inputLog == 1) {
							//게임화면
							int score = 0; //누적 점수
							int count = 3; //남은 생명
							int round = 1; //몇번째 문제인지
							boolean hintCheck = false;
							
							System.out.println("========난이도 선택========");
							System.out.print("[1]Normal [2]Hell >> ");
							mc.selectLevel(sc.nextInt());
							mc.GenerateRandomVariable();
							while(true) {
								if(round<=question) {
									System.out.println("== 노래가 끝나고 나면 정답을 입력해 주세요 ==");
									System.out.println("============ "+(round)+"번째 문제 ============");
									
									if(hintCheck==true) {						
										System.out.println("힌트 : " + mc.singer() +" - " + mc.hint());		
										hintCheck = false;
									}
									
									mc.play();
									mc.stop();				
									System.out.println("패스를 원한다면 p를 입력해주세요");			
									System.out.print("정답 입력 >> ");
									String ans = sc.next();
									if(ans.equals(mc.answer())) {
										score = mc.score(ans);
										round++;
										System.out.println("정답입니다!");
										System.out.println("현재 획득한 점수 : "+score);
										mc.next();
										continue;
									}else if(ans.equals("p")) {
										System.out.println("정답 : "+mc.answer());
										mc.next();
										round++;
										continue;
									}else {
										System.out.println("틀렸습니다!");
										count--;
										System.out.println("남은 횟수 : "+count+"/3");
										hintCheck=true;
																												
									}
									if(count==0) {
										System.out.println("정답 : "+ mc.singer() +" - " + mc.answer());
										System.out.println("==============================");
										System.out.println("You're dead");
										hintCheck = false;
									}
								}
								System.out.println("==============================");
								int totalScore = memcon.scoreInsert(id, score); //Controller에 전송

								//랭크 보여줄 자리
								if(round>question || count==0) {
									System.out.println("게임 종료");
									System.out.println("당신의 점수 : "+score);
									System.out.println();
									System.out.println("다시 도전하시겠습니까?");
									System.out.print("[1]Yes [2]No >> ");
									int retry = sc.nextInt();
									if(retry==1) {
										count=3;
										score=0;
										round=1;
										mc.GenerateRandomVariable();
										continue;
									}			
									else {
										break;
									}
									
								}
							}
							System.out.println("초기화면으로 돌아갑니다");
							break;

						}else if(inputLog == 2){
							//게임설명화면
							System.out.println();
							System.out.println("들리는 노래를 듣고 노래 제목을 맞춰주세요!");
							System.out.println("문제는 총 "+question+"문제이며 난이도별로 점수 배점이 다르게 들어갑니다");
							System.out.println("기회는 3번이고 틀리면 차감되며 패스는 기회는 줄어들지 않고 라운드 수는 올라갑니다");
							System.out.println("==========================================================================");
							System.out.println("난이도는 Normal, Hell 총 두 가지입니다.");
							System.out.print("Normal 모드는 "+mc.selectLevel(1).getTime()+"초 동안 노래를 들을 수 있으며, ");
							System.out.println("점수 배점은 문제당"+mc.selectLevel(1).getScore()+" 입니다.");
							System.out.print("Hell 모드는 "+mc.selectLevel(2).getTime()+"초 동안 노래를 들을 수 있으며, ");
							System.out.println("점수 배점은 문제당"+mc.selectLevel(2).getScore()+" 입니다.");
							System.out.println("==========================================================================");
							System.out.print("정답 입력은 한글과 숫자, 띄어쓰기 없이 입력 바랍니다\t");
							System.out.println("ex)love dive = 러브다이브, 비밀번호486");
							System.out.println("문제를 틀리고 재시도를 할때에는 노래제목 초성 힌트가 나갑니다");
							System.out.println("과연 당신은 하늘에 닿을 수 있을까요?");
							System.out.println();
							System.out.print("[1]돌아가기 [2]로그아웃 >> ");
							int helpSelect = sc.nextInt();
							if(helpSelect==1) { 
								continue;
							}else {
								System.out.println("초기화면으로 돌아갑니다");
								break;
							}	
						}else if(inputLog == 3) {
							ArrayList<MemberDTO> resultList = new ArrayList<>();

							resultList = memcon.conRank(); //Controller에 기능 요청

							//ArrayList 출력
							for(int i=0; i<resultList.size(); i++) {
								if(i==0) {
									System.out.println("\r\n"
											+ "      ::::::::       :::    :::    :::   ::: \r\n"
											+ "    :+:    :+:      :+:   :+:     :+:   :+:  \r\n"
											+ "   +:+             +:+  +:+       +:+ +:+    \r\n"
											+ "  +#++:++#++      +#++:++         +#++:      \r\n"
											+ "        +#+      +#+  +#+         +#+        \r\n"
											+ "#+#    #+#      #+#   #+#        #+#         \r\n"
											+ "########       ###    ###       ###          \r\n"
											+"======================================================\r\n"
											+(i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
											+resultList.get(i).getScore() + " / " + resultList.get(i).getDate()+"\r\n"
											+ "======================================================");
									System.out.println();
									
								}else {
									
									System.out.println((i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
											+ resultList.get(i).getScore() + " / " + resultList.get(i).getDate());
								}
							}System.out.println();
						
						}
					}
				}else {
					System.out.println("로그인에 실패 하셨습니다");
				}

			}else if(input == 3) {
				ArrayList<MemberDTO> resultList = new ArrayList<>();

				resultList = memcon.conRank(); //Controller에 기능 요청

				//ArrayList 출력
				for(int i=0; i<resultList.size(); i++) {
					if(i==0) {
						System.out.println("\r\n"
								+ "      ::::::::       :::    :::    :::   ::: \r\n"
								+ "    :+:    :+:      :+:   :+:     :+:   :+:  \r\n"
								+ "   +:+             +:+  +:+       +:+ +:+    \r\n"
								+ "  +#++:++#++      +#++:++         +#++:      \r\n"
								+ "        +#+      +#+  +#+         +#+        \r\n"
								+ "#+#    #+#      #+#   #+#        #+#         \r\n"
								+ "########       ###    ###       ###          \r\n"
								+"======================================================\r\n"
								+(i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
								+resultList.get(i).getScore() + " / " + resultList.get(i).getDate()+"\r\n"
								+ "======================================================");
						System.out.println();
						
					}else {
						
						System.out.println((i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
								+ resultList.get(i).getScore() + " / " + resultList.get(i).getDate());
					}
				}System.out.println();
				
			}else if(input == 4) {
				System.out.println("탈퇴할 아이디와 비밀번호를 입력하세요");
				System.out.print("아이디 : ");
				String id = sc.next();
				System.out.print("비밀번호 : ");
				String pw = sc.next();			

				//DELETE 작업
				result = memcon.conDelete(id, pw); //Controller에 전송			
				if(result > 0) {
					System.out.println("탈퇴에 성공했습니다");
					result = 0;
				}else {
					System.out.println("탈퇴 실패");
				}
				
			}else if(input == 5) {
				System.out.println("게임을 종료합니다");
				break;
			}
		}
	}
}
