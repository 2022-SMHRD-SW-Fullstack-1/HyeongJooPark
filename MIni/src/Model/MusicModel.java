package Model;

public class MusicModel {
	
	private String songName;
	private String singer;
	private int playTime;
	private String musicPath;

	public MusicModel() {
		
	}
	
	public MusicModel(String songName, String singer, int playTime, String musicPath) {
		this.songName = songName;
		this.singer = singer;
		this.playTime = playTime;
		this.musicPath = musicPath;
	}

	public String getSongName() {
		return songName;
	}

	public String getSinger() {
		return singer;
	}

	public int getPlayTime() {
		return playTime;
	}

	public void setPlayTime(int playTime) {
		this.playTime = playTime;
	}

	public String getMusicPath() {
		return musicPath;
	}

	
}
