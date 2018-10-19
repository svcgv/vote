/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.indihx.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 
 * 字符串操作支持方法集合.
 *
 * 该类主要应用于框架内部，参考Jakarta's Commons Lang
 *
 * <p>This class delivers some simple functionality that should really
 * be provided by the core Java <code>String</code> and {@link StringBuffer}
 * classes, such as the ability to {@link #replace} all occurrences of a given
 * substring in a target string. It also provides easy-to-use methods to
 * convert between delimited strings, such as CSV strings, and collections and
 * arrays.
 *
 * @author zhengwei
 * @since 2018-3-12
 */
public abstract class StringUtils {

	//文件夹分隔符.
	private static final String FOLDER_SEPARATOR = "/";

	//windows 文件夹分隔符
	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	//上级目录符号.
	private static final String TOP_PATH = "..";

	//当前目录符号.
	private static final String CURRENT_PATH = ".";

	//扩展分隔符.
	private static final char EXTENSION_SEPARATOR = '.';


	//---------------------------------------------------------------------
	// 字符串操作的简便方法.
	//---------------------------------------------------------------------
	
	/**
	 * 获取自字符串
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return obj.toString();
		}
	}

	/**
	 * 检查给给定的字符是否不为空，且长度大于零。
	 * 注意：当字符串仅仅包含一个空格时，返回值也是true.
	 * <p><pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str 被检查的字符串.
	 * @return <code>true</code> 不为空，且长度>0时。
	 * @see #hasText(String)
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 检查给定的字符串中是否包含文本信息(至少一个非空字符串).
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str 被检查字符串 (可能为 <code>null</code>)
	 * @return <code>true</code> 当长度>0,不为<code>null</code>,且至少包括一个非空字符. 
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查给定的字符串是否包含空格.
	 * @param 被检查字符串 (可能为 <code>null</code>)
	 * @return <code>true</code> 如果有一个空格字符时.
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去除字符串头部的空格字符.
	 * 
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 去除头部字符.
	 * 
	 * @param str the String to check
	 * @param leadingCharacter the leading character to be trimmed
	 * @return the trimmed String
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 去除尾部字符.
	 * 
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 去除所有空格字符，包括头部、尾部和中间。
	 * 
	 * @param str the String to check
	 * @return the trimmed String
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return buf.toString();
	}


	/**
	 * 检查某字符串是否忽略大小写以一个特定字符串开始.
	 * 
	 * @param str the String to check
	 * @param prefix the prefix to look for
	 * @see java.lang.String#startsWith
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 检查某字符串是否忽略大小写以一个特定字符串结束.
	 * 
	 * @param str the String to check
	 * @param suffix the suffix to look for
	 * @see java.lang.String#endsWith
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * 计算某子字符串在字符串中出现的次数.
	 * 
	 * @param str string to search in. Return 0 if this is null.
	 * @param sub string to search for. Return 0 if this is null.
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0, pos = 0, idx = 0;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 用新字符串替换所有老字符串.
	 * 
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * 在某字符串中删除所有的指定字符串.
	 * 
	 * @param inString the original String
	 * @param pattern the pattern to delete all occurrences of
	 * @return the resulting String
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * 删除给定字符串中包含的任何字符.
	 * 
	 * @param inString the original String
	 * @param charsToDelete a set of characters to delete.
	 * E.g. "az\n" will delete 'a's, 'z's and new lines.
	 * @return the resulting String
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}


	//---------------------------------------------------------------------
	// 字符串格式化操作的简便方法
	//---------------------------------------------------------------------

	/**
	 * 为非<code>null</code>字符串添加单引号括起.
	 * 
	 * @param str the input String (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or <code>null<code> if the input was <code>null</code>
	 */
	public static String quote(String str) {
		return (str != null ? "'" + str + "'" : null);
	}

	/**
	 * 如果给定的对象是字符串用单引号括起.
	 * 
	 * @param obj the input Object (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or the input object as-is if not a String
	 */
	public static Object quoteIfString(Object obj) {
		return (obj instanceof String ? quote((String) obj) : obj);
	}

	/**
	 * 去除限定修饰.
	 * Unqualify a string qualified by a '.' dot character. For example,
	 * "this.name.is.qualified", returns "qualified".
	 * @param qualifiedName the qualified name
	 */
	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, '.');
	}

	/**
	 * 去除限定修饰,可指定分隔符.
	 * Unqualify a string qualified by a separator character. For example,
	 * "this:name:is:qualified" returns "qualified" if using a ':' separator.
	 * @param qualifiedName the qualified name
	 * @param separator the separator
	 */
	public static String unqualify(String qualifiedName, char separator) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
	}

	/**
	 * 将字符串首字符转换为大写。
	 * 
	 * Capitalize a <code>String</code>, changing the first letter to
	 * upper case as per {@link Character#toUpperCase(char)}.
	 * No other letters are changed.
	 * @param str the String to capitalize, may be <code>null</code>
	 * @return the capitalized String, <code>null</code> if null
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}

	/**
	 * 将字符串首字符转换为小写。
	 * 
	 * Uncapitalize a <code>String</code>, changing the first letter to
	 * lower case as per {@link Character#toLowerCase(char)}.
	 * No other letters are changed.
	 * @param str the String to uncapitalize, may be <code>null</code>
	 * @return the uncapitalized String, <code>null</code> if null
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}

	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}

	/**
	 * 从给定的文件路径中获取文件名.
	 * 
	 * Extract the filename from the given path,
	 * e.g. "mypath/myfile.txt" -> "myfile.txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename, or <code>null</code> if none
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}

	/**
	 * 从给定的文件路径中获取文件后缀.
	 * 
	 * Extract the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename extension, or <code>null</code> if none
	 */
	public static String getFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(sepIndex + 1) : null);
	}

	/**
	 * 去除文件路径中的文件后缀.
	 * 
	 * Strip the filename extension from the given path,
	 * e.g. "mypath/myfile.txt" -> "mypath/myfile".
	 * @param path the file path (may be <code>null</code>)
	 * @return the path with stripped filename extension,
	 * or <code>null</code> if none
	 */
	public static String stripFilenameExtension(String path) {
		if (path == null) {
			return null;
		}
		int sepIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
		return (sepIndex != -1 ? path.substring(0, sepIndex) : path);
	}

	/**
	 * 在原文件路径上追加相对路径.
	 * 
	 * Apply the given relative path to the given path,
	 * assuming standard Java folder separation (i.e. "/" separators);
	 * @param path the path to start from (usually a full file path)
	 * @param relativePath the relative path to apply
	 * (relative to the full file path above)
	 * @return the full file path that results from applying the relative path
	 */
	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		}
		else {
			return relativePath;
		}
	}

	/**
	 * 规范化文件路径.
	 * 
	 * Normalize the path by suppressing sequences like "path/.." and
	 * inner simple dots.
	 * <p>The result is convenient for path comparison. For other uses,
	 * notice that Windows separators ("\") are replaced by simple slashes.
	 * @param path the original path
	 * @return the normalized path
	 */
	public static String cleanPath(String path) {
		String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

		// Strip prefix from path to analyze, to not treat it as part of the
		// first path element. This is necessary to correctly parse paths like
		// "file:core/../core/io/Resource.class", where the ".." should just
		// strip the first "core" directory while keeping the "file:" prefix.
		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			pathToUse = pathToUse.substring(prefixIndex + 1);
		}

		String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
		List pathElements = new LinkedList();
		int tops = 0;

		for (int i = pathArray.length - 1; i >= 0; i--) {
			if (CURRENT_PATH.equals(pathArray[i])) {
				// Points to current directory - drop it.
			}
			else if (TOP_PATH.equals(pathArray[i])) {
				// Registering top path found.
				tops++;
			}
			else {
				if (tops > 0) {
					// Merging path element with corresponding to top path.
					tops--;
				}
				else {
					// Normal path element found.
					pathElements.add(0, pathArray[i]);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, TOP_PATH);
		}

		return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
	}

	/**
	 * 比较两个文件路径是否相同。
	 * Compare two paths after normalization of them.
	 * @param path1 first path for comparison
	 * @param path2 second path for comparison
	 * @return whether the two paths are equivalent after normalization
	 */
	public static boolean pathEquals(String path1, String path2) {
		return cleanPath(path1).equals(cleanPath(path2));
	}

	/**
	 * 解析本地字符串返回Locale对象.
	 * 
	 * Parse the given <code>localeString</code> into a {@link Locale}.
	 * <p>This is the inverse operation of {@link Locale#toString Locale's toString}.
	 * @param localeString the locale string, following <code>Locale's</code>
	 * <code>toString()</code> format ("en", "en_UK", etc);
	 * also accepts spaces as separators, as an alternative to underscores
	 * @return a corresponding <code>Locale</code> instance
	 */
	public static Locale parseLocaleString(String localeString) {
		String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
		String language = (parts.length > 0 ? parts[0] : "");
		String country = (parts.length > 1 ? parts[1] : "");
		String variant = "";
		if (parts.length >= 2) {
			// There is definitely a variant, and it is everything after the country
			// code sans the separator between the country code and the variant.
			int endIndexOfCountryCode = localeString.indexOf(country) + country.length();
			// Strip off any leading '_' and whitespace, what's left is the variant.
			variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
			if (variant.startsWith("_")) {
				variant = trimLeadingCharacter(variant, '_');
			}
		}
		return (language.length() > 0 ? new Locale(language, country, variant) : null);
	}


	//---------------------------------------------------------------------
	// 字符串与数组、集合工作的简便方法.
	//---------------------------------------------------------------------

	/**
	 * 添加字符串到字符串数组中。
	 *
	 * @param array the array to append to (can be <code>null</code>)
	 * @param str the String to append
	 * @return the new array (never <code>null</code>)
	 */
	public static String[] addStringToArray(String[] array, String str) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[] {str};
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * 合并两个字符串数组.
	 * 
	 * Concatenate the given String arrays into one,
	 * with overlapping array elements included twice.
	 * <p>The order of elements in the original arrays is preserved.
	 * @param array1 the first array (can be <code>null</code>)
	 * @param array2 the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
	 */
	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 合并两个字符串数组，去除重复元素.
	 * 
	 * Merge the given String arrays into one, with overlapping
	 * array elements only included once.
	 * <p>The order of elements in the original arrays is preserved
	 * (with the exception of overlapping elements, which are only
	 * included on their first occurence).
	 * @param array1 the first array (can be <code>null</code>)
	 * @param array2 the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (ObjectUtils.isEmpty(array1)) {
			return array2;
		}
		if (ObjectUtils.isEmpty(array2)) {
			return array1;
		}
		List result = new ArrayList();
		result.addAll(Arrays.asList(array1));
		for (int i = 0; i < array2.length; i++) {
			String str = array2[i];
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * 字符串数组排序.
	 * 
	 * Turn given source String array into sorted array.
	 * @param array the source array
	 * @return the sorted array (never <code>null</code>)
	 */
	public static String[] sortStringArray(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * 将集合对象转换为字符串数组。
	 * 
	 * Copy the given Collection into a String array.
	 * The Collection must contain String elements only.
	 * @param collection the Collection to copy
	 * @return the String array (<code>null</code> if the passed-in
	 * Collection was <code>null</code>)
	 */
	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}

	/**
	 * 去除字符串数组元素的首部或尾部空格字符.
	 * 
	 * Trim the elements of the given String array,
	 * calling <code>String.trim()</code> on each of them.
	 * @param array the original String array
	 * @return the resulting array (of the same size) with trimmed elements
	 */
	public static String[] trimArrayElements(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return new String[0];
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}

	/**
	 * 去除字符串数组中的重复元素。
	 * 
	 * Remove duplicate Strings from the given array.
	 * Also sorts the array, as it uses a TreeSet.
	 * @param array the String array
	 * @return an array without duplicates, in natural sort order
	 */
	public static String[] removeDuplicateStrings(String[] array) {
		if (ObjectUtils.isEmpty(array)) {
			return array;
		}
		Set set = new TreeSet();
		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}
		return toStringArray(set);
	}

	/**
	 * 将字符串转换为字符串数组，需指定转换分隔符.
	 * 
	 * Split a String at the first occurrence of the delimiter.
	 * Does not include the delimiter in the result.
	 * @param toSplit the string to split
	 * @param delimiter to split the string up with
	 * @return a two element array with index 0 being before the delimiter, and
	 * index 1 being after the delimiter (neither element includes the delimiter);
	 * or <code>null</code> if the delimiter wasn't found in the given input String
	 */
	public static String[] split(String toSplit, String delimiter) {
		if (!hasLength(toSplit) || !hasLength(delimiter)) {
			return null;
		}
		int offset = toSplit.indexOf(delimiter);
		if (offset < 0) {
			return null;
		}
		String beforeDelimiter = toSplit.substring(0, offset);
		String afterDelimiter = toSplit.substring(offset + delimiter.length());
		return new String[] {beforeDelimiter, afterDelimiter};
	}

	/**
	 * 将字符串数组转换为Properties对象，其中字符串数组中的元素类似于
	 * name:jack其中":"是可选的拆分标志符.
	 * 
	 * Take an array Strings and split each element based on the given delimiter.
	 * A <code>Properties</code> instance is then generated, with the left of the
	 * delimiter providing the key, and the right of the delimiter providing the value.
	 * <p>Will trim both the key and value before adding them to the
	 * <code>Properties</code> instance.
	 * @param array the array to process
	 * @param delimiter to split each element using (typically the equals symbol)
	 * @return a <code>Properties</code> instance representing the array contents,
	 * or <code>null</code> if the array to process was null or empty
	 */
	public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
		return splitArrayElementsIntoProperties(array, delimiter, null);
	}

	/**
	 * 将字符串数组转换为Properties对象,而且可以剔除指定的字符串，
	 * 例如：字符串数组中有一元素为name : jack,该方法可提出空格，转换属性为name=jack
	 * 
	 * Take an array Strings and split each element based on the given delimiter.
	 * A <code>Properties</code> instance is then generated, with the left of the
	 * delimiter providing the key, and the right of the delimiter providing the value.
	 * <p>Will trim both the key and value before adding them to the
	 * <code>Properties</code> instance.
	 * @param array the array to process
	 * @param delimiter to split each element using (typically the equals symbol)
	 * @param charsToDelete one or more characters to remove from each element
	 * prior to attempting the split operation (typically the quotation mark
	 * symbol), or <code>null</code> if no removal should occur
	 * @return a <code>Properties</code> instance representing the array contents,
	 * or <code>null</code> if the array to process was <code>null</code> or empty
	 */
	public static Properties splitArrayElementsIntoProperties(
			String[] array, String delimiter, String charsToDelete) {

		if (ObjectUtils.isEmpty(array)) {
			return null;
		}
		Properties result = new Properties();
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			if (charsToDelete != null) {
				element = deleteAny(array[i], charsToDelete);
			}
			String[] splittedElement = split(element, delimiter);
			if (splittedElement == null) {
				continue;
			}
			result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
		}
		return result;
	}

	/**
	 * 将字符串拆分为字符串数组，可指定拆分符，拆分后将忽略空格，
	 * 如 str = "a,b, ,c" 拆分后的字符串数组为{"a","b","c"}
	 * 
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * 将字符串拆分为字符串数组
	 *  
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's <code>trim</code>
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens (<code>null</code> if the input String
	 * was <code>null</code>)
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}

	/**
	 * 指定一个不受限字符串，将给定字符串拆分为字符串数组.
	 * 
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of potential
	 * delimiter characters - in contrast to <code>tokenizeToStringArray</code>.
	 * @param str the input String
	 * @param delimiter the delimiter between elements (this is a single delimiter,
	 * rather than a bunch individual delimiter characters)
	 * @return an array of the tokens in the list
	 * @see #tokenizeToStringArray
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter) {
		return delimitedListToStringArray(str, delimiter, null);
	}

	/**
	 * 指定一个不受限字符串，将给定字符串拆分为字符串数组,可删除指定字符.
	 * 
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of potential
	 * delimiter characters - in contrast to <code>tokenizeToStringArray</code>.
	 * @param str the input String
	 * @param delimiter the delimiter between elements (this is a single delimiter,
	 * rather than a bunch individual delimiter characters)
	 * @param charsToDelete a set of characters to delete. Useful for deleting unwanted
	 * line breaks: e.g. "\r\n\f" will delete all new lines and line feeds in a String.
	 * @return an array of the tokens in the list
	 * @see #tokenizeToStringArray
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] {str};
		}
		List result = new ArrayList();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
			}
		}
		else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(deleteAny(str.substring(pos), charsToDelete));
			}
		}
		return toStringArray(result);
	}

	/**
	 * Convert a CSV list into an array of Strings.
	 * @param str the input String
	 * @return an array of Strings, or the empty array in case of empty input
	 */
	public static String[] commaDelimitedListToStringArray(String str) {
		return delimitedListToStringArray(str, ",");
	}

	/**
	 * 将字符串转换为一个java.util.Set对象，字符串需以“,”分隔。
	 * 
	 * Convenience method to convert a CSV string list to a set.
	 * Note that this will suppress duplicates.
	 * @param str the input String
	 * @return a Set of String entries in the list
	 */
	public static Set commaDelimitedListToSet(String str) {
		Set set = new TreeSet();
		String[] tokens = commaDelimitedListToStringArray(str);
		for (int i = 0; i < tokens.length; i++) {
			set.add(tokens[i]);
		}
		return set;
	}

	/**
	 * 将集合转换为字符串，不仅可以指定元素之间的分隔符，还可为集合元素指定前缀和后缀，
	 * 
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @param prefix the String to start each element with
	 * @param suffix the String to end each element with
	 */
	public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * 将集合转换为字符串，可以指定元素之间的分隔符。
	 *  
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 */
	public static String collectionToDelimitedString(Collection coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}

	/**
	 * 将集合转换为字符串，以“,”隔符。
	 * 
	 * Convenience method to return a Collection as a CSV String.
	 * E.g. useful for <code>toString()</code> implementations.
	 * @param coll the Collection to display
	 */
	public static String collectionToCommaDelimitedString(Collection coll) {
		return collectionToDelimitedString(coll, ",");
	}

	/**
	 * 将集合转换为'a','b','c'这类的字符串，
	 * 这些字符串可以用于作为sql语句中In的查询条件.
	 * 
	 * @param coll 集合.
	 * @return
	 */
	public static String collectionToSqlInParamString(Collection coll) {
		return collectionToDelimitedString(coll, ",", "'", "'");
	}

	/**
	 * 将对象数组转换为字符串，可以指定元素之间的分隔符。
	 * 
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param arr the array to display
	 * @param delim the delimiter to use (probably a ",")
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (ObjectUtils.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * 将对象数组转换为字符串，以“,”隔符。
	 * 
	 * Convenience method to return a String array as a CSV String.
	 * E.g. useful for <code>toString()</code> implementations.
	 * @param arr the array to display
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}

	/**
	 * 判断字符串是否为空.
	 * 
	 * @param str 
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) || "null".equals(str) || "NULL".equals(str);
	}

	/**
	 * 判断对象是否为空。
	 * 
	 * @param o
	 * @return
	 */
	public static boolean isEmpty(Object o) {
		if(o == null || "".equals(o) || "null".equals(o) || "NULL".equals(o)){
			return true;
		}
		else if(!(o instanceof String)&&o.toString().equals("0")){
			//不是字符串，且其值为0也算是空。
			return true;
		}
		return false;
	}

	public static StringTokenizer toSqlTokenizer(String str){
		return new StringTokenizer(str," \n\r\f\t,()=<>&|+-=/*'^![]~\\",true);
	}
	
	/**
	 * list中的值顺序替换将字符串中的字符.
	 * 如：
	 *  str  = "va = ? and vb = ? and vc = ?" 
	 *  replaceStr = "?"
	 *  list = [aaa,bbb,ccc]
	 *  替换后的str= "va = 'aaa' and vb = 'bbb' vc = 'ccc'"
	 * @param str
	 * @param replaceStr
	 * @param list
	 * @return
	 */
	public static String replaceStrWithList(String str,String replaceStr,List list){
		return replaceStrWithArray(str, replaceStr, list.toArray());
	}

	/**
	 * array中的值顺序替换将字符串中的字符.
	 * 如：
	 *  str  = "va = ? and vb = ? and vc = ?" 
	 *  replaceStr = "?"
	 *  list = [aaa,bbb,ccc]
	 *  替换后的str= "va = 'aaa' and vb = 'bbb' vc = 'ccc'"
	 * @param str
	 * @param replaceStr
	 * @param array
	 * @return
	 */
	public static String replaceStrWithArray(String str,String replaceStr,Object[] array){
		StringBuffer buffer = new StringBuffer();

		StringTokenizer tokenizer = toSqlTokenizer(str);
		int cursor = 0;
		while (tokenizer.hasMoreTokens()) {
			String t = tokenizer.nextToken();
			if(t.equals(replaceStr)){
				buffer.append("'"+array[cursor++]+"'");
			}
			else{
				buffer.append(t);
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 将数字转为中文格式输出
	 * 
	 * @param num
	 *            0至9的整型数值
	 * @return 中文字符
	 */
	public final static char cNum2Num_ZH(char num) {
		switch (num) {
		case '0':
			return '○';
		case '1':
			return '一';
		case '2':
			return '二';
		case '3':
			return '三';
		case '4':
			return '四';
		case '5':
			return '五';
		case '6':
			return '六';
		case '7':
			return '七';
		case '8':
			return '八';
		case '9':
			return '九';

		default:
			return num;
		}
	}

	/**
	 * 将数字转为大写中文数字格式
	 * 
	 * @param num
	 *            0至9的整型数值
	 * @return 大写中文字符
	 */
	public final static char cNum2Num_ZH_CASE(char num) {
		switch (num) {
		case '0':
			return '零';
		case '1':
			return '壹';
		case '2':
			return '贰';
		case '3':
			return '叁';
		case '4':
			return '肆';
		case '5':
			return '伍';
		case '6':
			return '陆';
		case '7':
			return '柒';
		case '8':
			return '捌';
		case '9':
			return '玖';

		default:
			return num;
		}
	}

	public final static String num2Num_ZH(String num) {
		char[] nums = num.toCharArray();
		for (int i = 0; i < nums.length; i++) {
			nums[i] = cNum2Num_ZH(nums[i]);
		}
		return new String(nums);
	}

	/**
	 * 将数字日期转换成中文日期 如：
	 * "2006-11-21" 转换之后是 "二○○六年十一月二十一日"
	 * 
	 * @return
	 */
	public final static String numDate2ChineseDate(String ndate) {
		String[] ymd = ndate.split("-");
		String year = num2Num_ZH(ymd[0]);
		String month = num2Num_ZH(ymd[1]);
		String day = num2Num_ZH(ymd[2]);
		
		if("○".equals(month.substring(0,1))){
			month=month.substring(1);
		}else{
			month = "十"+month.substring(1);
		}

		if("○".equals(day.substring(0,1))){
			day=day.substring(1);
		}else if("一".equals(day.substring(0,1))){
			day = "十"+day.substring(1);
		}else{
			day =day.substring(0,1)+ "十"+day.substring(1);
		}

		return year+"年"+month+"月"+day+"日";
	}

	/**
	 * 将一个字符转换为Map对象，要求字符串格式如下：
	 * 	username：用户名,password：密码。
	 * 解析结果是：
	 * 	map.put("username","用户名");
	 * 	map.put("password","密码");
	 * 
	 * @param toSplit 待解析字符串
	 * @param delimiter 分隔符
	 * @return
	 */
	public static Map splitToMap(String toSplit){
		Map map = new TreeMap();
		
		String[] arr = toSplit.split(",");
		for (int i = 0; i < arr.length; i++) {
			String[] a = arr[i].split(":");
			map.put(a[0], a[1]);
		}
		return map;
	}
	
	 /**
	  * 右补位，左对齐
	  * @param oriStr  原字符串
	  * @param len  目标字符串长度
	  * @param paddingChar  填充字符
	  * @return  目标字符串
	  */
	 public static String padRight(String oriStr,int len,char paddingChar){
		 String str="";
		 int strlen = oriStr.length();
		 if(strlen < len){
			 for(int i=0;i<len-strlen;i++){
				 str = str+paddingChar;
				 }
			 }
		 str =  oriStr+str;
		 return str;
	 }
	 
	 /**
	  * 左补位，右对齐
	  * @param oriStr  原字符串
	  * @param len  目标字符串长度
	  * @param paddingChar  填充字符
	  * @return  目标字符串
	  */
	 public static String padLeft(String oriStr,int len,char paddingChar){
		 String str="";
		 int strlen = oriStr.length();
		 if(strlen < len){
			 for(int i=0;i<len-strlen;i++){
				 str = str+paddingChar;
			 }
		 }
		 str = str+oriStr ;
		 return str;
	 }
	 
	/**
	 * 获取系统的根路径
	 * @return
	 */
	public static String getRootPath() {
		ServletRequestAttributes srb = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		
		HttpServletRequest request = srb.getRequest();
		
		String rootPath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
		
		return rootPath;
	}
	
	/**
	 * 获取UUid
	 * @return 序列号
	 */
	public static String newGUID() {
		return UUID.randomUUID().toString();
	}
}
