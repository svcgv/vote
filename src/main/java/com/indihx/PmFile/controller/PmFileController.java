package com.indihx.PmFile.controller;


import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.FileUtils;
import com.indihx.util.UserUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.indihx.PmFile.entity.PmFileEntity;
import com.indihx.PmFile.service.PmFileService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-06 19:33:25
 */
@Controller
@RequestMapping("/pmfile")
public class PmFileController {
    @Autowired
    private PmFileService pmFileService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmFileEntity> pmFile = pmFileService.queryList(params);
        return R.ok().put("page", pmFile);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("fileId") long fileId,HttpSession session){

		PmFileEntity entity = pmFileService.queryObject(fileId);
        return R.ok().put("pmFile", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmFileEntity pmFile,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmFile.setCreatorId(usesr.getUsrId());
    	pmFile.setCreateTime(DateUtil.getDateTime());
        pmFileService.insert(pmFile);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmFileEntity pmFile,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmFile.setModifier(usesr.getUsrId());
    	pmFile.setModifyTime(DateUtil.getDateTime());
        pmFileService.update(pmFile);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long fileId,HttpSession session){
        pmFileService.delete(fileId);
        return R.ok();
    }
    
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> pmFileUpload(@RequestParam("file") MultipartFile[] myfiles,@RequestParam("uploadType") String uploadType,@RequestParam("fileTypes") String fileTypes, HttpSession session) throws IOException{
    	UsrInfo usesr = UserUtil.getUser(session);
    	if(fileTypes==null||"".equals(fileTypes)) {
    		return R.error("请上传文件描述");
    	}
    	List<Long> idList = new ArrayList<Long>();
    	PmFileEntity pmFile=new PmFileEntity();
    	pmFile.setCreatorId(usesr.getUsrId());
    	pmFile.setCreateTime(DateUtil.getDateTime());
    	pmFile.setUploadType(uploadType);
    	for(int i=0;i<myfiles.length;i++) {
    		pmFile.setFileSize(myfiles[i].getSize());
    		List<Object> list = JSONObject.parseArray(fileTypes);
    		
    		pmFile.setFilePath(FileUtils.writeFile(myfiles[i]));;
    		pmFile.setFileUploadName(myfiles[i].getOriginalFilename());
    		for(int o = 0;o<list.size();o++) {
    			Map<String,Object> map = (Map<String, Object>) list.get(o);
    			if(pmFile.getFileUploadName().equals(map.get("fileName"))) {
    				pmFile.setFileBusinessType((String)map.get("fileType"));
    			}
    		}
    		pmFileService.insert(pmFile);
    		long id = pmFile.getFileId();
    		
    		
    		idList.add(id);
    	}
    	
    	return R.ok().put("fileIds", idList);
	}

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public @ResponseBody void download(@RequestParam long id,HttpServletRequest request, HttpServletResponse response) throws IOException{
    	PmFileEntity pmFile= pmFileService.queryObject(id);
    	System.out.println(pmFile.getFilePath());
    	String name = pmFile.getFileUploadName();
		//获取文件的绝对路径
		String path = pmFile.getFilePath();
		//设置文件的MIME类型
		//response.setContentType(getServletContext().getMimeType(name));
		//设置响应头文件，标识为文件下载类型，并附上文件的名称
		response.setHeader("Content-Disposition", "attachment;filename="+name);
	
		//对文件进行读取和存贮
		InputStream is = new FileInputStream(path);
		OutputStream os = response.getOutputStream();
		byte[] flush = new byte[1024];
		int len = 0;
		while((len = is.read(flush))!=-1){
			os.write(flush,0,len);
			os.flush();
		}
		is.close();
    }
}
