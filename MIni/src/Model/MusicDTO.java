package Model;

public class MusicDTO {
	
	private String path;
	private String singer;
	private String title;
	private String head;
	
	public MusicDTO(String path, String singer, String title, String head) {
		this.path = path;
		this.singer = singer;
		this.title = title;
		this.head = head;
	}
	
	public String getPath() {
		return path;
	}
	public String getSinger() {
		return singer;
	}
	public String getTitle() {
		return title;
	}
	public String getHead() {
		return head;
	}

}
