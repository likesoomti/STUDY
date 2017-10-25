package controller;

import java.util.List;

import dao.AdminDao;
import dao.EventDao;
import dao.UserDao;
import domain.HG_Event;
import domain.HG_Facility;
import domain.User;

public class AdminController {
	
	private AdminDao admindao;
	
	public AdminController() {
		admindao = new AdminDao();
	} 
	//수정
	public boolean requestAdminuser(String email,String password) throws Exception {
		
		return admindao.Checked_Admin(email, password);
		
	}
	public void event_create(String title, String content, String LOC, String start_date, String end_date, String picture)
	{
		
		admindao.event_create(title,content,picture,LOC,start_date,end_date);
	}
	
	public List<HG_Event> admin_event()
	{
		
		return admindao.admin_eventList();
	}
	
	public boolean facility_create(String title,int category,String address,String phone,String profile,float lon,float lat,int fac_num,String content){
		
		return admindao.facility_create(title,category,address,phone,profile,lon,lat,fac_num,content);
	}
	
	public List<HG_Facility> admin_facility()
	{
		
		return admindao.admin_facilityList();
	}
	
	
	

}
