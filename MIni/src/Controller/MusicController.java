package Controller;

import java.util.ArrayList;
import java.util.Random;

import Model.Hell;
import Model.Level;
import Model.MusicModel;
import Model.Normal;
import javazoom.jl.player.MP3Player;

public class MusicController {
	
	MusicModel mm = new MusicModel();
	
	Level LV = new Level() {
	};
	
//	ArrayList<MusicModel> musiclist = new ArrayList<>();
	Random rd = new Random();
	MP3Player mp3 = new MP3Player();
	
	int round=0;
	int sum=0;
	int [] rdVal = new int[239];

	public void GenerateRandomVariable() {
		for(int i=0;i<239;i++) {
			rdVal[i]=rd.nextInt(239);
			for(int j=0;j<i;j++) {
				if(rdVal[i]==rdVal[j]) {
					i--;
				}
			}
		}
	}
	public MusicController() {
		mm.select();
//		for(int i = 0; i<56 ; i++) {
//			musiclist.add(new MusicModel(musiclist.get(i).getMusicPath(), musiclist.get(i).getSinger(), musiclist.get(i).getSongName(), musiclist.get(i).getHead()));
							
	}
	
	public Level selectLevel(int selectL) {
		if(selectL==1) {
			LV = new Normal();
		}else if(selectL==2) {
			LV = new Hell();
		}
		return LV;
	}
	
	//문제 출제
	public void play() {
		mp3.play(mm.select().get(rdVal[round]).getPath());
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
		}else if(round<mm.select().size()-1) {
			round++;
			mp3.play(mm.select().get(rdVal[round]).getPath());
		}
	}
	
	public String answer() {
		return mm.select().get(rdVal[round]).getTitle();
	}
	
//	public String hint() {
//		
//	}
	
	public int score(String ans) {
		sum+=LV.getScore();
		return sum;
	}


}

