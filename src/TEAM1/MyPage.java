package TEAM1;

import java.util.Scanner;

public class MyPage {

	public static void main(String[] args) throws Exception {
		
		UserImpl userOB = new UserImpl();
		GroupImpl groupOB = new GroupImpl();
		Scanner sc = new Scanner(System.in);
		Design designOb = new Design();
		int ch;
		while(true) {
		
			do{
				
				System.out.println();
				designOb.mypageDesign();
				System.out.println();
				System.out.print("��� �޴� ��ȣ�� �Է��� �ּ��� : ");
				ch = sc.nextInt();
				System.out.println();
				
			}while(ch<1);
		
			switch (ch) {
			
			case 1:				
				userOB.userEdit();
				break;
			case 2:
				groupOB.groupEnter();
				break;
			case 3:
				groupOB.groupMyBuild();
				break;
			case 4:
				userOB.userLogout();
				break;
			case 5:
				userOB.userRemove();
				break;
			case 6:
				LoginMain.main(null);
				break;

			default:
				System.out.println("����");
				System.exit(0);
				
			}	
		}
	}
}