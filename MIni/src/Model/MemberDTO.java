package Model;

public class MemberDTO {
	private String id;
	private String pw;
	private String nick;
	private int score;
	
	//conRank 사용시 ArrayList에 담을 nick, score 를 하나의 생성자로 만듬
	public MemberDTO(String nick, int score) {
		this.nick = nick;
		this.score = score;
	}
	public String getId() {
		return id;
	}
	public String getNick() {
		return nick;
	}
	
	
}
