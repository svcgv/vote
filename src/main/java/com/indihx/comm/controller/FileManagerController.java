package com.indihx.comm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.indihx.AbstractBaseController;
import com.indihx.comm.entity.FileUpload;
import com.indihx.comm.service.impl.FileManagerServiceImpl;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.system.entity.UsrInfo;

/***
 * 
 * <p>
 * 描 述: 文件上传下载管理
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
@Controller
@RequestMapping("/file")
public class FileManagerController extends AbstractBaseController {

	@Resource
	private FileManagerServiceImpl fileService;

	/***
	 * 
	 * @param myfiles
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fileUpload.upload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> upload(@RequestParam("file") MultipartFile myfiles,
			HttpServletRequest request,HttpSession session) {
		UsrInfo usrInfo = getUser(session);
		String path = request.getSession().getServletContext().getRealPath("/");
		String relaPath = request.getSession().getServletContext().getContextPath();
		Map<String, Object> map = fileService.uploadFile(myfiles, path, relaPath,null,null,usrInfo,null,null);
		return map;
	}

	/***
	 * 多文件上传
	 * 
	 * @param myfiles
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/fileUploadMulti.upload", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> uploadMulti(@RequestParam("file") MultipartFile[] myfiles,
			HttpServletRequest request, @RequestParam("relaTable") String relaTable,
			@RequestParam("relaTabId") String relaTabId,@RequestParam("fileType") String fileType,@RequestParam("fileTypeId") String fileTypeId,HttpSession session) {
		UsrInfo usrInfo = getUser(session);
		List<Map<String, Object>> list = new ArrayList<>();
		String path = request.getSession().getServletContext().getRealPath("/");
		String relaPath = request.getSession().getServletContext().getContextPath();
		for (int i = 0; i < myfiles.length; i++) {
			Map<String, Object> map = fileService.uploadFile(myfiles[i], path, relaPath,relaTable,relaTabId,usrInfo,fileType,fileTypeId);
			list.add(map);
		}
		return list;
	}

	/***
	 * 打开文件上传页面
	 * 
	 * @param relaTable
	 *            表名称
	 * @param relaTabId
	 *            主键ID
	 * @return 文件上传页面
	 */
	@RequestMapping(value = "/uploadFileView.do")
	public ModelAndView uploadFileView(String relaTable, String relaTabId,String relaTypeId) {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setRelaTable(relaTable);
		fileUpload.setRelaTabId(ObjectUtil.toLong(relaTabId));
		List<FileUpload> fileUploads=fileService.queryFiles(fileUpload);
		Map<String, Object> map = new HashMap<>();
		map.put("relaTable", relaTable);
		map.put("relaTabId", relaTabId);
		map.put("relaTypeId", relaTypeId);
		map.put("fileUploads", fileUploads);
		return returnModel(map, "/comm/queryFileMng");
	}
	
	
	/***
	 * 打开文件上传页面
	 * 
	 * @param relaTable
	 *            表名称
	 * @param relaTabId
	 *            主键ID
	 * @return 文件上传页面
	 */
	@RequestMapping(value = "/seeFileView.do")
	public ModelAndView seeFileView(String relaTable, String relaTabId,String relaTypeId) {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setRelaTable(relaTable);
		fileUpload.setRelaTabId(ObjectUtil.toLong(relaTabId));
		List<FileUpload> fileUploads=fileService.queryFiles(fileUpload);
		Map<String, Object> map = new HashMap<>();
		map.put("relaTable", relaTable);
		map.put("relaTabId", relaTabId);
		map.put("relaTypeId", relaTypeId);
		map.put("fileUploads", fileUploads);
		return returnModel(map, "/comm/querFileInfo");
	}
	
	/***
	 * 删除文件
	 * @param fileId 文件ID
	 * @return
	 */
	@RequestMapping(value = "/delFiles.do")
	public @ResponseBody int delFiles(@RequestBody Map<String, Object> reqMap){
		String fileId = ObjectUtil.toString(reqMap.get("fileId"));
		if (StringUtils.isEmpty(fileId)) {
			return 0;
		}
		return fileService.deleteFile(fileId);
	}

}
