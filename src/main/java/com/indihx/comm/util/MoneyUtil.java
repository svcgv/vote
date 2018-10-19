package com.indihx.comm.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 处理金额字符串</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-15 下午04:21:23</p>
 * @author 产品开发部
 * @version 2.0
 * MoneyUtil
 */
public class MoneyUtil extends AbstractUtil {

	//日志
	private static Logger log = LoggerFactory.getLogger(MoneyUtil.class);

	// 参数:总字符串,需要分析的字符串,第i次调用本方法,前一次调用时是否成功
	private static String toChinese(String sStr, String sFour, int i,
			boolean bPre) {

		String sStruct = "";// 回传结果
		for (int j = 0; j < 4; j++) {
			if (sFour.charAt(j) != '0')// 处理每一位数值时，在前面是否需要加“零”
			{
				if (j == 0)// 处理千位
				{
					if (!bPre) {
						sStruct = sStruct + '0';
					}
					sStruct = sStruct + sFour.charAt(j);
				} else// 处理百、十、个位
				{
					if (sFour.charAt(j - 1) == '0') {
						sStruct = sStruct + '0';
					}
					sStruct = sStruct + sFour.charAt(j);
				}

				switch (j)// 单独处理“角”和“分”
				{
				case 0: {
					if (i == 3) {
						sStruct = sStruct + '角';
					} else {
						sStruct = sStruct + '仟';
					}
					break;
				}
				case 1: {
					if (i == 3) {
						sStruct = sStruct + '分';
					} else {
						sStruct = sStruct + '佰';
					}
					break;
				}
				case 2: {
					sStruct = sStruct + '拾';
					break;
				}
				case 3: {
					if (!sStruct.equals("")) {
						switch (i)// 处理单位
						{
						case 0: {
							sStruct = sStruct + "亿";
							break;
						}
						case 1: {
							sStruct = sStruct + "万";
							break;
						}
						case 2: {
							sStruct = sStruct + "元";
							break;
						}
						}
					}
				}
				}
			} else// 当个位为零时，处理单位
			{
				if (!sStruct.equals("") && j == 3) {
					switch (i) {
					case 0: {
						sStruct = sStruct + "亿";
						break;
					}
					case 1: {
						sStruct = sStruct + "万";
						break;
					}
					}
				}
				if (i == 2 && j == 3
						&& (!sStr.equals("") || !sStruct.equals("")))// 是否加“圆”字
				{
					sStruct = sStruct + "元";
				}
			}
		}
		return sStruct;
	}

	/**
	 * 转化为大写汉字的金额值
	 * 
	 * @param digit
	 *            最多有两位小数,本方法最大支持金额999999999999.99
	 * @return String 转化为大写汉字的金额值
	 */
	public static String toRMB(String digit) {

		return toRMB(Double.parseDouble(digit));
	}

