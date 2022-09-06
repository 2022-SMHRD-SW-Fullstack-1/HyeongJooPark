package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int cnt = 0;
	
	public void getCon() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "campus_k_0830_4";
			String db_pw = "smhrd4";
			
			conn = DriverManager.getConnection(url, db_id, db_pw);
			
//		if(conn!=null) {
//			System.out.println("접속 성공");
//		}else {
//			System.out.println("접속 실패");
//		}
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs!=null) {
				rs.close();
			}if(psmt!=null) {
				psmt.close();
			}if(conn!=null) {
				conn.close();
			}	
	}catch(SQLException e) {
		e.printStackTrace();
	}
	}
	
	public int insert(String id, String pw, String nick) {
		getCon();
		try {
			String sql = "insert into user_info values(?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setString(3, nick);
			
			cnt = psmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return cnt;
	}
		
	public String login(String id, String pw) {
		getCon();
		String nick = null;
		try {
			String sql = "select nick from user_info where id=? and pw=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				nick=rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}return nick;
	}
	
	public int saveRank(String id, int score) {
		getCon();
		try {
			String sql = "insert into user_score values(?, ?, sysdate)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, score);
			
			cnt = psmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return cnt;
	}
	//랭킹보기
	public ArrayList<MemberDTO> select(){
		ArrayList<MemberDTO> totalList = new ArrayList<>();
		getCon();
		
		try {
			String sql = "SELECT nick, score, res_date FROM (SELECT i.nick, s.score, s.res_date FROM user_info i, user_score s WHERE i.id = s.id ORDER BY s.score desc) WHERE rownum < 11";

			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				String nick = rs.getString("nick");
				int score = rs.getInt("score");
				String res_date = rs.getString("res_date");
				
				MemberDTO dto = new MemberDTO(nick, score, res_date);
				totalList.add(dto);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return totalList;
	}
	
	
	public String giveHint(String songName) {// 힌트출력
		getCon();
		String head="";
		try {
			String sql = "select head from musiclist where title=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, songName);
			
			rs = psmt.executeQuery();
			while(rs.next()) {
				String path = rs.getString("path");
				String title = rs.getString("title");
				String singer = rs.getString("singer");
				head = rs.getString("head");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return head;
	}
	
	
	
		
	}
	
