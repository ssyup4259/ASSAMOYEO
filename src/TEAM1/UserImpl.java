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

	public UserImpl() { // 생성자 초기화

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
			System.out.println("생성자 초기화 오류");
			e.printStackTrace();
		}

	}

	@Override
	public void userRegister() throws Exception { // 회원가입
		Design designOb = new Design();
		Scanner sc = new Scanner(System.in);

		UserVO vo = new UserVO();
		designOb.registDesign();
		while (true) {

			boolean flag = true;
			System.out.println();
			System.out.print("■□ 아이디 [5~15자이내 영/숫자혼합] 입력해 주세요 : ");
			vo.setU_id(sc.next());

			try {// 아이디 조건 체크
				inputForm(vo.getU_id());
			} catch (Exception e) {
				System.out.println(e.toString());
				flag = false;
			}

			if (userCheckID(vo.getU_id())) { // 중복아이디 체크
				System.out.println(">> 존재하는 아이디입니다.");
				flag = false;
			}
			if (flag) {
				break;
			}
		}

		while (true) {// 비밀번호 입력, 재입력

			boolean flag = true;

			System.out.print("■□ 비밀번호 입력 [5~15자이내 영/숫자혼합] 입력해주세요 : ");
			vo.setU_pwd(sc.next());

			try {// 비밀번호 조건 체크
				inputForm(vo.getU_pwd());
			} catch (Exception e) {
				System.out.println(e.toString());
				flag = false;
			}

			if (flag) {
				break;
			}

		}

		while (true) {// 비밀번호 입력, 재입력
			boolean flag = false;

			System.out.print("■□ 비밀번호 재 입력 해주세요 : ");
			String pw;
			pw = sc.next();

			if (vo.getU_pwd().equals(pw)) {
				flag = true;
			} else {
				System.out.println(">> 비밀번호 불일치 다시 입력해주세요");
			}

			if (flag) {
				break;
			}

		}

		System.out.print("■□ 이름을 입력해주세요 : ");
		vo.setU_name(sc.next());

		while (true) {// 성별 유효성체크

			System.out.print("■□ 성별 입력 [M/F] : ");
			vo.setU_sex(sc.next());

			if (vo.getU_sex().equals("M") || vo.getU_sex().equals("F"))
				break;

		}

		while (vo.getU_age() < 10 || vo.getU_age() > 200) { // 나이 유효성 체크

			System.out.print("■□ 나이 입력 [10 ~ 200] 를 입력해주세요 : ");
			vo.setU_age(sc.nextInt());

		}

		// 지역 유효성 체크
		while (true) {
			
			boolean flag = true;

			System.out.print("■□ 지역 [서울특별시 송파구] 을 입력해주세요: ");
			vo.setU_area(sc.next());

			try {

				if (vo.getU_area().length() < 4 || vo.getU_area().length() > 30)
					throw new Exception();

			} catch (Exception e) {
				System.out.println(">> 지역 길이 오류 : " + vo.getU_area());
				flag = false;
			}

			try {

				for (int i = 0; i < vo.getU_area().length(); i++) {

					char ch = vo.getU_area().charAt(i);

					if (ch == ' ')
						continue;
					else if (ch >= 48 && ch <= 57) // 숫자 이면
						throw new Exception();
					else if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) // 영어
																				// 소문자
																				// 또는
																				// 대문자
																				// 이면
						throw new Exception();

				}

			} catch (Exception e) {
				flag = false;
				System.out.println(">> 숫자/영문자 입력 불가");
			}

			if (flag)
				break;

		}

		while (true) {// 관심사 유효성체크

			String hobby;
			System.out.print("■□ 관심사 [공부, 운동, 여행, 음식, 반려동물, 게임] 를 입력해주세요 : ");
			sc = new Scanner(System.in);
			hobby = sc.next();		
			
			if (check_hobby(hobby)) {
				vo.setU_hobby(hobby);
				break;
			}

		}

		while (true) {

			boolean flag = true;

			System.out.print("■□ 핸드폰 번호 [010-XXXX-XXXX] 를 입력해주세요: ");
			vo.setU_phone(sc.next());
			String str = vo.getU_phone();

			try {

				if (vo.getU_phone().substring(0, 3).equals("010")) {

					for (int i = 4; i < 8; i++) { // 앞 4자리 유효성 체크

						char ch = str.charAt(i);

						if (ch >= '0' && ch <= '9') {
							continue;
						} else {
							throw new Exception("\n>> 숫자만 입력해 주세요");
						}
					}

					for (int i = 9; i < 13; i++) { // 뒤 4자리 유효성 체크

						char ch = str.charAt(i);

						if (ch >= '0' && ch <= '9') {
							continue;
						} else {
							throw new Exception("\n>> 숫자만 입력해 주세요");
						}
					}

					flag = false;

				} else {
					throw new Exception("\n>> 핸드폰 번호는 010으로 시작");
				}

			} catch (Exception e) {
				System.out.println(">> 핸드폰 번호 오류");
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

				System.out.println(">> 회원가입 성공");
				System.out.println();
				designOb.MainTop();
			}

		} catch (Exception e) {
			System.out.println(">> 회원가입 실패");
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
		 * currentPw: 로그인된 회원의 비밀번호 입력받기 cHobby: 변경된 관심사 cArea: 변경된 지역 nPw1:
		 * 비밀번호 변경 nPw2: 비밀번호 변경확인
		 */

		boolean flag1 = true;
		boolean flag2 = true;
		
		System.out.println();
		designOb.updateDesign();
		System.out.println();
		do {
			flag1 = true;

			System.out.println(">> 정보수정을 위한 비밀번호 입력");

			System.out.print("■□ 비밀번호를 입력해 주세요 : ");
			currentPw = sc.next();

			if (currentPw.equals(loginPwd)) {
				break;
			} else {
				flag1 = false;
				System.out.println(">> 비밀번호 불일치!!\n");

			}

		} while (!flag1);
		
		try {
			do {

				System.out.print("■□ 변경하고자 하는 번호를 입력해주세요 (1. 관심사, 2. 지역, 3. 비밀번호, 4. 뒤로가기) : ");
				ch = sc.nextInt();

			} while (ch < 1 || ch > 4);

			if (ch == 1) {
				
				while (true) {
					
					String hobby;
					System.out.print("■□ 새로운 관심사를 입력해주세요 [공부, 운동, 여행, 음식, 반려동물, 게임] : ");		
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

				System.out.print("■□ 새로운 지역을 입력해주세요 : ");
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
					System.out.print("■□ 새로운 비밀번호를 입력해주세요 : ");
					nPw1 = sc.next();
					System.out.print("■□ 비밀번호 확인 입력해주세요 : ");
					nPw2 = sc.next();
					if (!nPw1.equals(nPw2)) {

						flag2 = false;
						System.out.println(">> 비밀번호 불일치\n");

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

					System.out.println(">> 수정 완료");

				}

			} catch (Exception e) {
				System.out.println(">> 수정 실패");
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
		System.out.println(">> 회원탈퇴");
		System.out.println(">> 탈퇴 후 회원정보는 모두 삭제됩니다");

		Iterator<UserVO> it = u_lists.iterator();

		while (it.hasNext()) {

			UserVO vo = it.next();

			if (loginId.equals(vo.getU_id())) {

				System.out.print("■□ 비밀번호를 입력해주세요 : ");
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

							System.out.println(">> 탈퇴 완료");
							System.out.println();
							Main.main(null);

						}

					} catch (Exception e) {
						System.out.println(">> 탈퇴 실패");
						System.out.println();
					}

					break;

				} else {

					System.out.println(">> 비밀번호 불일치!!\n");
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
		System.out.print("■□ 아이디를 입력해 주세요 : ");
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
			System.out.println(">> 존재하지 않는 아이디입니다.\n");
		} else if (flag1 == true) {
			System.out.print("■□ 비밀번호를 입력해 주세요 : ");
			String cPw2 = sc.next();

			if (cPw2.equals(cPw1)) {

				flag2 = true;
				loginPwd = cPw1;

			}

			if (flag2 == false)
				System.out.println(">> 잘못된 비밀번호입니다\n");
			else if (flag2 == true) {
				System.out.println(">> 로그인 성공!");
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
		System.out.println(">> 로그아웃 완료!");
		designOb.logOutDesign();
		loginId = null;
		System.out.println();
		Main.main(null);

	}

	@Override
	public void inputForm(String str) throws Exception {

		// 조건1, 조건2

		if (str.length() < 5 || str.length() > 15) {
			throw new Exception(">> 길이 오류 : " + str);
		}

		int ck1 = 0; // 영문자 개수
		int ck2 = 0; // 숫자 개수

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			if (ch >= 48 && ch <= 57) // 숫자 이면
				ck2++;
			else if ((ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122)) // 영어
																		// 소문자
																		// 또는
																		// 대문자
																		// 이면
				ck1++;
			else
				// 숫자도 영문자도 아니면
				throw new Exception("\n공백/특수문자 불가능");

		}

		if (ck1 == str.length() || ck2 == str.length())
			throw new Exception("\n영문자와 숫자가 각각 하나 이상 포함되어야 합니다");

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
			System.out.println("출력할 내용이 없습니다.");
		}
	}
	
	public boolean check_hobby(String str) {
		
		String[] hobby = {"공부", "운동", "여행", "음식", "반려동물", "게임"};
		
		for (int i = 0; i < hobby.length; i++) {
			
			if (str.equals(hobby[i])){
				
				return true;
				
			} else
				continue;		
		}		
		return false;
	}
	
}