package TEAM1;

import java.io.Serializable;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

public class GroupVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String g_name; //모임 이름
	private String g_area; //모임 지역
	private String g_hobby; //모임 관심사
	private String g_pwd; //모임개설시 비밀번호
	
	private int g_age; //모임 나이제한
	private int g_count; //모임 인원제한
	private int g_inwon = 1; //모임 현재인원
	
	private String g_cap; // 모임장
	
	private List<String> g_party = new ArrayList<String>();
	
	public String getG_name() {
		return g_name;
	}
	public List<String> getG_party() {
		return g_party;
	}
	public void setG_party(List<String> g_party) {
		this.g_party = g_party;
	}
	public void setG_name(String g_name) {
		this.g_name = g_name;
	}
	public String getG_area() {
		return g_area;
	}
	public void setG_area(String g_area) {
		this.g_area = g_area;
	}
	public String getG_hobby() {
		return g_hobby;
	}
	public void setG_hobby(String g_hobby) {
		this.g_hobby = g_hobby;
	}
	public String getG_pwd() {
		return g_pwd;
	}
	public void setG_pwd(String g_pwd) {
		this.g_pwd = g_pwd;
	}
	public int getG_age() {
		return g_age;
	}
	public void setG_age(int g_age) {
		this.g_age = g_age;
	}
	public int getG_count() {
		return g_count;
	}
	public void setG_count(int g_count) {
		this.g_count = g_count;
	}
	public String getG_cap() {
		return g_cap;
	}
	public void setG_cap(String g_cap) {
		this.g_cap = g_cap;
	}
	
	public int getG_inwon() {
		return g_inwon;
	}
	public void setG_inwon(int g_inwon) {
		this.g_inwon = g_inwon;
	}
	
	@Override
	public String toString() {
		return g_name + ":" + g_area + ":" + g_hobby + ":" + g_pwd + ":" + g_age + ":" + g_count + g_cap;
	}
}
