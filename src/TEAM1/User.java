package TEAM1;

public interface User {
	
	// 회원가입
	public void userRegister() throws Exception;
	// 회원가입 유효성검사
	public void inputForm(String str) throws Exception;
	// 내정보수정
	public void userEdit();
	// 탈퇴
	public void userRemove();	
	// 로그인
	public void userLogin() throws Exception;
	// 로그아웃
	public void userLogout() throws Exception;	
	//ID중복체크
	public boolean userCheckID(String id);
}