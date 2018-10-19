/**
 * 把输入的字符串金额转换成所需的字符串金额
 */
package com.indihx.comm.util;

/**
 * @author linxy
 * 
 */
public class AmtUtil {

	static int indexOfPoint; // 小数点的位置
	static int lengthOfInt; // 整数部分的长度
	static int numOfCommer; // 逗号数量
	static int indexOfFirstCommer; // 首个逗号的位置
	static String intPart = ""; // 整数部分
	static String decimalPart = ""; // 小数部分

	/*
	 * 分解整数与小数
	 */
	private static void dividedString(String amt) {
		try {
			Double.parseDouble(amt);
		} catch (Exception e) {
			System.out.println("不能转换非数值型的字符串");
			return;
		}

		if (amt.indexOf(".") == -1) {
			indexOfPoint = amt.length();
			lengthOfInt = amt.length();
			intPart = amt;
			decimalPart = "";
			/*
			 * System.out.println(""); System.out.println("无小数点");
			 * System.out.println("小数点位置："+indexOfPoint);
			 * System.out.println("整数长度："+lengthOfInt);
			 */
		} else {
			indexOfPoint = amt.indexOf(".");
			lengthOfInt = amt.substring(0, indexOfPoint).length();
			intPart = amt.substring(0, indexOfPoint);
			decimalPart = amt.substring(indexOfPoint + 1, amt.length());
			/*
			 * System.out.println(""); System.out.println("有小数点");
			 * System.out.println("小数点位置："+indexOfPoint);
			 * System.out.println("整数长度："+lengthOfInt);
			 * System.out.println("小数部分："+decimalPart);
			 */
		}
	}

	/*
	 * 转换整数部分，三位加一逗号
	 */
	private static String convertIntPart(String intPart) {
		int pointer = 0; // 指针
		String result = "";
		if (lengthOfInt % 3 == 0) {
			numOfCommer = lengthOfInt / 3 - 1;
			indexOfFirstCommer = 3;
			/*
			 * System.out.println(""); System.out.println("除三余0");
			 * System.out.println("逗号数量："+numOfCommer);
			 * System.out.println("首个逗号的位置："+indexOfFirstCommer);
			 */
		} else {
			numOfCommer = lengthOfInt / 3;
			indexOfFirstCommer = lengthOfInt % 3;
			/*
			 * System.out.println(""); System.out.println("除三余"+lengthOfInt%3);
			 * System.out.println("逗号数量："+numOfCommer);
			 * System.out.println("首个逗号的位置："+indexOfFirstCommer);
			 */
		}

		for (int i = 0; i <= numOfCommer;) {
			if (i == 0) {
				result = intPart.substring(pointer, indexOfFirstCommer);
				pointer += indexOfFirstCommer;
			} else if (i != numOfCommer) {
				result = result + "," + intPart.substring(pointer, pointer + 3);
				pointer += 3;
			} else {
				result = result + "," + intPart.substring(pointer, pointer + 3);
			}
			i++;
		}

		return result;
	}

	/*
	 * 转换小数部分，只取n位小数
	 */
	private static String convertDecimalPart(String decimalPart, int decimalNum) {
		// 超过n位小数
		if (decimalPart.length() > decimalNum) {
			// System.out.println("超过"+decimalNum+"位小数");
			decimalPart = decimalPart.substring(0, decimalNum);
			return "." + decimalPart;
		}
		// 无小数

		else if (decimalPart.equals("") || decimalPart == null) {
			// System.out.println("无小数");
			for (int i = 0; i < decimalNum; i++)
				decimalPart = decimalPart + "0";
			return "." + decimalPart;
		}

		// 小于n位小数
		else if (decimalPart.length() < decimalNum) {
			// System.out.println("小于"+decimalNum+"位小数");
			for (int i = 0; i < (decimalNum - decimalPart.length()); i++)
				decimalPart = decimalPart + "0";
			return "." + decimalPart;
		}
		// else System.out.println("正好"+decimalNum+"位小数");
		return "." + decimalPart;

	}

	/*
	 * 把输入的字符串金额转换成千分位字符串金额
	 */
	public static String toAmtString(String amt) {
		dividedString(amt);
		return convertIntPart(intPart) + convertDecimalPart(decimalPart, 2);
	}

	/*
	 * 保留n位小数
	 */
	public static String save2DecimalPart(String amt) {
		dividedString(amt);
		return intPart + convertDecimalPart(decimalPart, 2);
	}

	public static void main(String args[]) {
		String a = AmtUtil.toAmtString("12");
		System.out.println("result=" + a);
		a = AmtUtil.toAmtString("12.");
		System.out.println("result=" + a);
		a = AmtUtil.toAmtString("12.0");
		System.out.println("result=" + a);
		a = AmtUtil.toAmtString("12.1");
		System.out.println("result=" + a);
		a = AmtUtil.toAmtString("12.12");
		System.out.println("result=" + a);
		a = AmtUtil.toAmtString("12.123");
		System.out.println("result=" + a);

		System.out.println("");
		System.out.println(AmtUtil.save2DecimalPart("1"));
		System.out.println(AmtUtil.save2DecimalPart("1."));
		System.out.println(AmtUtil.save2DecimalPart("1.0"));
		System.out.println(AmtUtil.save2DecimalPart("1.1"));
		System.out.println(AmtUtil.save2DecimalPart("1.12"));
		System.out.println(AmtUtil.save2DecimalPart("1.123"));
	}
}
