package controller;

import dao.EventDao;

public class EventController {

	private EventDao eventDao;
	
	public EventController() {
		eventDao = new EventDao();
	}
	//수정
	public String requestEventList() throws Exception {
		
		return eventDao.eventList();
		
	}

}
