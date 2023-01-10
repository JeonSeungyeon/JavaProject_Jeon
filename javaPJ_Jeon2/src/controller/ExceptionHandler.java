package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandler {
	
	// ===================================================================
	
	// -------------------------------------------------------------------
	// 스캐너를 정수를 입력받을 때의 예외처리
	public static int readInt() {
	    Scanner scan = new Scanner(System.in);
	    int num = 0;
	    try {
	        num = scan.nextInt();
	        if(num < 0) {
	        	NegativeException e = new NegativeException();
	        	throw new NegativeException();
	        }
	    } catch (InputMismatchException e) {
	        System.out.print("숫자만 입력해 주세요 : ");
	        return readInt();
	    } catch (NegativeException e) {
			System.out.print("양수만 입력해 주세요 : ");
			return readInt();
		} 
	    return num;
	}
	
	// -------------------------------------------------------------------
	// 스캐너를 문자열를 입력받을 때의 예외처리
	public static String readString() {
		Scanner scan = new Scanner(System.in);
	    String str = null;
	    try {
	    	str = scan.nextLine();
	    	if(str.length() == 0) {
	    		EmptyValueException e = new EmptyValueException();
	    		throw new EmptyValueException();
	    	}
	    } catch(EmptyValueException e) {
	    	System.out.println("입력값이 없습니다.");
	    	System.out.print("다시 입력해 주세요: ");
	    	return readString();
	    }
	    return str;
	}
}

// ===================================================================
// -------------------------------------------------------------------
// 음수 예외 클래스
class NegativeException extends Exception {

	public NegativeException() {}
	public NegativeException(String message) {
		super(message);
	}
}

// -------------------------------------------------------------------
// 빈 문자열 예외 클래스
class EmptyValueException extends Exception	{
	public EmptyValueException() {}
}
