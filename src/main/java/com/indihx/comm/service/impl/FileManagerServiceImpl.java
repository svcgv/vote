package com.indihx.comm.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.comm.dao.FileUploadMapper;
import com.indihx.comm.entity.FileUpload;
import com.indihx.comm.service.IFileManagerService;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.system.entity.UsrInfo;

/***
 * 
 * <p>
 * 描 述: 文件管理service
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年5月5日
 * </p>
 * 
 * @author 谢追辉
 */
@Service
public class FileManagerServiceImpl implements IFileManagerService {
	private static Logger logger = LoggerFactory.getLogger(InitSysBasicServiceImpl.class);
	
	@Resource
	private FileUploadMapper fileMapper;
	
	@Resource
	private CommUtilMapper commMapper;

	@Override
	public Map<String, Object> uploadFile(MultipartFile myfiles, String path,String relaPath,String relaTable,
			String relaTabId,UsrInfo usrInfo,String fileType,String fileTypeId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (myfiles.isEmpty()) {
			logger.info("-->文件对象不存在");
			return null;
		}
		path = path + "upload";
		relaPath = relaPath + "/upload";
		String oldFileName = myfiles.getOriginalFilename();
		String fileName = DateUtil.getDateTime() + oldFileName;
		Long fileSize = myfiles.getSize();
		String fileSizeStr = new DecimalFormat("#.00").format(fileSize/1024);
		path = path + "/" + fileName;
		relaPath = relaPath + "/" + fileName;
		logger.info("-->文件上传路径："+path);
		File localFile = new File(path);
		try {
			myfiles.transferTo(localFile);
			map.put("status", "success");
			FileUpload fileUpload = new FileUpload();
			fileUpload.setFileAddre(relaPath);
			fileUpload.setFileName(fileName);
			fileUpload.setOldFileName(oldFileName);
			fileUpload.setFileSize(fileSizeStr);
			fileUpload.setFileType(fileType);
			fileUpload.setFileTypeId(fileTypeId);
			//当关联关系表和ID不为空的时候，保存 到数据库
			if (StringUtils.isNotEmpty(relaTable) && StringUtils.isNotEmpty(relaTabId)) {
				fileUpload.setRelaTable(relaTable);
				fileUpload.setRelaTabId(ObjectUtil.toLong(relaTabId));
			}
			fileUpload.setUploadUser(usrInfo==null ?"未知":usrInfo.getUsrName());
			String fileId = saveFileInfo(fileUpload);
			map.put("fileId", fileId);  
			map.put("fileName", oldFileName); 
			map.put("relaPath", relaPath);  //相对路径
			map.put("path", path);   //绝对路径
			map.put("uploadUser", fileUpload.getUploadUser());   //绝对路径
		} catch (IllegalStateException e) {
			logger.error("-->文件上传失败！！！");
			map.put("status", "error");
			e.printStackTrace();
		} catch (IOException e) {
			map.put("status", "error");
			logger.error("-->文件上传失败！！！");
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public String saveFileInfo(FileUpload fileUpload) {
		String fileId = commMapper.getPrimaryKey();
		fileUpload.setFileId(new Long(fileId));
		fileUpload.setUploadDate(DateUtil.getDateTime());
		fileUpload.setFileSts("00");
		int cnt = fileMapper.insertSelective(fileUpload);
		return cnt > 0 ? fileId : "";
	}

	@Override
	public List<FileUpload> queryFiles(FileUpload fileUpload) {
		List<FileUpload> fileUploads = fileMapper.selectByPrimaryKey(fileUpload);
		return fileUploads;
	}

	@Override
	public int deleteFile(String fileId) {
		return fileMapper.deleteByPrimaryKey(ObjectUtil.toLong(fileId));
	}
	

}
