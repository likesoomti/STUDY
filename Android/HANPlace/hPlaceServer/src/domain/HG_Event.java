package domain;

public class HG_Event {
	
	private int event_id;
	private String event_title;
	private String event_content;
	private String event_picture;
	private String event_loc;
	private String event_start_date;
	private String event_end_date;
	
	public HG_Event() {
		
	}
	
	public HG_Event(int event_id, String event_title, String event_content, String event_picture, String event_loc, String event_start_date, String event_end_date) {
		
		this.event_id = event_id;
		this.event_title = event_title;
		this.event_content = event_content;
		this.event_picture = event_picture;
		this.event_loc = event_loc;
		this.event_start_date = event_start_date;
		this.event_end_date = event_end_date;
		
	}

	public HG_Event(String event_title, String event_content, String event_picture, String event_loc, String event_start_date, String event_end_date) {

		this.event_title = event_title;
		this.event_content = event_content;
		this.event_picture = event_picture;
		this.event_loc = event_loc;
		this.event_start_date = event_start_date;
		this.event_end_date = event_end_date;
		
	}
	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getEvent_title() {
		return event_title;
	}

	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}

	public String getEvent_content() {
		return event_content;
	}

	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}

	public String getEvent_picture() {
		return event_picture;
	}

	public void setEvent_picture(String event_picture) {
		this.event_picture = event_picture;
	}

	public String getEvent_loc() {
		return event_loc;
	}

	public void setEvent_loc(String event_loc) {
		this.event_loc = event_loc;
	}

	public String getEvent_start_date() {
		return event_start_date;
	}

	public void setEvent_start_date(String event_start_date) {
		this.event_start_date = event_start_date;
	}

	public String getEvent_end_date() {
		return event_end_date;
	}

	public void setEvent_end_date(String event_end_date) {
		this.event_end_date = event_end_date;
	}

}
