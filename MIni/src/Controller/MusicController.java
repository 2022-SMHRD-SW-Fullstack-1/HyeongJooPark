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
	int [] rdVal = new int[273];

	public void GenerateRandomVariable() {
		for(int i=0;i<273;i++) {
			rdVal[i]=rd.nextInt(273);
			for(int j=0;j<i;j++) {
				if(rdVal[i]==rdVal[j]) {
					i--;
				}
			}
		}
	}
	public MusicController() {
		mm.select();							
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
		if(mp3.isPlaying()==true){
			mp3.stop();
		}	
		mp3.play(mm.select().get(rdVal[round]).getPath());
	}

	//다음 문제 출제
	public void next() {
		if(mp3.isPlaying()==true) {
			mp3.stop();
		}
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
		if(mp3.isPlaying()==true) {
			mp3.stop();
		}else if(round<mm.select().size()-1) {
			round++;
			mp3.play(mm.select().get(rdVal[round]).getPath());
		}
	}
	
	public String answer() {
		return mm.select().get(rdVal[round]).getTitle();
	}
	
	public String singer() {
		return mm.select().get(rdVal[round]).getSinger();
	}
	
	public String hint() {
		return mm.select().get(rdVal[round]).getHead();
	}
	
	public int score(String ans) {
		sum+=LV.getScore();
		return sum;
	}


}

