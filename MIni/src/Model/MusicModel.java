package Model;

import java.util.ArrayList;

public class MusicModel {
	MemberDAO md = new MemberDAO();
	
	private String songName;
	private String singer;
	private String musicPath;
	private String head;


	public MusicModel() {
		
	}
	
	public MusicModel(String musicPath, String singer, String songName, String head) {
		this.songName = songName;
		this.singer = singer;
		this.musicPath = musicPath;
		this.head = head;
	}

	public String getSongName() {
		return songName;
	}

	public String getSinger() {
		return singer;
	}

	public String getMusicPath() {
		return musicPath;
	}
	
	public String getHead() {
		return head;
	}
	public ArrayList<MusicDTO> select(){
		ArrayList<MusicDTO> mList = new ArrayList<>();
		md.getCon();
		
		try {
			String sql = "SELECT path, singer, title, head FROM musiclist";

			md.psmt = md.conn.prepareStatement(sql);
			md.rs = md.psmt.executeQuery();
			while(md.rs.next()) {
				String path = md.rs.getString("path");
				String title = md.rs.getString("title");
				String singer = md.rs.getString("singer");
				String head = md.rs.getString("head");
				
				MusicDTO mdto = new MusicDTO(path, singer, title, head);
				mList.add(mdto);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
//		finally {
//			close();
//		}
		return mList;
	}

	
}
