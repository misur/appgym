package com.appGym.webGym.test;

import java.util.Calendar;
import java.util.Date;

public class TestTime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date now = Calendar.getInstance().getTime();
//		now.setMonth(now.getMonth()+1);
		System.out.println(now.toString());
	}

}
