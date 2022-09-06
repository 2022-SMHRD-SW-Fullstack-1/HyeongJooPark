package Model;

public abstract class Level {
	
	private int time;
	private int score;
		
	public Level() {
		
	}
	
	public Level(int time, int score) {
		this.time = time;
		this.score = score;
	}
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
