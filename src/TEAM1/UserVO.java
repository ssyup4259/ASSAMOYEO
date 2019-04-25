package TEAM1;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String u_name;
	private String u_id; //영어 소문자, 숫자
	private String u_sex; //F, M
	private String u_pwd; //특수문자X
	private String u_area; //OOOOO시 OO구
	private String u_hobby; //공부, 운동, 여행, 음식, 반려동물, 게임
	private String u_phone; //전화번호
	private int u_age; //10 ~ 200
	
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		this.u_id = u_id;
	}
	public String getU_sex() {
		return u_sex;
	}
	public void setU_sex(String u_sex) {
		this.u_sex = u_sex;
	}
	public String getU_pwd() {
		return u_pwd;
	}
	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}
	public String getU_area() {
		return u_area;
	}
	public void setU_area(String u_area) {
		this.u_area = u_area;
	}
	public String getU_hobby() {
		return u_hobby;
	}
	public void setU_hobby(String u_hobby) {
		this.u_hobby = u_hobby;
	}
	public int getU_age() {
		return u_age;
	}
	public void setU_age(int u_age) {
		this.u_age = u_age;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
}