	/**
	 * 转化为大写汉字的金额值
	 * 
	 * @param digit
	 *            最多有两位小数,本方法最大支持金额999999999999.99
	 * @return String
	 */
	public static String toRMB(double digit) {

		// 将数据格式化为四位小数
		DecimalFormat df = new DecimalFormat("#.0000");
		StringBuffer sbDigit = new StringBuffer(df.format(digit));
		sbDigit.replace(sbDigit.length() - 2, sbDigit.length(), "00");
		String sDigit = "";// 将double转化为string
		sDigit = sbDigit.toString();
		sDigit = sDigit.substring(0, sDigit.length() - 5)
				+ sDigit.substring(sDigit.length() - 4);// 去除小数点

		// 将字符串补齐16位，利于分组
		// sDigit = sDigit + "00";
		if (sDigit.length() > 16) {
			return "款项过大！";
		}

		if (sDigit.length() < 16) {
			int iLength = 16 - sDigit.length();
			for (int i = 0; i < iLength; i++) {
				sDigit = "0" + sDigit;
			}
		}
		if (sDigit.equals("0000000000000000")) {
			return "零元整";
		}
		String sChinese = sDigit;
		String sFour = "";// 每四位构造一个string
		boolean bPreStr = true;// 前一个string是否构造成功
		sDigit = "";// 总字符串
		// 将字符串分为四组，每一组单独处理，都处理完后串接
		for (int i = 0; i < 4; i++) {
			sFour = toChinese(sDigit, sChinese.substring(i * 4, i * 4 + 4), i,
					bPreStr);
			// Debug.info(sFour);
			if (sFour.length() == 0 || sFour.length() == 1) {
				bPreStr = false;
			} else if (sFour.charAt(sFour.length() - 2) < '0'
					|| sFour.charAt(sFour.length() - 2) > '9') {
				bPreStr = false;
			} else {
				bPreStr = true;
			}
			sDigit = sDigit + sFour;
		}
		// 去掉字符串最前面的‘0’
		for (;;) {
			if (sDigit.charAt(0) == '0') {
				sDigit = sDigit.substring(1);
			} else {
				break;
			}
		}

		sChinese = "";
		for (int i = 0; i < sDigit.length(); i++) {
			if (sDigit.charAt(i) >= '0' && sDigit.charAt(i) <= '9') {
				switch (sDigit.charAt(i)) {
				case '1': {
					sChinese = sChinese + "壹";
					break;
				}
				case '2': {
					sChinese = sChinese + "贰";
					break;
				}
				case '3': {
					sChinese = sChinese + "叁";
					break;
				}
				case '4': {
					sChinese = sChinese + "肆";
					break;
				}
				case '5': {
					sChinese = sChinese + "伍";
					break;
				}
				case '6': {
					sChinese = sChinese + "陆";
					break;
				}
				case '7': {
					sChinese = sChinese + "柒";
					break;
				}
				case '8': {
					sChinese = sChinese + "捌";
					break;
				}
				case '9': {
					sChinese = sChinese + "玖";
					break;
				}
				case '0': {
					sChinese = sChinese + "零";
					break;
				}
				}
			} else {
				sChinese = sChinese + sDigit.charAt(i);
			}
		}

		if (!sDigit.endsWith("分"))// 有"分"不加"整"
		{
			sChinese = sChinese + "整";
		}

		return sChinese;
	}

	static int indexOfPoint; // 小数点的位置
	static int lengthOfInt; // 整数部分的长度
	static int numOfCommer; // 逗号数量
	static int indexOfFirstCommer; // 首个逗号的位置
	static String intPart = ""; // 整数部分
	static String decimalPart = ""; // 小数部分

	/**
	 * 把输入的字符串金额转换成千分位字符串金额
	 * 
	 * @param amt
	 *            金额字符串
	 * @return
	 */
	public static String toAmtString(String amt) {

		String ret = "";
		BigDecimal d = ObjectUtil.getZeroBigDecimal();
		boolean flag = false;
		try {
			d = new BigDecimal(amt);
			if (d.compareTo(ObjectUtil.getZeroBigDecimal()) < 0) {
				d = d.abs();
				flag = true;
			}
		} catch (java.lang.NumberFormatException ex) {

		}
		dividedString(d.toString());
		ret = convertIntPart(intPart) + convertDecimalPart(decimalPart, 2);
		if (flag)
			ret = "-" + ret;
		return ret;
	}

	/**
	 * 把输入的字符串金额转换保留2位小数字符串金额
	 * 
	 * @param amt
	 *            金额字符串
	 * @return
	 */
	public static String save2DecimalPart(String amt) {

		dividedString(amt);
		return intPart + convertDecimalPart(decimalPart, 2);
	}

	/**
	 * 把输入的字符串金额转换保留n位小数字符串金额
	 * 
	 * @param amt
	 *            金额字符串
	 * @param scale
	 *            小数位数
	 * @return
	 */
	public static String saveNDecimalPart(String amt, int scale) {

		dividedString(amt);
		return intPart + convertDecimalPart(decimalPart, scale);
	}

	/**
	 * 分解整数与小数
	 * 
	 * @param amt
	 */
	private static void dividedString(String amt) {

		try {
			Double.parseDouble(amt);
		} catch (Exception e) {
			log.info("不能转换非数值型的字符串");
			return;
		}

		if (amt.indexOf(".") == -1) {
			indexOfPoint = amt.length();
			lengthOfInt = amt.length();
			intPart = amt;
			decimalPart = "";
			/*
			 * Debug.info(""); Debug.info("无小数点");
			 * Debug.info("小数点位置："+indexOfPoint);
			 * Debug.info("整数长度："+lengthOfInt);
			 */
		} else {
			indexOfPoint = amt.indexOf(".");
			lengthOfInt = amt.substring(0, indexOfPoint).length();
			intPart = amt.substring(0, indexOfPoint);
			decimalPart = amt.substring(indexOfPoint + 1, amt.length());
			/*
			 * Debug.info(""); Debug.info("有小数点");
			 * Debug.info("小数点位置："+indexOfPoint);
			 * Debug.info("整数长度："+lengthOfInt);
			 * Debug.info("小数部分："+decimalPart);
			 */
		}
	}

