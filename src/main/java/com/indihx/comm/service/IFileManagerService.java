package com.indihx.comm.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.indihx.comm.entity.FileUpload;
import com.indihx.system.entity.UsrInfo;

public interface IFileManagerService {

	/**
	 * 文件上传功能
	 * 
	 * @param myfiles
	 *            文件元素
	 * @param path
	 *            项目的绝对路径
	 * @param relaPath
	 *            相对路径
	 * @param relaTable
	 *            对应表
	 * @param relaTabId
	 *            对应ID
	 * @return
	 */
	Map<String, Object> uploadFile(MultipartFile myfiles, String path, String relaPath, String relaTable,
			String relaTabId, UsrInfo usrInfo, String fileKind,String fileTypeId);

	/***
	 * 保存文件信息
	 * 
	 * @param fileUpload
	 *            文件实体
	 * @return 文件ID
	 */
	String saveFileInfo(FileUpload fileUpload);

	/***
	 * 查询附件信息
	 * 
	 * @param fileUpload
	 * @return
	 */
	List<FileUpload> queryFiles(FileUpload fileUpload);

	/***
	 * 根据文件ID删除文件
	 * 
	 * @param fileId
	 * @return
	 */
	int deleteFile(String fileId);
}
