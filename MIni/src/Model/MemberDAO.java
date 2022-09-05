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
			
		if(conn!=null) {
			System.out.println("접속 성공");
		}else {
			System.out.println("접속 실패");
		}
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
	
	public int insert(String id, int pw, String nick) {
		getCon();
		try {
			String sql = "insert into user_info values(?, ?, ?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, pw);
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
		
	public String login(String id, int pw) {
		getCon();
		String nick = null;
		try {
			String sql = "select nick from user_info where id=? and pw=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setInt(2, pw);
			
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
	//랭킹보기
	public ArrayList<MemberDTO> select(){
		ArrayList<MemberDTO> totalList = new ArrayList<>();
		getCon();
		
		try {
			String sql = "SELECT nick, score\r\n"
					+ "FROM (SELECT nick, score\r\n"
					+ "	FROM user_score\r\n"
					+ "	ORDER BY score desc)\r\n"
					+ "WHERE rownum<11;";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				String nick = rs.getString("nick");
				int score = rs.getInt("score");
				
				MemberDTO dto = new MemberDTO(nick, score);
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
	
	
	
	
	
	
		
	}
	
