package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Exceptions {
	
	// ===================================================================
	// InputMismatchException 처리
	
	// -------------------------------------------------------------------
	// int에 대한 exception 처리
	public static int readInt() {
	    Scanner scan = new Scanner(System.in);
	    int num;
	    try {
	        num = scan.nextInt();
	    } catch (InputMismatchException e) {
	        System.out.print("숫자만 입력해 주세요 : ");
	        return readInt();
	    } finally {
	    	// scan.close()는 전체 메소드 마지막에 쓰기
	    	scan.close();
	    }
	    return num;
	}
	

//	public static String readString() {
//		Scanner scan = new Scanner(System.in);
//	    String str = null;
//	    do {
//	    	str = scan.next();
//	    	if(str.length() == 0) {
//	    		System.out.print("입력값이 없습니다 : ");
//	    	}
//	    } while(!str.isEmpty());
//
//	    return str;
//	}
}
