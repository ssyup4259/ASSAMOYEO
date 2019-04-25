package TEAM1;

public interface Group {

	// 모임개설
	public void groupBuild() throws Exception;
	// 모임수정
	public void groupEdit();
	// 모임삭제
	public void groupRemove();
	// 모임찾기(회원용)
	public void groupSearch() throws Exception;
	// 모임가입
	public void groupRegister(int num) throws Exception;
	// 전체모임출력(비회원용)
	public void groupPrint() throws Exception;
	//내가 개설한 모임보기
	public void groupMyBuild();
	//참여한 모임보기
	public void groupEnter() throws Exception;
}