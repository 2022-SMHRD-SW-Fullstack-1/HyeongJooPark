package Music;

import java.util.ArrayList;
import java.util.Random;
import javazoom.jl.player.MP3Player;

public class MusicController {

   ArrayList<MusicModel> musiclist = new ArrayList<>();
   Random rd = new Random();
   MP3Player mp3 = new MP3Player();
   
   int num=0;
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
   
   //문제 출제
   public void play() {
   if(num<3) {
	   mp3.play(musiclist.get(rdVal[num]).getMusicPath());
   }else System.out.println("문제를 모두 풀었습니다");
   }
   //다음 문제 출제
   public void next() {
	   num++;
   }
   //정지
   public void stop() {
      mp3.stop();
   }
   //패스
   public void pass() {
      if(mp3.isPlaying()) {
         mp3.stop();
      }
      if(num<musiclist.size()-1) {
         num++;
         mp3.play(musiclist.get(rdVal[num]).getMusicPath());
      }else System.out.println("문제를 모두 풀었습니다");
   
 
}

   public String answer() {
      return musiclist.get(rdVal[num]).getSongName();
   }
      

}

