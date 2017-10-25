package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	
	private static Connection connection;

	public ConnectDB() {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hplace", "root", "qwer1234");
			System.out.println("데이터베이스가 연결되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩에 실패했습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결에 실패했습니다.");
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {		
		return connection;		
	}

}
