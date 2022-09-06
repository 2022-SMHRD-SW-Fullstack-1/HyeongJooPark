package Model;

public class MemberDTO {
	
	private String id;
	private String pw;
	private String nick;
	private int score;
	private String date;
	
	//conRank 사용시 ArrayList에 담을 nick, score 를 하나의 생성자로 만듬
	public MemberDTO(String nick, int score, String date) {
		this.nick = nick;
		this.score = score;
		this.date = date;
	}
	public String getId() {
		return id;
	}
	public String getNick() {
		return nick;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getDate() {
		return date;
	}
}
