package TEAM1;

public interface Group {

	// ���Ӱ���
	public void groupBuild() throws Exception;
	// ���Ӽ���
	public void groupEdit();
	// ���ӻ���
	public void groupRemove();
	// ����ã��(ȸ����)
	public void groupSearch() throws Exception;
	// ���Ӱ���
	public void groupRegister(int num) throws Exception;
	// ��ü�������(��ȸ����)
	public void groupPrint() throws Exception;
	//���� ������ ���Ӻ���
	public void groupMyBuild();
	//������ ���Ӻ���
	public void groupEnter() throws Exception;
}