package View;

import java.util.Scanner;

import Controller.MusicController;

public class MusicView {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MusicController mc = new MusicController();
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
					System.out.println("=======================");
					mc.next();
					continue;
				}else if(ans.equals("p")) {
					mc.next();
					round++;
					continue;
				}else {System.out.println("틀렸습니다!");
				System.out.println("남은 횟수 : "+count+"/3");	
				count--;
				}
			}
			if(count==0) {
				System.out.println("====================");
				System.out.println("You're dead");
			}else {
				System.out.println("====================");
				System.out.println("게임 종료");
			}
			//랭크 보여줄 자리
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
		System.out.println("초기화면으로 돌아갑니다");

	}

}