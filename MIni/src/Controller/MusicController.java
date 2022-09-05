package Controller;

import java.util.ArrayList;
import java.util.Random;

import Model.Easy;
import Model.Hard;
import Model.Level;
import Model.MusicModel;
import Model.Normal;
import javazoom.jl.player.MP3Player;

public class MusicController {
	
	Level LV = new Level() {
	};
	
	ArrayList<MusicModel> musiclist = new ArrayList<>();
	Random rd = new Random();
	MP3Player mp3 = new MP3Player();
	
	int round=0;
	int [] rdVal = new int[3];

	public void GenerateRandomVariable() {
		for(int i=0;i<3;i++) {
			rdVal[i]=rd.nextInt(3);
			for(int j=0;j<i;j++) {
				if(rdVal[i]==rdVal[j]) {
					i--;
				}
			}
		}
	}

	public MusicController() {
		musiclist.add(new MusicModel("롤리폴리","티아라",19,"music/rolypoly.mp3"));
		musiclist.add(new MusicModel("애프터라이크","아이브",19,"music/afterlike.mp3"));
		musiclist.add(new MusicModel("라일락","아이유",20,"music/lilac.mp3"));
	}
	
	public Level selectLevel(int selectL) {
		if(selectL==1) {
			LV = new Easy();
		}else if(selectL==2) {
			LV = new Normal();
		}else if(selectL==3) {
			LV = new Hard();
		}
		return LV;
	}
	
	//문제 출제
	public void play() {
		if(round<3) {
			mp3.play(musiclist.get(rdVal[round]).getMusicPath());
		}else System.out.println("문제를 모두 풀었습니다");
	}

	//다음 문제 출제
	public void next() {
		round++;
	}

	//정지
	public void stop() {
		try {
			Thread.sleep(LV.getTime()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mp3.stop();
	}

	//패스
	public void pass() {
		if(mp3.isPlaying()) {
			mp3.stop();
		}
		if(round<musiclist.size()-1) {
			round++;
			mp3.play(musiclist.get(rdVal[round]).getMusicPath());
		}else System.out.println("문제를 모두 풀었습니다");
	}
	
	public String answer() {
		return musiclist.get(rdVal[round]).getSongName();
	}
	
	public int score(String ans) {
		int sum=LV.getScore();
		
		if(ans.equals(answer())) {
			sum = +LV.getScore();
		}
		
		return sum;
	}


}

