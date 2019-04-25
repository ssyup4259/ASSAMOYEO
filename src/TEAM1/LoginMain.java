package TEAM1;

import java.util.Scanner;

public class LoginMain {

	public static void main(String[] args) throws Exception {
		
		UserImpl userOB = new UserImpl();
		GroupImpl groupOB = new GroupImpl();
		Scanner sc = new Scanner(System.in);
		Design designOb = new Design();
		
		designOb.logInAfterDesign();
		System.out.println();
		
		int ch;

		while (true) {

			do {

				System.out.print("■□ 메뉴 번호를 입력해 주세요 : ");
				ch = sc.nextInt();
				System.out.println();

			} while (ch < 1 || ch > 6);

			switch (ch) {

			case 1:
				groupOB.groupBuild();
				break;

			case 2:
				groupOB.groupSearch();
				break;

			case 3:
				MyPage.main(null);
				break;
				
			case 4:
				userOB.userLogout();
				break;
				
			case 5:
				System.out.println("종료");
				return;
				
			default :
				System.out.println("종료");
				return;

			}
		}
	}
}