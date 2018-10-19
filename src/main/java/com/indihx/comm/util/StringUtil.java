package com.indihx.comm.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>标    题: 核心框架</p>
 * <p>描    述: 字符串处理实用类</p>
 * <p>版    权: Copyright (c) 2010</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2010-12-13 下午12:32:42</p>
 * @author 产品开发部
 * @version 2.0
 * StringUtil
 */
public final class StringUtil extends AbstractUtil {
	//日志
	private static Logger log = LoggerFactory.getLogger(StringUtil.class); 

	/**
	 * 判断String是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {

		if (ObjectUtil.isEmpty(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断一个字符串是不是数字(包括浮点型)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric1(String str) {

		char[] charArr = str.toCharArray();
		int dot_index = str.indexOf("."); // 小数点的索引
		if (dot_index == 0 || dot_index == charArr.length - 1) {
			return false;
		} else {
			for (int i = 0; i < charArr.length; i++) {
				if (i == dot_index) {
					continue;
				} else {
					if (charArr[i] >= '0' && charArr[i] <= '9') {
						continue;
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * 判断一个字符串是不是数字(包括负数或1-20)
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric2(String str) {
		char[] charArr = str.toCharArray();
		int index = str.indexOf("-"); // 负号的索引
		for (int i = 0; i < charArr.length; i++) {
			if (i == index) {
				continue;
			} else {
				if (charArr[i] >= '0' && charArr[i] <= '9') {
					continue;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public static long genEventCode() {

		return System.currentTimeMillis();
	}

	/**
	 * 把参数word的第一个字母大写
	 * 
	 * @param word
	 * @return
	 */
	public static final String upperFirstWord(String word) {

		String firstWord = word.substring(0, 1).toUpperCase();
		return firstWord + word.substring(1);
	}

	/**
	 * 把参数word的第一个字母小写
	 * 
	 * @param word
	 * @return
	 */
	public static final String lowerFirstWord(String word) {

		String firstWord = word.substring(0, 1).toLowerCase();
		return firstWord + word.substring(1);
	}

	/**
	 * 将字节数组转换为指定编码的字符串
	 * 
	 * @param strbyte
	 * @param destEncoding
	 *            目标编码
	 * @return
	 */
	public static String encodeByte(byte[] strbyte, String destEncoding) {

		String ret = null;

		try {
			ret = new String(strbyte, destEncoding);
		} catch (Exception e) {
			ret = "byte to " + destEncoding + " error. str=[" + strbyte + "]";
			e.printStackTrace();
		}
		return (ret);
	}

	/**
	 * 将字节数组转换为指定编码的字符串
	 * 
	 * @param str
	 * @param srcEncoding
	 *            原字符串编码
	 * @param destEncoding
	 *            目标编码
	 * @return
	 */
	public static String encodeStr(String str, String srcEncoding,
			String destEncoding) {

		String ret = null;

		try {
			ret = new String(str.getBytes(srcEncoding), destEncoding);
		} catch (Exception e) {
			ret = srcEncoding + " to " + destEncoding + " error. str=" + str;
			e.printStackTrace();
		}
		return (ret);
	}

