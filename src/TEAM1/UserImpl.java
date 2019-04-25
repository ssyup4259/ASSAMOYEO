package TEAM1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import TEAM1.Design.MainThread3;

public class UserImpl implements User {

	public static List<UserVO> u_lists = null;
	private String path = System.getProperty("user.dir");
	private File f = new File(path, "\\team\\user.txt");
	public static String loginId;
	public static String loginPwd;

	Scanner sc = new Scanner(System.in);

	public UserImpl() { // ������ �ʱ�ȭ

		try {

			if (!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}

			if (f.exists()) {

				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);

				u_lists = (ArrayList<UserVO>) ois.readObject();

				fis.close();
				ois.close();

			}

		} catch (Exception e) {
			System.out.println("������ �ʱ�ȭ ����");
			e.printStackTrace();
		}

	}

	@Override
	public void userRegister() throws Exception { // ȸ������
		Design designOb = new Design();
		Scanner sc = new Scanner(System.in);

		UserVO vo = new UserVO();
		designOb.registDesign();
		while (true) {

			boolean flag = true;
			System.out.println();
			System.out.print("��� ���̵� [5~15���̳� ��/����ȥ��] �Է��� �ּ��� : ");
			vo.setU_id(sc.next());

			try {// ���̵� ���� üũ
				inputForm(vo.getU_id());
			} catch (Exception e) {
				System.out.println(e.toString());
				flag = false;
			}

			if (userCheckID(vo.getU_id())) { // �ߺ����̵� üũ
				System.out.println(">> �����ϴ� ���̵��Դϴ�.");
				flag = false;
			}
			if (flag) {
				break;
			}
		}

		while (true) {// ��й�ȣ �Է�, ���Է�

			boolean flag = true;

			System.out.print("��� ��й�ȣ �Է� [5~15���̳� ��/����ȥ��] �Է����ּ��� : ");
			vo.setU_pwd(sc.next());

			try {// ��й�ȣ ���� üũ
				inputForm(vo.getU_pwd());
			} catch (Exception e) {
				System.out.println(e.toString());
				flag = false;
			}

			if (flag) {
				break;
			}

		}

		while (true) {// ��й�ȣ �Է�, ���Է�
			boolean flag = false;

			System.out.print("��� ��й�ȣ �� �Է� ���ּ��� : ");
			String pw;
			pw = sc.next();

			if (vo.getU_pwd().equals(pw)) {
				flag = true;
			} else {
				System.out.println(">> ��й�ȣ ����ġ �ٽ� �Է����ּ���");
			}

			if (flag) {
				break;
			}

		}

		System.out.print("��� �̸��� �Է����ּ��� : ");
		vo.setU_name(sc.next());

		while (true) {// ���� ��ȿ��üũ

			System.out.print("��� ���� �Է� [M/F] : ");
			vo.setU_sex(sc.next());

			if (vo.getU_sex().equals("M") || vo.getU_sex().equals("F"))
				break;

		}

		while (vo.getU_age() < 10 || vo.getU_age() > 200) { // ���� ��ȿ�� üũ

			System.out.print("��� ���� �Է� [10 ~ 200] �� �Է����ּ��� : ");
			vo.setU_age(sc.nextInt());

		}

		// ���� ��ȿ�� üũ
		while (true) {
			
			boolean flag = true;

			System.out.print("��� ���� [����Ư���� ���ı�] �� �Է����ּ���: ");
			vo.setU_area(sc.next());

			try {

				if (vo.getU_area().length() < 4 || vo.getU_area().length() > 30)
					throw new Exception();

			} catch (Exception e) {
				System.out.println(">> ���� ���� ���� : " + vo.getU_area());
				flag = false;
			}

			try {

				for (int i = 0; i < vo.getU_area().length(); i++) {

					char ch = vo.getU_area().charAt(i);

					if (ch == ' ')
						continue;
					else if (ch >= 48 && ch <= 57) // ���� �̸�
						throw new Exception();
					else if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) // ����
																				// �ҹ���
																				// �Ǵ�
																				// �빮��
																				// �̸�
						throw new Exception();

				}

			} catch (Exception e) {
				flag = false;
				System.out.println(">> ����/������ �Է� �Ұ�");
			}

			if (flag)
				break;

		}

		while (true) {// ���ɻ� ��ȿ��üũ

			String hobby;
			System.out.print("��� ���ɻ� [����, �, ����, ����, �ݷ�����, ����] �� �Է����ּ��� : ");
			sc = new Scanner(System.in);
			hobby = sc.next();		
			
			if (check_hobby(hobby)) {
				vo.setU_hobby(hobby);
				break;
			}

		}

		while (true) {

			boolean flag = true;

			System.out.print("��� �ڵ��� ��ȣ [010-XXXX-XXXX] �� �Է����ּ���: ");
			vo.setU_phone(sc.next());
			String str = vo.getU_phone();

			try {

				if (vo.getU_phone().substring(0, 3).equals("010")) {

					for (int i = 4; i < 8; i++) { // �� 4�ڸ� ��ȿ�� üũ

						char ch = str.charAt(i);

						if (ch >= '0' && ch <= '9') {
							continue;
						} else {
							throw new Exception("\n>> ���ڸ� �Է��� �ּ���");
						}
					}

					for (int i = 9; i < 13; i++) { // �� 4�ڸ� ��ȿ�� üũ

						char ch = str.charAt(i);

						if (ch >= '0' && ch <= '9') {
							continue;
						} else {
							throw new Exception("\n>> ���ڸ� �Է��� �ּ���");
						}
					}

					flag = false;

				} else {
					throw new Exception("\n>> �ڵ��� ��ȣ�� 010���� ����");
				}

			} catch (Exception e) {
				System.out.println(">> �ڵ��� ��ȣ ����");
			}

			if (!flag)
				break;

		}

		if (u_lists == null)
			u_lists = new ArrayList<UserVO>();

		u_lists.add(vo);

		try {

			if (u_lists != null) {

				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(u_lists);

				fos.close();
				oos.close();

				System.out.println(">> ȸ������ ����");
				System.out.println();
				designOb.MainTop();
			}

		} catch (Exception e) {
			System.out.println(">> ȸ������ ����");
		}

	}

	@Override
	public void userEdit() {
		Design designOb = new Design();
		UserVO vo = new UserVO();
		Iterator<UserVO> it = u_lists.iterator();

		String currentPw, nPw1, nPw2, cArea;
		int ch;

		/*
		 * currentPw: �α��ε� ȸ���� ��й�ȣ �Է¹ޱ� cHobby: ����� ���ɻ� cArea: ����� ���� nPw1:
		 * ��й�ȣ ���� nPw2: ��й�ȣ ����Ȯ��
		 */

		boolean flag1 = true;
		boolean flag2 = true;
		
		System.out.println();
		designOb.updateDesign();
		System.out.println();
		do {
			flag1 = true;

			System.out.println(">> ���������� ���� ��й�ȣ �Է�");

			System.out.print("��� ��й�ȣ�� �Է��� �ּ��� : ");
			currentPw = sc.next();

			if (currentPw.equals(loginPwd)) {
				break;
			} else {
				flag1 = false;
				System.out.println(">> ��й�ȣ ����ġ!!\n");

			}

		} while (!flag1);
		
		try {
			do {

				System.out.print("��� �����ϰ��� �ϴ� ��ȣ�� �Է����ּ��� (1. ���ɻ�, 2. ����, 3. ��й�ȣ, 4. �ڷΰ���) : ");
				ch = sc.nextInt();

			} while (ch < 1 || ch > 4);

			if (ch == 1) {
				
				while (true) {
					
					String hobby;
					System.out.print("��� ���ο� ���ɻ縦 �Է����ּ��� [����, �, ����, ����, �ݷ�����, ����] : ");		
					hobby = sc.next();
					
					while(it.hasNext()){
						
						vo = it.next();
						
						if(loginId.equals(vo.getU_id()))
							break;					
					}
						
					if (check_hobby(hobby)) {
						vo.setU_hobby(hobby);
						break;
					}
				}

			} else if (ch == 2) {

				System.out.print("��� ���ο� ������ �Է����ּ��� : ");
				cArea = sc.next();

				while (it.hasNext()) {

					vo = it.next();

					if (loginId.equals(vo.getU_id())) {

						vo.setU_area(cArea);

					}

				}

			} else if (ch == 3) {

				do {
					flag2 = true;
					System.out.print("��� ���ο� ��й�ȣ�� �Է����ּ��� : ");
					nPw1 = sc.next();
					System.out.print("��� ��й�ȣ Ȯ�� �Է����ּ��� : ");
					nPw2 = sc.next();
					if (!nPw1.equals(nPw2)) {

						flag2 = false;
						System.out.println(">> ��й�ȣ ����ġ\n");

					}

					while (it.hasNext()) {

						vo = it.next();

						if (loginId.equals(vo.getU_id())) {

							vo.setU_pwd(nPw1);

						}

					}

				} while (flag2 == false);

			} else {
				try {
					MyPage.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			UserVO newvo = new UserVO();

			it = u_lists.iterator();

			while (it.hasNext()) {

				vo = it.next();

				if (loginId.equals(vo.getU_id())) {
					newvo.setU_age(vo.getU_age());
					newvo.setU_area(vo.getU_area());
					newvo.setU_hobby(vo.getU_hobby());
					newvo.setU_id(vo.getU_id());
					newvo.setU_name(vo.getU_name());
					newvo.setU_phone(vo.getU_phone());
					newvo.setU_pwd(vo.getU_pwd());
					newvo.setU_sex(vo.getU_sex());

					u_lists.remove(vo);

					break;
				}

			}

			u_lists.add(newvo);

			try {

				if (u_lists != null) {

					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);

					oos.writeObject(u_lists);

					fos.close();
					oos.close();

					System.out.println(">> ���� �Ϸ�");

				}

			} catch (Exception e) {
				System.out.println(">> ���� ����");
			}
		} catch (Exception e) {
			try {
				MyPage.main(null);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void userRemove() {
		Design designOb = new Design();
		System.out.println();
		designOb.withDrawDesgin();
		System.out.println();
		System.out.println(">> ȸ��Ż��");
		System.out.println(">> Ż�� �� ȸ�������� ��� �����˴ϴ�");

		Iterator<UserVO> it = u_lists.iterator();

		while (it.hasNext()) {

			UserVO vo = it.next();

			if (loginId.equals(vo.getU_id())) {

				System.out.print("��� ��й�ȣ�� �Է����ּ��� : ");
				String cPw = sc.next();

				if (cPw.equals(vo.getU_pwd())) {

					u_lists.remove(vo);

					try {

						if (u_lists != null) {

							FileOutputStream fos = new FileOutputStream(f);
							ObjectOutputStream oos = new ObjectOutputStream(fos);

							oos.writeObject(u_lists);

							fos.close();
							oos.close();

							System.out.println(">> Ż�� �Ϸ�");
							System.out.println();
							Main.main(null);

						}

					} catch (Exception e) {
						System.out.println(">> Ż�� ����");
						System.out.println();
					}

					break;

				} else {

					System.out.println(">> ��й�ȣ ����ġ!!\n");
					break;

				}
			}
		}
	}

	@Override
	public void userLogin() throws Exception {
		Design designOb = new Design();
		Iterator<UserVO> it = u_lists.iterator();

		boolean flag1 = false;
		boolean flag2 = false;
		String cPw1 = "";
		designOb.mainDesign();
		System.out.println();
		System.out.print("��� ���̵� �Է��� �ּ��� : ");
		loginId = sc.next();

		while (it.hasNext()) {

			UserVO vo = it.next();

			if (loginId.equals(vo.getU_id())) {

				flag1 = true;
				cPw1 = vo.getU_pwd();
				break;

			}

		}

		if (flag1 == false) {
			System.out.println();
			System.out.println(">> �������� �ʴ� ���̵��Դϴ�.\n");
		} else if (flag1 == true) {
			System.out.print("��� ��й�ȣ�� �Է��� �ּ��� : ");
			String cPw2 = sc.next();

			if (cPw2.equals(cPw1)) {

				flag2 = true;
				loginPwd = cPw1;

			}

			if (flag2 == false)
				System.out.println(">> �߸��� ��й�ȣ�Դϴ�\n");
			else if (flag2 == true) {
				System.out.println(">> �α��� ����!");
				System.out.println();
				designOb.loginDesign(loginId);
				LoginMain.main(null);
			}
		}

	}

	@Override
	public void userLogout() throws Exception {
		Design designOb = new Design();
		System.out.println();
		System.out.println(">> �α׾ƿ� �Ϸ�!");
		designOb.logOutDesign();
		loginId = null;
		System.out.println();
		Main.main(null);

	}

	@Override
	public void inputForm(String str) throws Exception {

		// ����1, ����2

		if (str.length() < 5 || str.length() > 15) {
			throw new Exception(">> ���� ���� : " + str);
		}

		int ck1 = 0; // ������ ����
		int ck2 = 0; // ���� ����

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			if (ch >= 48 && ch <= 57) // ���� �̸�
				ck2++;
			else if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) // ����
																		// �ҹ���
																		// �Ǵ�
																		// �빮��
																		// �̸�
				ck1++;
			else
				// ���ڵ� �����ڵ� �ƴϸ�
				throw new Exception("\n����/Ư������ �Ұ���");

		}

		if (ck1 == str.length() || ck2 == str.length())
			throw new Exception("\n�����ڿ� ���ڰ� ���� �ϳ� �̻� ���ԵǾ�� �մϴ�");

	}

	@Override
	public boolean userCheckID(String id) {

		if (u_lists == null) {
			u_lists = new ArrayList<UserVO>();
		}

		Iterator<UserVO> it = u_lists.iterator();

		while (it.hasNext()) {
			UserVO vo = it.next();

			if (vo.getU_id().equals(id)) {
				return true;
			}
		}

		return false;

	}

	public void print() {
		Design designOb = new Design();
		MainThread3 mt = designOb.new MainThread3();
		mt.start();
		try {
			mt.join();
		} catch (Exception e) {

		}
		designOb.viewGroupDesign();
		System.out.println();
		try {

			Iterator<UserVO> it = u_lists.iterator();

			while (it.hasNext()) {

				UserVO vo = it.next();
				System.out.println(vo.toString());

			}

		} catch (Exception e) {
			System.out.println("����� ������ �����ϴ�.");
		}
	}
	
	public boolean check_hobby(String str) {
		
		String[] hobby = {"����", "�", "����", "����", "�ݷ�����", "����"};
		
		for (int i = 0; i < hobby.length; i++) {
			
			if (str.equals(hobby[i])){
				
				return true;
				
			} else
				continue;		
		}		
		return false;
	}
	
}