package com.cha.board.test;

public class Test1 {

	public static void main(String[] args) {
		
		MyClass mc = new MyClass();
		
		mc.<Integer>printValue(100);		
		
		mc.printStr("hello"); // 1. 함수의 사용과 관리가 복잡해짐.
		mc.<String>printValue("hello"); // 2. 함수 오버로딩 -> 코드 중복이 많이 발생
		mc.<String>printValue("hello"); // 3. 자료형을 매개변수로(제너릭)
		
		
		Morning m1 = new Morning();
		m1.drive();
		
		
		
		Car c1 = new Morning();
		c1.drive();
		
		c1 = new Sonata();
		c1.drive();
	}

}

interface Car {
	public void drive(); 
}

class Morning implements Car {
	public void drive() {
		System.out.println("모닝이 달립니다.");
	}
}

class Sonata implements Car {
	public void drive() {
		System.out.println("소나타가 달립니다.");
	}
}

class MyClass {
	
	public<T> void printValue(T value) {
		System.out.println(value);
	}
	
	public void printValue(String value) {
		System.out.println(value);
	}
	
	public void printStr(String value) {
		System.out.println(value);
	}
	
}
