package controller;

import util.ConnectDB;

public class Controllers {
	
	private static ConnectDB connectDB;
	private static EventController eventController;
	
	public Controllers() {
		connectDB = new ConnectDB();
		eventController = new EventController();
	}
	
	public static ConnectDB getConnectDB() {
		return connectDB;
	}
	
	public static EventController getEventController() {
		return eventController;
	}
}