	/**
	 * ISO-8859-1转UTF-8
	 * 
	 * @param str
	 * @return
	 */
	public static String ISO2UTF8(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			ret = "ISO2UTF8 error. str=" + str;
			e.printStackTrace();
		}
		return (ret);
	}

	/**
	 * UTF-8转ISO-8859-1
	 * 
	 * @param str
	 * @return
	 */
	public static String UTF82ISO(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("UTF-8"), "ISO-8859-1");
		} catch (Exception e) {
			ret = "UTF82ISO error. str=" + str;
			e.printStackTrace();
		}
		return (ret);
	}

	/**
	 * ISO-8859-1转GBK
	 * 
	 * @param str
	 * @return
	 */
	public static String ISO2GBK(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("ISO-8859-1"), "GBK");
		} catch (Exception e) {
			ret = "ISO2GBK error. str=" + str;
			e.printStackTrace();
		}
		return (ret);
	}

	/**
	 * GBK转ISO-8859-1
	 * 
	 * @param str
	 * @return
	 */
	public static String GBK2ISO(String str) {

		String ret = null;

		try {
			ret = new String(str.getBytes("GBK"), "ISO-8859-1");
		} catch (Exception e) {
			ret = "GBK2ISO error. str=" + str;
			e.printStackTrace();
		}
		return (ret);
	}

	/**
	 * 把源串sOld中为sPartten的字符串用sReplace替换，
	 * 
	 * @param sOld
	 *            源字符串
	 * @param sPartten
	 *            要替换的字符串
	 * @param sReplace
	 *            替换成的字符串
	 * @return
	 */
	public static String replace(String sOld, String sPartten, String sReplace) {

		if (sOld == null) {
			return null;
		}
		if (sPartten == null) {
			return sOld;
		}
		if (sReplace == null) {
			sReplace = "";
		}
		StringBuffer sBuffer = new StringBuffer();
		int isOldLen = sOld.length();
		int isParttenLen = sPartten.length();
		int iIndex = -1;
		int iHead = 0;
		while ((iIndex = sOld.indexOf(sPartten, iHead)) > -1) {
			sBuffer.append(sOld.substring(iHead, iIndex)).append(sReplace);
			iHead = iIndex + isParttenLen;
		}
		sBuffer.append(sOld.substring(iHead, isOldLen));
		return sBuffer.toString();
	}

	/**
	 * 把输入值格式化输入，如 price=4535.234095 则此函数返回为 4535.23。空则返回“”
	 * 
	 * @param price
	 * @return
	 */
	public static String format2Decimal(BigDecimal price) {
		if (null == price)
			return "";
		else {
			return format2Decimal(price.doubleValue());
		}
	}

	/**
	 * 把输入值格式化保留两位小数，如price=4535.234095 则此函数返回为4535.23
	 * 
	 * @param price
	 * @return
	 */
	public static String format2Decimal(double price) {

		java.text.NumberFormat nf = java.text.NumberFormat
				.getInstance(java.util.Locale.CHINESE);
		java.text.DecimalFormat df = (java.text.DecimalFormat) nf;
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		String pattern = "#0.00";
		df.applyPattern(pattern);
		df.setDecimalSeparatorAlwaysShown(true);
		return df.format(price);
	}

	/**
	 * 把输入值格式化保留两位小数，如price=4535.234095 则此函数返回为4535.23
	 * 
	 * @param price
	 * @return
	 */
	public static String format2Decimal(String price) {

		double d = 0.0;
		if (ObjectUtil.isEmpty(price)) {
			d = 0.0;
		} else {
			try {
				d = Double.parseDouble(price);
			} catch (java.lang.NumberFormatException ex) {
				d = 0.0;
			} catch (java.lang.Exception ex) {
				d = 0.0;
			}
		}
		return format2Decimal(d);
	}

	/**
	 * 将金额转换成千分位格式
	 * 
	 * @param price
	 * @return
	 */
	public static String format2Amt(String price) {

		double d = 0.0;
		if (ObjectUtil.isEmpty(price)) {
			d = 0.0;
		} else {
			try {
				d = Double.parseDouble(price);
			} catch (java.lang.NumberFormatException ex) {
				d = 0.0;
			} catch (java.lang.Exception ex) {
				d = 0.0;
			}
		}
		return MoneyUtil.toAmtString(format2Decimal(d));
	}

	/**
	 * 将金额转换成千分位后添加人民币符号与"元"
	 * 
	 * @param price
	 * @return
	 */
	public static String format2AmtWithYuan(String price) {

		return "￥" + format2Amt(price) + "元";
	}

	/**
	 * 按分隔符号读出字符串的内容
	 * 
	 * @param strlist
	 *            含有分隔符号的字符串
	 * @param ken
	 *            分隔符号
	 * @return 列表
	 */
	public static final List<Object> parseStringToArrayList(String strlist, String ken) {

		StringTokenizer st = new StringTokenizer(strlist, ken);
		if (strlist == null || strlist.equals("") || st.countTokens() <= 0) {
			return new ArrayList<Object>();
		}
		int size = st.countTokens();
		List<Object> strv = new ArrayList<Object>();
		for (int i = 0; i < size; i++) {
			String nextstr = st.nextToken();
			if (!nextstr.equals("")) {
				strv.add(nextstr);
			}
		}
		return strv;
	}

	/**
	 * 实现特殊字符分隔的字符串处理
	 * 
	 * @param str
	 * @param asciiCode
	 *            特殊字符的AscII码
	 * @return by zhangcs
	 */
	public static String[] splitByAscII(String str, int asciiCode) {
		int icount = 0;
		for (int i = 0; i < str.length(); i++)
			if ((int) str.charAt(i) == asciiCode)
				icount++;
		if (icount == 0)
			return null;
		String[] strSplit = new String[icount];// 如果最后一个字段不以分隔符结尾则 String
												// []strSplit=new
												// String[icount+1];
		int pos = 0;
		int index = 0;
		for (int i = 0; i < str.length() && index < icount; i++) {
			if ((int) str.charAt(i) == asciiCode) {
				strSplit[index++] = str.substring(pos, i);
				pos = i + 1;
			}
		}
		return strSplit;
	}

	/**
	 * 把源串str以delim为分割符号，分成一个String数组返回，如 str="aaa,bbb,ccc" , delim="," 则此函数返回为
	 * String[0]="aaa" String[1]="bbb" String[2]="ccc"
	 * 
	 * @param str
	 *            待处理字符串
	 * @param delim
	 *            分隔符
	 * @return
	 */
	public static String[] split(String str, String delim) {

		if (ObjectUtil.isEmpty(delim)) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
		}
		StringTokenizer st = new StringTokenizer(str, delim);
		int size = st.countTokens();
		if (size < 0) {
			return null;
		}
		String[] strReturn = new String[size];
		int i = 0;
		while (st.hasMoreTokens()) {
			strReturn[i++] = st.nextToken();
		}
		return strReturn;
	}

	/**
	 * 把源串str以delim为分割符号，分成一个String数组返回，<br>
	 * 如 str="aaa,,bbb,ccc" , delim=","<br>
	 * 则此函数返回为 String[0]="aaa"<br>
	 * String[1]="" <br>
	 * String[2]="bbb"<br>
	 * String[3]="ccc"
	 * 
	 * @param str
	 *            待处理字符串
	 * @param delim
	 *            分割符
	 * @return 如果传入的字符为空，返回数组string[0]=""
	 */
	public static String[] splitInNull(String str, String delim) {

		if (ObjectUtil.isEmpty(delim)) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
			return strReturn;
		}
		if (ObjectUtil.isEmpty(str)) {
			String[] strReturn = new String[1];
			strReturn[0] = str;
			return strReturn;
		}

		StringTokenizer st = new StringTokenizer(str, delim, true);
		int i = st.countTokens();

		StringBuffer tempString = new StringBuffer();
		for (int j = 0; j < i; j++) {
			tempString.append(" " + st.nextToken() + " ");
		}

		st = new StringTokenizer(tempString.toString(), delim);
		log.info(tempString.toString());
		i = st.countTokens();

		String[] tempArray = new String[i];
		for (int j = 0; j < i; j++) {
			tempArray[j] = st.nextToken().trim();
		}
		return tempArray;
	}

	/**
	 * 将传入的字符串数组转换成字符串，转换之后的格式：'xxx','xxx',...,'xxx'
	 * 
	 * @param stringArray
	 * @return
	 */
	public static String Array2String(String stringArray[]) {

		try {
			String StringResult = "";
			if (ObjectUtil.isEmpty(stringArray)) {
				return StringResult;
			}
			int size = stringArray.length;
			for (int i = 0; i < size; i++) {
				if (ObjectUtil.isEmpty((String) stringArray[i])) {
					continue;
				}
				StringResult += "'" + (String) stringArray[i] + "',";
			}
			StringResult = StringResult.substring(0, StringResult.length() - 1);
			return StringResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 将传入的字符串数组转换成指定分隔符的字符串 以delim为分隔，如 Array2String(array[],"-")，则返回串是'-'分隔的
	 * 
	 * @param stringArray
	 * @param delim
	 *            分割符
	 * @return
	 */
	public static String Array2String(String stringArray[], String delim) {

		try {
			String StringResult = "";
			if (ObjectUtil.isEmpty(stringArray)) {
				return StringResult;
			}
			int size = stringArray.length;
			if (size == 1) {
				return stringArray[0];
			}
			for (int i = 0; i < size - 1; i++) {
				if (ObjectUtil.isEmpty((String) stringArray[i])) {
					continue;
				}
				StringResult += (String) stringArray[i] + delim;
			}
			StringResult += (String) stringArray[size - 1];
			return StringResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 将字符串格式化成 HTML 代码输出
	 * 
	 * @param string
	 *            要格式化的字符串
	 * @return 格式化后的字符串
	 */
	public static String toHtml(String string) {

		if (string == null) {
			return "";
		}
		string = string.replaceAll("&", "&amp;");
		string = string.replaceAll("\"", "&quot;");
		string = string.replaceAll("<", "&lt;");
		string = string.replaceAll(">", "&gt;");
		// string = string.replaceAll("\r\n", "\n");
		string = string.replaceAll("\n", "<br>\n");
		string = string.replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
		string = string.replaceAll(" ", "&nbsp;");
		return string;
	}

	/**
	 * 将sql内的'转换成''
	 * 
	 * @param sql
	 * @return
	 */
	public static String toSql(String sql) {

		if (sql == null) {
			return "";
		}
		sql = sql.replaceAll("'", "''");
		return sql;
	}

	/**
	 * 将sql内的''转换成'
	 * 
	 * @param sql
	 * @return
	 */
	public static String fromSql(String sql) {

		if (sql == null) {
			return "";
		}
		sql = sql.replaceAll("''", "'");
		return sql;
	}

	/**
	 * 得到一对象toString的值，如果是null则返回""
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {

		if (null == obj || ObjectUtil.isEmpty(obj.toString())) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * 得到一对象toString的值，如果是null则返回默认值，如果默认值为null则返回""
	 * 
	 * @param obj
	 * @param defaultObj
	 *            默认值
	 * @return
	 */
	public static String getString(Object obj, Object defaultObj) {

		if (null == obj || ObjectUtil.isEmpty(obj.toString())) {
			if (null == defaultObj)
				return "";
			else
				return defaultObj.toString();
		} else {
			return obj.toString();
		}
	}

	/**
	 * 从字符串首位开始获取指定字符串的指定长度的子串，不考虑中文情况造成的字符串长度变化问题
	 * 
	 * @param sourceString
	 *            源字符串
	 * @param maxLength
	 *            截取最大长度
	 * @return 剪切后的结果，如果源字符串为null，返回空串
	 */
	public static String subString(String sourceString, int maxLength) {

		String innerSourceString = sourceString;
		if (null == sourceString)
		// 如果为null，返回空串
		{
			innerSourceString = "";
		}
		String endString = "";
		int trueLength = innerSourceString.length();
		if (trueLength > maxLength)
		// 实际长度大于需要的长度
		{
			endString = innerSourceString.substring(0, maxLength);
		} else {
			endString = innerSourceString;
		}
		return endString;
	}

	public static char[] format2chat(BigDecimal formatamt) {

		char[] amt = new char[10];
		for (int i = 0; i < 10; i++) {
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
		if (newamt.length() < 10) {
			amt[j] = '￥';
		}
		return amt;
		// System.out.println("amt["+j+"]:" + amt[j]);
	}

	/**
	 * 从字符串首位开始获取指定字符串的指定长度的子串，使用byte形式获取字符串长度避免中文情况造成的字符串长度变化问题
	 * 
	 * @param sourceString
	 * @param maxLength
	 *            截取最大长度
	 * @return 剪切后的结果，如果源字符串为null，返回空串
	 */
	public static String subStringByBytes(String sourceString, int maxLength) {

		String innerSourceString = sourceString;
		if (null == sourceString)
		// 如果为null，返回空串
		{
			innerSourceString = "";
		}
		String endString = "";
		byte[] sourceByte = sourceString.getBytes();

		byte[] retByte = new byte[maxLength];
		int trueLength = sourceByte.length;
		if (trueLength > maxLength)
		// 实际长度大于需要的长度
		{
			System.arraycopy(sourceByte, 0, retByte, 0, maxLength);
			endString = new String(retByte);
		} else {
			endString = innerSourceString;
		}
		return endString;
	}

	/**
	 * 从指定偏移位置开始获取指定字符串的指定长度的子串，使用byte形式获取字符串长度避免中文情况造成的字符串长度变化问题
	 * 
	 * @param sourceString
	 * @param offset
	 *            偏移位置
	 * @param maxLength
	 *            截取最大长度
	 * @return 剪切后的结果，如果源字符串为null，返回空串
	 */
	public static String subStringByBytes(String sourceString, int offset,
			int maxLength) {

		if (maxLength <= 0)
			return "";
		if (offset <= 0)
			return subStringByBytes(sourceString, maxLength);
		String endString = "";
		byte[] sourceByte = sourceString.getBytes();
		byte[] retByte;
		int trueLength = sourceByte.length;
		if (trueLength > (maxLength + offset))
		// 实际长度大于需要的长度
		{
			retByte = new byte[maxLength];
			System.arraycopy(sourceByte, offset, retByte, 0, maxLength);
			endString = new String(retByte);
		} else if (trueLength > offset) {
			retByte = new byte[trueLength - offset];
			System.arraycopy(sourceByte, offset, retByte, 0, trueLength
					- offset);
			endString = new String(retByte);
		} else {
			endString = "";
		}
		return endString;
	}

	/**
	 * 按照所设字符集，返回字符串的长度，使用byte形式获取字符串长度避免中文情况造成的字符串长度变化问题
	 * 
	 * @param str
	 *            判断字符串
	 * @param encoding
	 *            编码设定
	 * @return 此编码的长度
	 */
	public static int getStrLenByBytes(String str, String encoding) {

		int retInt = -1;

		try {
			if (ObjectUtil.isEmpty(str)) {
				retInt = 0;
			} else {

				byte[] byArr = str.getBytes(encoding);

				retInt = byArr.length;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retInt;
	}

	/**
	 * 按照系统字符集，返回字符串的长度，使用byte形式获取字符串长度避免中文情况造成的字符串长度变化问题
	 * 
	 * @param str
	 *            判断字符串
	 * @return 此编码的长度
	 */
	public static int getStrLenByBytes(String str) {

		return getStrLenByBytes(str, System.getProperty("file.encoding"));
	}

	/**
	 * 根据给定的位数，用给定的字符前补足字符串的位数，不考虑中文情况造成的字符串长度变化问题
	 * 
	 * @param strValue
	 *            源字条串
	 * @param ch
	 *            要补的字符
	 * @param iSign
	 *            补足后的长度
	 * @return
	 */
	public static String fillString(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			int iDifference = iSign - strValue.length();
			if (iDifference <= 0) {
				return strValue;
			}
			for (int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}
			strTemp.append(strValue);
			return strTemp.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 根据给定的位数，用给定的字符后补足字符串的位数，不考虑中文情况造成的字符串长度变化问题
	 * 
	 * @param strValue
	 *            源字条串
	 * @param ch
	 *            要补的字符
	 * @param iSign
	 *            补足后的长度
	 * @return
	 */
	public static String fillStringOnTail(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			strTemp.append(strValue);
			int iDifference = iSign - strValue.length();

			if (iDifference <= 0) {
				return strValue;
			}
			for (int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}

			return strTemp.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 根据给定的位数，用给定的字符前补足字符串的位数，使用byte形式获取字符串长度避免中文情况造成的字符串长度变化问题
	 * 
	 * @param strValue
	 *            源字条串
	 * @param ch
	 *            要补的字符
	 * @param iSign
	 *            补足后的长度
	 * @return
	 */
	public static String fillStringByBytes(String strValue, char ch, int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			int iDifference = iSign - strValue.getBytes().length;
			if (iDifference <= 0) {
				return strValue;
			}
			for (int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}
			strTemp.append(strValue);
			return strTemp.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}

	}

	/**
	 * 根据给定的位数，用给定的字符后补足字符串的位数，使用byte形式获取字符串长度避免中文情况造成的字符串长度变化问题
	 * 
	 * @param strValue
	 *            源字条串
	 * @param ch
	 *            要补的字符
	 * @param iSign
	 *            补足后的长度
	 * @return
	 */
	public static String fillStringByBytesOnTail(String strValue, char ch,
			int iSign) {

		try {
			StringBuffer strTemp = new StringBuffer();
			strTemp.append(strValue);
			int iDifference = iSign - strValue.getBytes().length;

			if (iDifference <= 0) {
				return strValue;
			}
			for (int i = 0; i < iDifference; i++) {
				strTemp.append(ch);
			}

			return strTemp.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "";
		}
	}

	/**
	 * 比较两个字符串是否相等（任意一个可以为Null）
	 * 
	 * @param str1
	 *            判断对象String1
	 * @param str2
	 *            判断对象String2
	 * @return 相等返回true，反之为false
	 */
	public static boolean equals(String str1, String str2) {

		return str1 != null ? str1.equals(str2) : str2 == null;
	}

	/**
	 * 判断字符串中是否包含所需判断的字符
	 * 
	 * @param str
	 *            基础字符串
	 * @param searchChar
	 *            判断用字符
	 * @return 包含时返回true，反之为false
	 */
	public static boolean contains(String str, char searchChar) {

		if (ObjectUtil.isEmpty(str)) {
			return false;
		} else {
			return str.indexOf(searchChar) >= 0;
		}
	}

	/**
	 * 判断字符串中是否包含所需判断的字符串
	 * 
	 * @param str
	 *            基础字符串
	 * @param searchChar
	 *            判断用字符串
	 * @return 包含时返回true，反之为false
	 */
	public static boolean contains(String str, String searchStr) {

		if (ObjectUtil.isEmpty(str) || ObjectUtil.isEmpty(searchStr)) {
			return false;
		} else {
			return str.indexOf(searchStr) >= 0;
		}
	}

	/**
	 * 截取该字符串的固定长度，在该字符穿之后加"..."
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String interceptionStr(String str, int length) {

		if (str == null)
			return "";
		if (length < 1)
			return str;
		if (str.length() <= length)
			return str;
		if (str.length() > length)
			return str.substring(0, length) + "...";
		return str;
	}

	/**
	 * 截取该字符串的固定长度，在该字符串之前加"..."
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String subStrFromEnd(String str, int length) {

		if (str == null)
			return "";
		if (length < 1)
			return str;
		if (str.length() <= length)
			return str;
		if (str.length() > length)
			return "..." + str.substring((str.length() - length));
		return str;
	}

	/**
	 * 字符串自增长 by zhangcs@2013 返回一个比str参数的Ascii码值大1的字符串。不含大写字母 终止字符为'-'
	 * */
	public static String getNextVal(String str) {
		return getNextVal(str, 0);
	}

	/**
	 * 字符串自增长 返回一个比str参数的Ascii码值大1的字符串。不含大写字母 终止字符为'-'
	 * 如果nextTimes不应大于str的（数字，英文字母）排列组合数 by zhangcs@2013
	 * */
	public static String getNextVal(String str, int nextTimes) {
		// ASCII码：0~9:48~57; a-z:97~122 //A~Z:65~90;
		byte[] pos = str.getBytes();
		for (int j = 0; j < nextTimes; j++) {
			for (int i = pos.length - 1; i >= 0; i--) {
				if (pos[i] == 45) {
					pos[i] = 48;
					continue;
				}
				if ((pos[i] >= 48 && pos[i] < 57)
						|| (pos[i] >= 97 && pos[i] < 122))
					pos[i]++;
				else if (pos[i] < 48)
					pos[i] = 48;
				else if (pos[i] >= 57 && pos[i] < 97)
					pos[i] = 97;
				else if (pos[i] >= 122)
					pos[i] = 45;
				if (i != pos.length - 1)
					i = pos.length - 1;
				break;
			}
		}
		return new String(pos);

	}

	/**
	 * 去掉楼幢和单元前的补零
	 * 
	 * @param str
	 * @return
	 */
	public static String cutStr(String str) {
		String str1 = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != '0') {
				str1 += str.substring(i);
				break;
			}
		}
		return str1;
	}

	/**
	 * 去掉楼幢和单元前的补零 但是当最后一位是0 的时候，保留最后一位0.
	 * 
	 * @param str
	 * @return
	 */
	public static String cutZero(String str) {
		String str1 = "";
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c != '0') {
				str1 += str.substring(i);
				break;
			}
		}
		if (!ObjectUtil.isEmpty(str) && ObjectUtil.isEmpty(str1)) {
			str1 = "0";
		}
		return str1;
	}
	
	/**
	 * 根据传入长度从头截取字符串并加上省略号
	 * @param str
	 * @param lenth
	 * @return
	 */
	public static String changeStrShort(String str, String lenth){
		int len = Integer.parseInt(lenth);
		if(str.length() <= len){
			return str;
		}else{
			str = str.substring(0, len)+"...";
			return str;
		}
	}
	
	public static void main(String[] args) {
		// String maxStr="jfsc";
		// String endStr=StringUtil.fillString("", '-', maxStr.length());
		// for(int i=0;!maxStr.equals(endStr);i++){
		// maxStr=StringUtil.getNextVal(maxStr);
		// System.out.println("=="+maxStr);
		//
		// }

		//BigDecimal ss = new BigDecimal("17529.66");
		// System.out.println(StringUtil.format2chat(ss));
		// String[] a = splitInNull("aaa,,bbb,ccc", ",");
		// for(int i = 0; i < a.length; i++) {
		// Debug.info(logger, a[i]);
		// }

		System.out.println(StringUtil.cutZero("00003"));
	}
}