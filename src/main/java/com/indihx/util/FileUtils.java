package com.indihx.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.RandomUtil;

public class FileUtils {

	public static final String FILE_ROOT_PATH="E://upload/";
	
	
	/**
	 * 判断是否存在
	 * @param file
	 * @return
	 */
	public static boolean ifFileExists(File file) {
		if(file.exists()) {
			return true;
		}
		return false;
	}
	
	public static String writeFile(File files) throws IOException {
		String tempName = generalTempFileName(files);
		String path = generalFilePath();
		File writeFile = new File(path+tempName);
		while(ifFileExists(writeFile)) {
			tempName = generalTempFileName(files);
			writeFile = new File(path+tempName);
		}

		//创建要写文件的输入流
		InputStream inputStream = new FileInputStream(files);

		OutputStream  outputStream = new FileOutputStream(writeFile);

		int bytesWritten = 0;
		int byteCount = 0;

		byte[] bytes = new byte[1024];

		while ((byteCount = inputStream.read(bytes)) != -1)
		{
			outputStream.write(bytes, bytesWritten, byteCount);
			bytesWritten += byteCount;
		}
		inputStream.close();
		outputStream.close();
		return path+tempName;
	}
	
	
	public static String writeFile(MultipartFile file) throws IOException {
		String tempName = generalTempFileName(file);
		String path = generalFilePath();
		File writeFile = new File(path+tempName);
		while(ifFileExists(writeFile)) {
			tempName = generalTempFileName(file);
			writeFile = new File(path+tempName);
		}

		//创建要写文件的输入流
		InputStream inputStream = file.getInputStream();

		getFile(inputStream,path+tempName);
		inputStream.close();
		return path+tempName;
	}
	
	public static void getFile(InputStream is,String fileName) throws IOException{
	    BufferedInputStream in=null;
	    BufferedOutputStream out=null;
	    in=new BufferedInputStream(is);
	    out=new BufferedOutputStream(new FileOutputStream(fileName));
	    int len=-1;
	    byte[] b=new byte[1024];
	    while((len=in.read(b))!=-1){
	        out.write(b,0,len);
	    }
	    in.close();
	    out.close();
	}
	
	
	/**
	 * 根据系统时间创建一个名称为YYYYmmDD的文件夹，
	 */
	public static String generalFilePath() {
		StringBuffer path = new StringBuffer(FILE_ROOT_PATH);
		path.append(DateUtil.getSysDate());
		path.append("/");
		File director = new File(path.toString());
		//若不存在则创建文件夹
		if(!ifFileExists(director)) {
			director.mkdirs();
		}
		return path.toString();
	}
	
	/**
	 * 生成一个随机文件名并校验，若目标目录下有该文件名则重新生成
	 * @param file
	 * @param filePath
	 * @return
	 */
	public static String generalTempFileName(File file) {
		StringBuffer tempName = new StringBuffer(file.getName());
		tempName.append(RandomUtil.generateMixString(4));
		return tempName.toString();
	}
	
	public static String generalTempFileName(MultipartFile file) {
		StringBuffer tempName = new StringBuffer(RandomUtil.generateMixString(4));
		tempName.append(file.getOriginalFilename());
		return tempName.toString();
	}
}
