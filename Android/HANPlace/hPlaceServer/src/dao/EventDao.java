package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import domain.HG_Event;
import util.ConnectDB;

public class EventDao {
	
	public String eventList() throws Exception {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		//JSONArray eventList = new JSONArray();
		List<HG_Event> eventList = new ArrayList<HG_Event>();
		Gson gsonEventList = new Gson();
		String jsonEventList = null;
		
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
			
			jsonEventList = gsonEventList.toJson(eventList);
			
		} catch (SQLException e) {
			System.out.println("제품 목록 보기에서 예외 발생");
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
			if(stmt != null) {
				try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		
		return jsonEventList;
		
	}

}
