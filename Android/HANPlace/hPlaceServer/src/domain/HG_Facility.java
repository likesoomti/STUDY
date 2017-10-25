package domain;

public class HG_Facility {
	
	private int FAC_id;
	private String FAC_name;
	private int Category_no;
	private String FAC_Address;
	private String FAC_phone;
	private String FAC_Proifle;
	private double FAC_LOC_Longi;
	private double FAC_LOC_Lati;
	private int FAC_Place_no;
	private String FAC_Place_detail;
	
	public int getFAC_id() {
		return FAC_id;
	}
	public void setFAC_id(int fAC_id) {
		FAC_id = fAC_id;
	}
	public String getFAC_name() {
		return FAC_name;
	}
	public void setFAC_name(String fAC_name) {
		FAC_name = fAC_name;
	}
	public int getCategory_no() {
		return Category_no;
	}
	public void setCategory_no(int category_no) {
		Category_no = category_no;
	}
	public String getFAC_Address() {
		return FAC_Address;
	}
	public void setFAC_Address(String fAC_Address) {
		FAC_Address = fAC_Address;
	}
	public String getFAC_phone() {
		return FAC_phone;
	}
	public void setFAC_phone(String fAC_phone) {
		FAC_phone = fAC_phone;
	}
	public String getFAC_Proifle() {
		return FAC_Proifle;
	}
	public void setFAC_Proifle(String fAC_Proifle) {
		FAC_Proifle = fAC_Proifle;
	}
	public double getFAC_LOC_Longi() {
		return FAC_LOC_Longi;
	}
	public void setFAC_LOC_Longi(double fAC_LOC_Longi) {
		FAC_LOC_Longi = fAC_LOC_Longi;
	}
	public double getFAC_LOC_Lati() {
		return FAC_LOC_Lati;
	}
	public void setFAC_LOC_Lati(double fAC_LOC_Lati) {
		FAC_LOC_Lati = fAC_LOC_Lati;
	}
	public int getFAC_Place_no(int fac_place_no) {
		return fac_place_no;
	}
	public void setFAC_Place_no(int fac_place_no) {
		FAC_Place_no = fac_place_no;
	}

	public int getFAC_Place_no() {
		return FAC_Place_no;
	}
	public String getFAC_Place_detail() {
		return FAC_Place_detail;
	}
	public void setFAC_Place_detail(String fAC_Place_detail) {
		FAC_Place_detail = fAC_Place_detail;
	}
	@Override
	public String toString() {
		return "HG_Facility [FAC_id=" + FAC_id + ", FAC_name=" + FAC_name + ", Category_no=" + Category_no
				+ ", FAC_Address=" + FAC_Address + ", FAC_phone=" + FAC_phone + ", FAC_Proifle=" + FAC_Proifle
				+ ", FAC_LOC_Longi=" + FAC_LOC_Longi + ", FAC_LOC_Lati=" + FAC_LOC_Lati + ", FAC_Place_no="
				+ FAC_Place_no + ", FAC_Place_detail=" + FAC_Place_detail + "]";
	}
	
	

	
	
	
}
