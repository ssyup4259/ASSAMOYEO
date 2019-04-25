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

public class GroupImpl implements Group {

	public List<GroupVO> g_lists = null;
	public List<String> g_party = null;
	private String path = System.getProperty("user.dir");
	private File f = new File(path, "\\team\\group.txt");
	UserImpl userOB = new UserImpl();

	Scanner sc = new Scanner(System.in);

	public GroupImpl() { // ���� �о����

		try {

			if (!f.getParentFile().exists()) {

				f.getParentFile().mkdirs();

			}

			if (f.exists()) {

				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);

				g_lists = (ArrayList<GroupVO>)ois.readObject();

				ois.close();
				fis.close();

			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@Override
	public void groupBuild() throws Exception {
		Design designOb = new Design();
		int ch;
		boolean flag1;

		GroupVO groupVO = new GroupVO();
		UserImpl userOB = new UserImpl();
		
		designOb.makeDesign();
		System.out.println();
		if (g_lists != null) {

			Iterator<GroupVO> it = g_lists.iterator();

			while (it.hasNext()) {

				GroupVO vo1 = it.next();

				if (userOB.loginId.equals(vo1.getG_cap())) {

					System.out.println(">> ȸ���Բ��� �̹� ������ ������ �����մϴ�!\n");
					LoginMain.main(null);
					break;

				}

			}
		}

		System.out.print("��� �����̸��� �Է����ּ��� [5���� �����Դϴ�.] : ");
		groupVO.setG_name(sc.next());

		while (true) {
			flag1 = true;

			System.out.print("��� ���� [����Ư���� ���ı�]�� �Է����ּ��� : ");
			groupVO.setG_area(sc.next());

			try {

				if (groupVO.getG_area().length() < 4
						|| groupVO.getG_area().length() > 30)
					throw new Exception();

			} catch (Exception e) {
				System.out.println(">> ���� ���� ���� : " + groupVO.getG_area());
				flag1 = false;
			}

			try {

				for (int i = 0; i < groupVO.getG_area().length(); i++) {

					char cha = groupVO.getG_area().charAt(i);

					if (cha == ' ')
						continue;
					else if (cha >= 48 && cha <= 57) // ���� �̸�
						throw new Exception();
					else if ((cha >= 65 && cha <= 90)
							|| (cha >= 97 && cha <= 122)) // ���� �ҹ��� �Ǵ� �빮�� �̸�
						throw new Exception();

				}

			} catch (Exception e) {
				flag1 = false;
				System.out.println(">> ����/������ �Է� �Ұ�");
			}

			if (flag1)
				break;

		}
	

		while (true) {// ���ɻ� ��ȿ��üũ

			String hobby;

			System.out.print("��� ���ɻ� [����, �, ����, ����, �ݷ�����, ����] �� �Է����ּ��� :  ");
			sc = new Scanner(System.in);
			hobby = sc.next();		
			
			if (userOB.check_hobby(hobby)) {
				groupVO.setG_hobby(hobby);
				break;
			}

		}

		System.out.print("��� ��������[�� : 19�� �̻�]�� �Է����ּ��� : ");
		groupVO.setG_age(sc.nextInt());

		System.out.print("��� �ִ��ο��� �Է����ּ��� : ");
		groupVO.setG_count(sc.nextInt());
		List<String> g_party = new ArrayList<String>();

		System.out.print("��� ������ ��й�ȣ�� �Է����ּ��� : ");
		groupVO.setG_pwd(sc.next());

		groupVO.setG_cap(userOB.loginId);
		System.out.println();
		designOb.optionDesign();
		System.out.println();
		System.out.print("��� �޴� ��ȣ�� �Է����ּ��� : ");
		ch = sc.nextInt();
		System.out.println();
		if (ch == 1) {

			if (g_lists == null)
				g_lists = new ArrayList<GroupVO>();

			g_party.add(groupVO.getG_cap().toString());
			groupVO.setG_party(g_party);

			g_lists.add(groupVO);

			try {

				if (g_lists != null) {

					FileOutputStream fos = new FileOutputStream(f);
					ObjectOutputStream oos = new ObjectOutputStream(fos);

					oos.writeObject(g_lists);

					fos.close();
					oos.close();

					System.out.println(">> ���� �Ϸ�");
					System.out.println();
					// System.out.println(vo.getG_party());
					LoginMain.main(null);

				}

			} catch (Exception e) {
				System.out.println(">> ���� ����");
				LoginMain.main(null);
			}

		} else if (ch == 2) {

			LoginMain.main(null);

		}

	}

	@Override
	public void groupEdit() {// ���Ӽ���

		String g_pwd = null;
		GroupVO vo = new GroupVO();
		Iterator<GroupVO> it = g_lists.iterator();

		while (it.hasNext()) {

			vo = it.next();

			if (userOB.loginId.equals(vo.getG_cap())) {
				g_pwd = vo.getG_pwd();
				break;
			}

		}

		String currentPw, cCap, cName;
		int ch;

		/*
		 * currentPw: �α��ε� ȸ���� ��й�ȣ �Է¹ޱ� cName: ����� ���Ӹ� cCap: ����� ������
		 */

		boolean flag1 = true;

		do {
			flag1 = true;

			System.out.println("��� ���Ӽ����� ���� ��й�ȣ �Է�");

			System.out.print(">> ��й�ȣ: ");
			currentPw = sc.next();

			if (currentPw.equals(g_pwd)) {
				break;
			} else {
				flag1 = false;
				System.out.println(">> ��й�ȣ ����ġ!!\n");

			}

		} while (!flag1);

		do {

			System.out.print("��� �����ϰ��� �ϴ� ������ȣ�� �Է����ּ���(1. ���Ӹ�, 2.������ : ");
			ch = sc.nextInt();

		} while (ch < 1 || ch > 2);

		it = g_lists.iterator();

		if (ch == 1) {

			System.out.print("��� ���ο� ���Ӹ��� �Է����ּ��� : ");
			cName = sc.next();

			while (it.hasNext()) {

				vo = it.next();
				if (userOB.loginId.equals(vo.getG_cap())) {

					vo.setG_name(cName);
					break;

				}

			}

		} else if (ch == 2) {

			System.out.print("��� ���ο� �������� �Է����ּ��� : ");
			cCap = sc.next();

			while (it.hasNext()) {

				vo = it.next();

				if (userOB.loginId.equals(vo.getG_cap())) {

					vo.setG_cap(cCap);
					break;

				}

			}

		}

		GroupVO newvo = new GroupVO();

		it = g_lists.iterator();

		while (it.hasNext()) {

			vo = it.next();

			if (userOB.loginId.equals(vo.getG_cap())) {

				newvo.setG_name(vo.getG_name());
				newvo.setG_cap(vo.getG_cap());
				newvo.setG_area(vo.getG_area());
				newvo.setG_hobby(vo.getG_hobby());
				newvo.setG_pwd(vo.getG_pwd());
				newvo.setG_age(vo.getG_age());
				newvo.setG_count(vo.getG_count());
				newvo.setG_inwon(vo.getG_inwon());
				newvo.setG_party(vo.getG_party());

				g_lists.remove(vo);
				g_lists.add(newvo);
				break;
			}

		}

		try {

			if (g_lists != null) {

				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(g_lists);

				fos.close();
				oos.close();

				System.out.println(">> ���� �Ϸ�");

			}

		} catch (Exception e) {
			System.out.println(">> ���� ����");
		}
	}

	@Override
	public void groupRemove() {// ���ӻ���

		System.out.println(">> ���ӻ���");
		System.out.println(">> ������ �����常 ���� �����մϴ�.");

		Iterator<GroupVO> it = g_lists.iterator();

		while (it.hasNext()) {

			GroupVO vo = it.next();

			if (userOB.loginId.equals(vo.getG_cap())) {
				
				System.out.print("��� ���� ��й�ȣ�� �Է����ּ��� : ");
				String cPw = sc.next();

				if (cPw.equals(vo.getG_pwd())) {

					g_lists.remove(vo);

					try {

						if (g_lists != null) {

							FileOutputStream fos = new FileOutputStream(f);
							ObjectOutputStream oos = new ObjectOutputStream(fos);

							oos.writeObject(g_lists);

							fos.close();
							oos.close();

							System.out.println(">> ���ӻ��� �Ϸ�");
							break;

						}

					} catch (Exception e) {
						System.out.println(">> ���ӻ��� ����");
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
	public void groupSearch() throws Exception {// ȸ���������
		Design designOb = new Design();
		MainThread3 mt = designOb.new MainThread3();
		mt.start();
		try {
			mt.join();
		} catch (Exception e) {

		}
		designOb.viewGroupDesign();
		int i = 0;
		int ch = 0;

		if (!f.exists()) {

			System.out.println();
			System.out.println(">> ������ �������� �ʽ��ϴ�!!");

		} else {
			designOb.catchDesign();
			System.out.println();
			System.out.println("������������������������������������������");
			System.out.printf("���     %s    %3s   %6s %7s %8s %5s %5s ���\n", "NO.", "���Ӹ�",
					"������", "����", "�ִ��ο�", "�����ο�", "��������");
			System.out.println("������������������������������������������");
			Iterator<GroupVO> it = g_lists.iterator();

			while (it.hasNext()) {

				GroupVO groupVO = it.next();
				i++;
				String name = groupVO.getG_name();
				while(name.length()< 6){
					name += "��";
				}
				System.out.printf("���     %d %10s %10s %7s %5d %5d %10d    ���\n", i,
						name, groupVO.getG_cap(),
						groupVO.getG_area(), groupVO.getG_count(),
						groupVO.getG_inwon(), groupVO.getG_age());
				
				if(groupVO.getG_party().size()>4){
					System.out.println("���          �����ο��� �����ϴ�. �������� ������.                        ���"); // �׽�Ʈ��
				}else{
					
					String str = groupVO.getG_party().toString();
					while (str.length() < 50 ) {
						str += " ";
					}
					System.out.printf("���                   %50s     ���\n",str);
				}
			}
			
			System.out.println("������������������������������������������");
			System.out.println();
			try {
				System.out.print(">> ������ ������ ��ȣ�� �Է����ּ���.(����ȭ������ ���� : 0) : ");
				ch = sc.nextInt();
				System.out.println();

			} catch (Exception e) {
				System.out.println(">> �������� �ʴ� �����Դϴ�");
			}

			if (ch == 0)
				LoginMain.main(null);

			else {
				try {
					groupRegister(ch);
				} catch (Exception e) {
					System.out.println(">> �������� �ʴ� �����Դϴ�.");
				}
				

			}

			System.out.println();
		}

	}

	@Override
	public void groupRegister(int num) throws Exception { // ��������

		GroupVO groupVO = g_lists.get(num - 1);

		// �ο�����

		if (groupVO.getG_inwon() >= groupVO.getG_count()) {

			System.out.println(">> ������ �� á���ϴ�!!!");
			groupSearch();

		} else {

			g_party = groupVO.getG_party();

			UserImpl userOB = new UserImpl();

			Iterator<UserVO> it = userOB.u_lists.iterator();

			if (g_party.contains((userOB.loginId))) {

				System.out.println(">> �̹� ������ �����Դϴ�.");
				LoginMain.main(null);

			} else {

				while (it.hasNext()) {

					UserVO userVO = it.next();

					// ��������

					if (userVO.getU_id().equals(userOB.loginId)) {

						if (groupVO.getG_age() > userVO.getU_age()) {

							System.out.println(">> �ش� ������ " + groupVO.getG_age()
									+ "�� �̻� �̿밡���մϴ�");
							groupSearch();

						} else {

							groupVO.setG_inwon(groupVO.getG_inwon() + 1);
							g_party.add(userOB.loginId.toString());

							GroupVO new_groupVO = new GroupVO();

							new_groupVO.setG_name(groupVO.getG_name());
							new_groupVO.setG_area(groupVO.getG_area());
							new_groupVO.setG_hobby(groupVO.getG_hobby());
							new_groupVO.setG_pwd(groupVO.getG_pwd());
							new_groupVO.setG_age(groupVO.getG_age());
							new_groupVO.setG_count(groupVO.getG_count());
							new_groupVO.setG_inwon(groupVO.getG_inwon());
							new_groupVO.setG_cap(groupVO.getG_cap());
							new_groupVO.setG_party(groupVO.getG_party());

							g_lists.remove(groupVO);
							g_lists.add(new_groupVO);

							break;

						}

						break;

					}

				}

				try {

					if (g_lists != null) {

						FileOutputStream fos = new FileOutputStream(f);
						ObjectOutputStream oos = new ObjectOutputStream(fos);

						oos.writeObject(g_lists);

						fos.close();
						oos.close();
						System.out.println(">> "+ num + "�� ���� �����Ϸ�!!!");
						LoginMain.main(null);

					}

				} catch (Exception e) {
					System.out.println(">> ���� ����");
				}

			}
		}
	}

	@Override
	public void groupPrint() throws Exception {// ��ȸ���������
		Design designOb = new Design();
		MainThread3 mt = designOb.new MainThread3();
		mt.start();
		try {
			mt.join();
		} catch (Exception e) {

		}
		designOb.viewGroupDesign();
		System.out.println();
		int ch;

		if (!f.exists()) {
			System.out.println(">> ������ �������� �ʽ��ϴ�!!");
		} else {

			System.out.println("������������������������������������������");
			System.out.printf("���    %3s    %6s     %7s     %8s        %5s   ���\n", "���Ӹ�", "������", "����",
					"�ִ��ο�", "��������");
			System.out.println("������������������������������������������");
			Iterator<GroupVO> it = g_lists.iterator();

			while (it.hasNext()) {

				GroupVO vo = it.next();
				String str = vo.getG_name();
				while(str.length()<5){
					str+="��";
				}
				System.out.printf("���    %5s %10s   %7s      %5d       %10d      ���\n", str,
						vo.getG_cap(), vo.getG_area(), vo.getG_count(),
						vo.getG_age());

			}
			System.out.println("������������������������������������������");

			System.out.println();

			do {
				
				designOb.MainTop();
				System.out.println();
				System.out.print("��� �޴���ȣ�� �Է����ּ���(1. ȸ������ 2. �α��� 3. ó������) : ");
				ch = sc.nextInt();

			} while (ch < 1 || ch > 3);

			switch (ch) {

			case 1:
				System.out.println();
				userOB.userRegister();
				break;

			case 2:
				System.out.println();
				userOB.userLogin();
				break;

			case 3:
				System.out.println();
				Main.main(null);
				break;
			}
		}
	}

	
	@Override
	public void groupMyBuild() {//���� ������ ����
		Design designOb = new Design();
		MainThread3 mt = designOb.new MainThread3();
		
		boolean flag = true;
		int ch;
		GroupVO groupVO = new GroupVO();
		Iterator<GroupVO> it = g_lists.iterator();
		System.out.println();
		mt.start();
		try {
			mt.join();
		} catch (Exception e) {

		}
		
		designOb.makeViewDesign(userOB.loginId);
		System.out.println();
		while (it.hasNext()) {
			
			groupVO = it.next();
			
			if (userOB.loginId.equals(groupVO.getG_cap())) {
				
				System.out.println("������������������������������������������");
				System.out.printf("���        %3s  %6s %7s %8s %5s %5s      ���\n", "���Ӹ�", "���ɻ�", "����", "�ִ��ο�", "�����ο�", "��������");
				System.out.println("������������������������������������������");
								
				String str = groupVO.getG_name();
				while(str.length()<5){
					str += "��";
				}
				System.out.printf("���         %5s %5s %8s %6d %8d %10d      ���\n",
						str, groupVO.getG_hobby(),
						groupVO.getG_area(), groupVO.getG_count(),
						groupVO.getG_inwon(), groupVO.getG_age());	
				System.out.println("������������������������������������������");
				
				System.out.println();
				flag = false;
				break;
			}			
			
		}
		
		sc = new Scanner(System.in);
		
		if (!flag) {
			
			System.out.print("��� �޴���ȣ�� �Է��� �ּ���(1. �����ο� ��� 2. ���ӻ��� 3. �ڷΰ���) : ");
			ch = sc.nextInt();
			System.out.println();
			
			switch (ch) {
			case 1:
				System.out.println("������������������������������������������");
				System.out.println("���                                    ���� �ο�                             ���");
				System.out.println("������������������������������������������");
				for (int i = 0; i < groupVO.getG_party().size(); i++){
					String str = groupVO.getG_party().get(i);
					while(str.length()<5){
						str+=" ";
					}
					System.out.printf("���                                   %10s                             ���\n",str);
				}
				System.out.println("������������������������������������������");
					try {
						MyPage.main(null);
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				break;
				
			case 2:				
				
				
				groupRemove();
				break;
				
			case 3:
				try {
					MyPage.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}				
				break;

			default:
				try {
					MyPage.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}	
				break;
			}
			
		} else {
			System.out.println(">> ������ ������ �����ϴ�.");
			System.out.print("��� �޴���ȣ�� �Է����ּ���(1. ���Ӱ����ϱ�, 2. ������������) : ");
			ch = sc.nextInt();
			if (ch == 1) {
				try {
					groupBuild();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				try {
					MyPage.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	
	}

	@Override
	public void groupEnter() throws Exception { // ������ ���� ���
		Design designOb = new Design();
		MainThread3 mt = designOb.new MainThread3();
		UserImpl userOB = new UserImpl();		
		String p_num = null;

		int i = 0;
		int ch;
		
		System.out.println();
		mt.start();
		try {
			mt.join();
		} catch (Exception e) {

		}
		designOb.particiDesign(userOB.loginId);
		System.out.println();
		System.out.println("������������������������������������������");
		System.out.printf("���       %s %3s%6s %7s  %5s %5s    %4s      ���\n", "NO.", "���Ӹ�", "����",
				"������", "�ִ��ο�", "�����ο�", "����ȣ");
		System.out.println("������������������������������������������");
				
		
		for(int ii = 0; ii<g_lists.size(); ii++){
			
			GroupVO groupVO = g_lists.get(ii);
			
			if(groupVO.getG_party().contains(userOB.loginId)){
				
				for(int jj=0; jj<userOB.u_lists.size();jj++){
					
					UserVO userVO = userOB.u_lists.get(jj);
					
					if(groupVO.getG_cap().equals(userVO.getU_id())){
						
						p_num = userVO.getU_phone();
						
					}					
					
				}

				i++;
				String str = groupVO.getG_name();
				while(str.length()<5){
					str += "��";
				}
				System.out.printf("���     %d %3s %6s %7s %5d   %5d     %10s    ���\n", i,
						str, groupVO.getG_area(),
						groupVO.getG_cap(), groupVO.getG_count(),
						groupVO.getG_inwon(), p_num);

			}

		}
		System.out.println("������������������������������������������");
		System.out.println();
		System.out.print(">> �޴� ��ȣ�� �Է��� �ּ���(1. ��������) : ");
		ch = sc.nextInt();

		switch (ch) {

		case 1:

			MyPage.main(null);
			break;

		}
	}
}