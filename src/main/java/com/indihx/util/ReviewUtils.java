package com.indihx.util;

public class ReviewUtils {
	public static String primaryState = "00";
	public static String firstPoint = "01";
	public static String secondPoint = "02";
	public static String thirdPoint = "03";
	public static String fourthPoint = "04";
	public static String fivethPoint = "05";
	
	public static String accessRes = "00";
	public static String failRes="01";
	
	public static String getNextState(String currentState,String result) {
		if(failRes.equals(result)) {
			return primaryState;
		}
		
		if(primaryState.equals(currentState)) {
			return firstPoint;
		}
		if(firstPoint.equals(currentState)) {
			return secondPoint;
		}
		if(secondPoint.equals(currentState)) {
			return thirdPoint;
		}
		if(thirdPoint.equals(currentState)) {
			return fourthPoint;
		}
		if(fourthPoint.equals(currentState)) {
			return fivethPoint;
		}
		
		else {
			return primaryState;
		}
	}
}
