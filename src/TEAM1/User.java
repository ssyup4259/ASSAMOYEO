package TEAM1;

public interface User {
	
	// ȸ������
	public void userRegister() throws Exception;
	// ȸ������ ��ȿ���˻�
	public void inputForm(String str) throws Exception;
	// ����������
	public void userEdit();
	// Ż��
	public void userRemove();	
	// �α���
	public void userLogin() throws Exception;
	// �α׾ƿ�
	public void userLogout() throws Exception;	
	//ID�ߺ�üũ
	public boolean userCheckID(String id);
}