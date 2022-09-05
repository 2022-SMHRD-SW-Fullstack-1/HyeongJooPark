package Model;

public class MemberDTO {
	private String id;
	private String pw;
	private String nick;

	
	
	public MemberDTO(String id, String nick) {
		this.id = id;
		this.nick = nick;
	}
	public String getId() {
		return id;
	}
	public String getNick() {
		return nick;
	}
	
	
}
