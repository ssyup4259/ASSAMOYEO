package TEAM1;

import java.util.Scanner;

public class Main extends Design {
	public static void main(String[] args) {
		UserImpl userOb = new UserImpl();
		Design designOb = new Design();
		Scanner sc = new Scanner(System.in);
		MainThread1 mt1 = designOb.new MainThread1();
		MainThread2 mt2 = designOb.new MainThread2();
		MainThread2 mt3 = designOb.new MainThread2();
		MainThread2 mt4 = designOb.new MainThread2();
		GroupImpl groupOb = new GroupImpl();
		designOb.MainTop();
		mt1.start();
		try {
			mt1.join();
		} catch (Exception e) {

		}
		designOb.MainBottom1();
		mt2.start();

		try {
			mt2.join();
		} catch (Exception e) {

		}
		System.out.println();
		designOb.MainBottom2();
		mt3.start();

		try {
			mt3.join();
		} catch (Exception e) {

		}
		System.out.println();
		designOb.MaindBottom3();
		mt4.start();
		try {
			mt4.join();
		} catch (Exception e) {

		}

		System.out.println();
		int ch;
		while (true) {

			do {
				System.out.println();
				System.out.print("■□ 메뉴 번호를 입력해 주세요(1.회원가입, 2.로그인, 3.전체모임, 4.종료) : ");
				ch = sc.nextInt();

			} while (ch < 1);

			switch (ch) {

				case 1 :
					System.out.println();
					try {

						userOb.userRegister();
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println();
					break;
				case 2 :
					System.out.println();
					try {
						userOb.userLogin();
					} catch (Exception e) {

						e.printStackTrace();
					}
					System.out.println();
					break;
				case 3 :
					System.out.println();
					try {
						groupOb.groupPrint();
					} catch (Exception e) {

						e.printStackTrace();
					}
					System.out.println();
					break;
				case 4 :
					System.out.println();
					designOb.logOutDesign();
					System.exit(0);
					System.out.println();
					break;

				default :
					System.out.println();
					designOb.logOutDesign();
					System.out.println("종료");
					System.exit(0);

			}
		}

	}

}
