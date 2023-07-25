package org.sp.app0725.calendar;

import java.util.Calendar;

public class CalTest {
	
	public static void main(String[] args) {
		//날짜 객체 얻어오기(현재 날짜가 default로 지정되어 있음)
		Calendar cal=Calendar.getInstance();
		
		int yy=cal.get(Calendar.YEAR); //상수 이용. int field -> 상수 이용 //연도 구하기
		int mm=cal.get(Calendar.MONTH);
		int dd=cal.get(Calendar.DATE);
		int day=cal.get(Calendar.DAY_OF_WEEK);
		
//		System.out.println(yy);
//		System.out.println(mm+1); //0월 부터 시작하기 때문에 현재 월은 +1 해줘야 함
//		System.out.println(dd);
//		System.out.println(day); //요일은 일요일=1
		
		//날짜 조작해보기
		//1950.06.25 로 날짜 출력하기
		cal.set(1950, 5, 25);
		
//		cal.set(Calendar.YEAR, 1950);
//		cal.set(Calendar.MONTH, 5);
//		cal.set(Calendar.DATE, 25);
		System.out.println("6.25 발생 요일은 "+cal.get(Calendar.DAY_OF_WEEK));
	}
}
