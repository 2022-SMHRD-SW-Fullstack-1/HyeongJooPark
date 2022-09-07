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

		System.out.println("'##::::'##:'##::::'##::'######::'####::'######::::::::::::'#######::'##::::'##:'####:'########:\r\n"
				+ " ###::'###: ##:::: ##:'##... ##:. ##::'##... ##::::::::::'##.... ##: ##:::: ##:. ##::..... ##::\r\n"
				+ " ####'####: ##:::: ##: ##:::..::: ##:: ##:::..::::::::::: ##:::: ##: ##:::: ##:: ##:::::: ##:::\r\n"
				+ " ## ### ##: ##:::: ##:. ######::: ##:: ##:::::::::::::::: ##:::: ##: ##:::: ##:: ##::::: ##::::\r\n"
				+ " ##. #: ##: ##:::: ##::..... ##:: ##:: ##:::::::::::::::: ##:'## ##: ##:::: ##:: ##:::: ##:::::\r\n"
				+ " ##:.:: ##: ##:::: ##:'##::: ##:: ##:: ##::: ##:::::::::: ##:.. ##:: ##:::: ##:: ##::: ##::::::\r\n"
				+ " ##:::: ##:. #######::. ######::'####:. ######:::::::::::: ##### ##:. #######::'####: ########:\r\n"
				+ "..:::::..:::.......::::......:::....:::......:::::::::::::.....:..:::.......:::....::........::");
		while(true) {
			System.out.println("\t=======*+:+    *    +:+*========");
			System.out.println("\t\t  [1] 회원가입");
			System.out.println("\t\t  [2] 로그인");
			System.out.println("\t\t  [3] 랭킹보기");
			System.out.println("\t\t  [4] 탈퇴");
			System.out.println("\t\t  [5] 종료");
			System.out.println("\t=======*+:+    *    +:+*========");
			System.out.print("\t\t번호를 입력하세요 >> ");
			int input = sc.nextInt();

			if(input == 1) {
				System.out.println("\t=======*+:+  회원가입  +:+*========");
				System.out.print("\t\t아이디 : ");
				String id = sc.next();
				System.out.print("\t\t비밀번호 : ");
				String pw = sc.next();
				System.out.print("\t\t닉네임 : ");
				String nick = sc.next();
				System.out.println("\t========*+:+    *    +:+*========");
				System.out.println();

				//INSERT 작업
				result = memcon.conInsert(id, pw, nick); //Controller에 전송
				if(result > 0) {
					System.out.println("\t　　　＼＼Yeah／／ \r\n"
							+ "\t　  ∧＿ﾍ　  ﾍ＿∧ \r\n"
							+ "\t　（／ω･)人(･ω＼ ) \r\n"
							+ "\t　／`　／　  ＼　`＼회원 가입에 성공했습니다");
					System.out.println();
					result = 0;
				}else {
					System.out.println("\t가입 실패");
					System.out.println();
					System.out.println();
				}
				//MemberDTO → id, pw, nick → (DB) 회원가입

			}else if(input == 2) {
				//로그인
				System.out.println("\t=======*+:+   로그인  +:+*=======");
				System.out.print("\t\t아이디 : ");
				String id = sc.next();

				System.out.print("\t\t비밀번호 : ");
				String pw = sc.next();
				System.out.println("\t========*+:+    *    +:+*========");
				System.out.println();
				System.out.println();

				//로그인 성공시 회원의 닉네임 출력하기
				String nick = memcon.conLogin(id, pw);

				if(nick != null) {
					//로그인 후 화면 
					System.out.println("                       888                                          888 \r\n"
							+ "                       888                                          888 \r\n"
							+ "                       888                                          888 \r\n"
							+ "888  888  888  .d88b.  888  .d8888b  .d88b.  88888b.d88b.   .d88b.  888 \r\n"
							+ "888  888  888 d8P  Y8b 888 d88P\"    d88\"\"88b 888 \"888 \"88b d8P  Y8b 888 \r\n"
							+ "888  888  888 88888888 888 888      888  888 888  888  888 88888888 Y8P \r\n"
							+ "Y88b 888 d88P Y8b.     888 Y88b.    Y88..88P 888  888  888 Y8b.      \"  \r\n"
							+ " \"Y8888888P\"   \"Y8888  888  \"Y8888P  \"Y88P\"  888  888  888  \"Y8888  888 ");
					System.out.println("======================= 환영합니다! " + nick + "님 =======================");
					System.out.println();
					System.out.println();
					int question = 10; // 문제의 수
					
					while(true) {
						System.out.println("\t===*+:+처음 오신 분이라면 게임설명보기를 선택해주세요+:+*===");
				
						System.out.print("\t\t[1]게임시작 [2]게임설명보기 [3]랭킹보기 >> ");
						
						int inputLog = sc.nextInt();
						System.out.println();
						System.out.println();
						if(inputLog == 1) {
							//게임화면
							int score = 0; //누적 점수
							int count = 3; //남은 생명
							int round = 1; //몇번째 문제인지
							boolean hintCheck = false;
							System.out.println("\t==========*+:+    난이도 선택     +:+*============");
							System.out.println();
							System.out.print("\t\t[1]Normal [2]Hell >> ");
							mc.selectLevel(sc.nextInt());
							System.out.println();
							System.out.println("\t==============*+:+    *    +:+*================");
							System.out.println();
							mc.GenerateRandomVariable();
							while(true) {
								if(round<=question) {
									System.out.println();
									System.out.println("\t==*+:+노래가 끝나고 나면 정답을 입력해주세요+:+*==");
									
									System.out.println();
									System.out.println("\t===============*+:+"+(round)+"번째 문제+:+*=================");
									System.out.println();
									if(hintCheck==true) {						
										System.out.println("\t\t힌트 : " + mc.singer() +" - " + mc.hint());		
										hintCheck = false;
									}
									
									mc.play();
									mc.stop();
									System.out.println("\t\t패스를 원한다면 p를 입력해주세요");			
									System.out.print("\t\t정답 입력 >> ");
									String ans = sc.next();
									System.out.println();

									if(ans.equals(mc.answer())) {
										score = mc.score(ans);
										round++;
										System.out.println("\t\t정답입니다!");
										System.out.println("\t\t정답 : "+ mc.singer()+" - " +mc.answer());
										System.out.println("\t\t현재 획득한 점수 : "+score);
										mc.next();
										System.out.println();
										System.out.println();
										continue;
									}else if(ans.equals("p")) {
										System.out.println("\t\t정답 : "+ mc.singer()+" - " +mc.answer());
										mc.next();
										System.out.println();
										round++;
										continue;
									}else {
										System.out.println("\t\t틀렸습니다!");
										count--;
										System.out.println("\t\t남은 횟수 : "+count+"/3");
										System.out.println();
										hintCheck=true;
																												
									}
									if(count==0) {
										System.out.println("\t\t정답 : "+ mc.singer() +" - " + mc.answer());
										System.out.println();
										System.out.println();
										System.out.println("\t=============*+:+    *     +:+*================");
										System.out.println();
										System.out.println("\t|||\t\tYou're dead\t\t|||");
										System.out.println();
										hintCheck = false;
									}
								}
								 
								System.out.println("\t=============*+:+    *     +:+*================");
								int totalScore = memcon.scoreInsert(id, score); //Controller에 전송

								//랭크 보여줄 자리
								if(round>question || count==0) {
									System.out.println("\t\t게임 종료");
									System.out.println("\t\t당신의 점수 : "+score);
									System.out.println();
									System.out.println();
									System.out.println("\t\t다시 도전하시겠습니까?");
									System.out.print("\t\t[1]Yes [2]No >> ");
									int retry = sc.nextInt();
									System.out.println();
									System.out.println();
									
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
							System.out.println("\t\t초기화면으로 돌아갑니다");
							System.out.println();
							
							break;

						}else if(inputLog == 2){
							//게임설명화면
							System.out.println("\t====================*+:+     *    +:+*=======================");
							System.out.println();
							System.out.println("\t들리는 노래를 듣고 노래 제목을 맞춰주세요!");
							System.out.println("\t문제는 총 "+question+"문제이며 난이도별로 점수 배점이 다르게 들어갑니다");
							System.out.println("\t기회는 3번이고 틀리면 차감되며 패스는 기회는 줄어들지 않고 라운드 수는 올라갑니다");
							System.out.println();
							System.out.println();
							System.out.println("\t난이도는 Normal, Hell 총 두 가지입니다.");
							System.out.print("\tNormal 모드는 "+mc.selectLevel(1).getTime()+"초 동안 노래를 들을 수 있으며, ");
							System.out.println("점수 배점은 문제당 "+mc.selectLevel(1).getScore()+"점 입니다.");
							System.out.print("\tHell 모드는 "+mc.selectLevel(2).getTime()+"초 동안 노래를 들을 수 있으며, ");
							System.out.println("점수 배점은 문제당 "+mc.selectLevel(2).getScore()+"점 입니다.");
							System.out.println();
							System.out.println();
							System.out.print("\t정답 입력은 한글과 숫자, 띄어쓰기 없이 입력 바랍니다\t");
							System.out.println("ex)love dive = 러브다이브, 비밀번호486");
							System.out.println("\t문제를 틀리고 재시도를 할때에는 노래제목 초성 힌트가 나갑니다");
							System.out.println("\t과연 당신은 하늘에 닿을 수 있을까요?");
							System.out.println();
							System.out.println("\t====================*+:+    *    +:+*=======================");
							System.out.println();
							System.out.print("\t[1]돌아가기 [2]로그아웃 >> ");
							int helpSelect = sc.nextInt();
							System.out.println();
							System.out.println();
							if(helpSelect==1) { 
								continue;
							}else {
								System.out.println("\t초기화면으로 돌아갑니다");
								System.out.println();
								System.out.println();
								break;
							}	
						}else if(inputLog == 3) {
							ArrayList<MemberDTO> resultList = new ArrayList<>();

							resultList = memcon.conRank(); //Controller에 기능 요청

							//ArrayList 출력
							for(int i=0; i<resultList.size(); i++) {
								if(i==0) {
									System.out.println("\r\n"
											+ "\t      ::::::::       :::    :::    :::   ::: \r\n"
											+ "\t    :+:    :+:      :+:   :+:     :+:   :+:  \r\n"
											+ "\t   +:+             +:+  +:+       +:+ +:+    \r\n"
											+ "\t  +#++:++#++      +#++:++         +#++:      \r\n"
											+ "\t        +#+      +#+  +#+         +#+        \r\n"
											+ "\t#+#    #+#      #+#   #+#        #+#         \r\n"
											+ "\t########       ###    ###       ###          \r\n"
											+"======================================================\r\n"
											+"\t"+(i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
											+resultList.get(i).getScore() + " / " + resultList.get(i).getDate()+"\r\n"
											+ "======================================================");
									System.out.println();
									
								}else {
									
									System.out.println("\t"+(i+1)+"등 " + resultList.get(i).getNick()+ "님의 점수 : "
											+ resultList.get(i).getScore() + " / " + resultList.get(i).getDate());
								}
							}System.out.println();
							System.out.println();
						
						}
					}
				}else {
					System.out.println("\t로그인에 실패 하셨습니다");
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
				System.out.println("\t탈퇴할 아이디와 비밀번호를 입력하세요");
				System.out.print("\t아이디 : ");
				String id = sc.next();
				System.out.print("\t비밀번호 : ");
				String pw = sc.next();			

				//DELETE 작업
				result = memcon.conDelete(id, pw); //Controller에 전송			
				if(result > 0) {
					System.out.println("\t탈퇴에 성공했습니다");
					result = 0;
				}else {
					System.out.println("\t탈퇴 실패");
				}
				
			}else if(input == 5) {
				System.out.println("\t게임을 종료합니다");
				break;
			}
		}
	}
}
