package kr.or.connect.Todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.Todo.dto.TodoDto;

public class TodoDao {
	
	private static String dbUrl = "jdbc:mysql:/localhost:3306/projectdb";
	private static String dbUser = "";
	private static String dbPasswd = "";
	
	
	public int addTodo(TodoDto todo) {
		int insertCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";
		
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());
			
			insertCount = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return insertCount;
	}
	
	public List<TodoDto> getTodos() {
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT id, name, regDate, sequence, title, type FROM todo WHERE type = 'TODO' ORDER BY regdate desc";
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			try(ResultSet rs = ps.executeQuery()) {
				while(rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String regDate = rs.getString("regDate");
					int sequence = rs.getInt("sequence");
					String title = rs.getString("title");
					String type = rs.getString("type");
					TodoDto todo = new TodoDto(id, name, regDate, sequence, title, type);
					list.add(todo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public int updateTodo(TodoDto todo) {
		int updateCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
			String sql = "UPDATE todo SET type = ? WHERE id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,  todo.getType());
			ps.setLong(2,  todo.getId());
			updateCount = ps.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch(Exception ex) {}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {}
			}
		}
		return updateCount;
	}
}