	/**
	 * 转换整数部分，三位加一逗号
	 * 
	 * @param intPart
	 * @return
	 */
	private static String convertIntPart(String intPart) {

		int pointer = 0; // 指针
		String result = "";
		if (lengthOfInt % 3 == 0) {
			numOfCommer = lengthOfInt / 3 - 1;
			indexOfFirstCommer = 3;
			/*
			 * Debug.info(""); Debug.info("除三余0");
			 * Debug.info("逗号数量："+numOfCommer);
			 * Debug.info("首个逗号的位置："+indexOfFirstCommer);
			 */
		} else {
			numOfCommer = lengthOfInt / 3;
			indexOfFirstCommer = lengthOfInt % 3;
			/*
			 * Debug.info(""); Debug.info("除三余"+lengthOfInt%3);
			 * Debug.info("逗号数量："+numOfCommer);
			 * Debug.info("首个逗号的位置："+indexOfFirstCommer);
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

	/**
	 * 转换小数部分，只取n位小数
	 * 
	 * @param decimalPart
	 * @param decimalNum
	 * @return
	 */
	private static String convertDecimalPart(String decimalPart, int decimalNum) {

		// 超过n位小数
		if (decimalPart.length() > decimalNum) {
			// Debug.info("超过"+decimalNum+"位小数");
			decimalPart = decimalPart.substring(0, decimalNum);
			return "." + decimalPart;
		}
		// 无小数

		else if (decimalPart.equals("") || decimalPart == null) {
			// Debug.info("无小数");
			for (int i = 0; i < decimalNum; i++)
				decimalPart = decimalPart + "0";
			return "." + decimalPart;
		}

		// 小于n位小数
		else if (decimalPart.length() < decimalNum) {
			// Debug.info("小于"+decimalNum+"位小数");
			for (int i = 0; i < (decimalNum - decimalPart.length()); i++)
				decimalPart = decimalPart + "0";
			return "." + decimalPart;
		}
		// else Debug.info("正好"+decimalNum+"位小数");
		return "." + decimalPart;
	}

	/**
	 * 转换为数组
	 * 
	 * @param formatamt
	 * @param maxsize
	 * @return
	 */
	public static char[] format2chatlink(BigDecimal formatamt, int maxsize) {
		char[] amt = new char[maxsize];
		for (int i = 0; i < maxsize; i++) {
			amt[i] = ' ';
		}
		if (formatamt == null) {
			return amt;
		}
		String tran_amt = AmtUtil.save2DecimalPart(formatamt.toString());
		int delIndex = tran_amt.indexOf(".");
		String newamt = tran_amt.substring(0, delIndex)
				+ tran_amt.substring((delIndex + 1));
		// System.out.println("newamt:" + newamt);

		int j = 0;
		for (int i = newamt.length() - 1; i >= 0; i--) {
			amt[j] = newamt.charAt(i);
			// System.out.println("amt["+j+"]:" + amt[j]);
			j++;
		}
		if (newamt.length() < maxsize) {
			amt[j] = '￥';
		}
		return amt;
		// System.out.println("amt["+j+"]:" + amt[j]);
	}

	public static void main(String args[]) {

		String a = toAmtString("12");
		log.info( "result=" + a);
		a = toAmtString("12.");
		log.info( "result=" + a);
		a = toAmtString("12.0");
		log.info( "result=" + a);
		a = toAmtString("12.1");
		log.info( "result=" + a);
		a = toAmtString("12.12");
		log.info( "result=" + a);
		a = toAmtString("12.123");
		log.info( "result=" + a);

		log.info( "");
		log.info( save2DecimalPart("1"));
		log.info( save2DecimalPart("1."));
		log.info( save2DecimalPart("1.0"));
		log.info( save2DecimalPart("1.1"));
		log.info( save2DecimalPart("1.12"));
		log.info( save2DecimalPart("1.123"));
	}
}
