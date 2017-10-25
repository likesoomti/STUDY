package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import domain.HG_Event;
import domain.HG_Facility;
import domain.User;
import util.ConnectDB;
import util.SHA256;

public class AdminDao {
private static AdminDao instance = new AdminDao();
	
	SHA256 sha = SHA256.getInsatnce();
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	String returns = "";

	public static AdminDao getInstance() {
		return instance;
	}

	
	//지구 리스트 뽑아낸다.
	public boolean Checked_Admin(String email,String password) throws Exception
	{
		String pwd = sha.getSha256(password.getBytes()); //pw 암호화
		
		User user = new User(email,password);
		try {

			ConnectDB connectDB = new ConnectDB();
			
		    System.out.println("접속에 성공!");
		    sql = "select * from User where user_email = ? and user_password =?;";
		    pstmt = connectDB.getConnection().prepareStatement(sql);
		    
		
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				user.setUser_role_id(rs.getString("User_role_id"));
				System.out.println("rsnex");
				
				int role = rs.getInt("user_role_id");
				
				if(role == 2){
					return true;
				}
			}
			
			
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return false;

	}
	public void event_create(String title, String content, String picture, String LOC, String start_date, String end_date)
	{
		try {

			
			ConnectDB connectDB = new ConnectDB();
			int rs;
		    System.out.println("접속에 성공!");
		    sql = "INSERT INTO HG_Event"
		    		+ "(Event_title,Event_content,Event_picture,Event_loc,Event_start_date,Event_end_date)"
		    		+ "VALUES (?,?,?,?,?,?)";
		    pstmt = connectDB.getConnection().prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, picture);
			pstmt.setString(4, LOC);
			pstmt.setString(5, start_date);
			pstmt.setString(6, end_date);
			pstmt.executeUpdate();
			
		    
		    rs = pstmt.executeUpdate();
			
			if(rs==1) {
				
				System.out.println("success");
							
			}
	
			
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
	}
	public List<HG_Event> admin_eventList(){
		
		Statement stmt = null;
		ResultSet rs = null;
		
		//JSONArray eventList = new JSONArray();
		List<HG_Event> eventList = new ArrayList<HG_Event>();
		
		try {
			String sql = "select * from HG_Event";
			
			ConnectDB connectDB = new ConnectDB();
			stmt = connectDB.getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				HG_Event event = new HG_Event();
				
				event.setEvent_id(rs.getInt("event_id"));
				event.setEvent_title(rs.getString("event_title"));
				event.setEvent_content(rs.getString("event_content"));
				event.setEvent_picture(rs.getString("event_picture"));
				event.setEvent_loc(rs.getString("event_loc"));
				event.setEvent_start_date(rs.getString("event_start_date"));
				event.setEvent_end_date(rs.getString("event_end_date"));
				
				eventList.add(event);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(stmt != null) {
				try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return eventList;
		
	}
	
	public boolean facility_create(String title,int category,String address,String phone,String profile,float lon,float lat,int fac_num,String content)
	{
		try {

			
			int rs;
		    System.out.println("접속에 성공!");
		    sql = "insert into hg_facility"
		    		+"(fac_name, category_no, fac_address, fac_phone, fac_profile, fac_loc_longi, fac_loc_lati,fac_place_no,FAC_Place_detail)"+
		    		"values(?,?,?,?,?,?,?,?,?)";

		    ConnectDB connectDB = new ConnectDB();
		    pstmt = connectDB.getConnection().prepareStatement(sql);
		    
			pstmt.setString(1, title);
			pstmt.setInt(2, category);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);
			pstmt.setString(5, profile);
			pstmt.setFloat(6, lon);
			pstmt.setFloat(7, lat);
			pstmt.setInt(8, fac_num);
			pstmt.setString(9, content);
			pstmt.executeUpdate();
			
		    
		    rs = pstmt.executeUpdate();
			
			if(rs==1) {
				
				System.out.println("success");
				return true;
							
			}
			
			return false;
	
			
		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();} catch (SQLException ex) {}
			if (rs != null)try {rs.close();} catch (SQLException ex) {}
		}
		return false;
	}
	public List<HG_Facility> admin_facilityList(){
		
		Statement stmt = null;
		ResultSet rs = null;

		//JSONArray eventList = new JSONArray();
		List<HG_Facility> faclist = new ArrayList<HG_Facility>();

		
		try {
			String sql = "select * from HG_Facility";
			
			ConnectDB connectDB = new ConnectDB();
			stmt = connectDB.getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				HG_Facility hg_Facility = new HG_Facility();
				
				hg_Facility.setFAC_id(rs.getInt("FAC_id"));
				hg_Facility.setFAC_name(rs.getString("FAC_name"));
				hg_Facility.setCategory_no(rs.getInt("Category_no"));
				hg_Facility.setFAC_Address(rs.getString("FAC_Address"));
				hg_Facility.setFAC_phone(rs.getString("FAC_phone"));
				hg_Facility.setFAC_Proifle(rs.getString("FAC_Profile"));
				hg_Facility.setFAC_LOC_Longi(rs.getDouble("fAC_LOC_Lati"));
				hg_Facility.setFAC_LOC_Lati(rs.getDouble("fAC_LOC_Lati"));
				hg_Facility.setFAC_Place_detail(rs.getString("fAC_Place_detail"));
				faclist.add(hg_Facility);
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(stmt != null) {
				try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return faclist;
		
	}
	
}
