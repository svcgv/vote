package com.indihx.comm.dao;

import java.util.List;

import com.indihx.comm.entity.FileTypeCfg;
import com.indihx.comm.entity.FileUpload;
/***
 * 
 * <p>描 述: 文件上传下载类</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年5月5日</p>
 * @author 谢追辉
 */
public interface FileUploadMapper {
	
	/***
	 * 删除文件，根据文件ID
	 * @param fileId 文件ID
	 * @return
	 */
    int deleteByPrimaryKey(Long fileId);
    
    /***
     * 根据实体保存文件信息
     * @param record  文件实体
     * @return
     */
    int insertSelective(FileUpload record);
    
    /***
     * 根据文件ID查询文件信息
     * @param fileId
     * @return
     */
    List<FileUpload> selectByPrimaryKey(FileUpload record);
    
    /***
     * 更新文件信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(FileUpload record);

    /***
     * 根据档案标志/业务类型查找上传的档案配置列表
     * @param fileId
     * @return
     */
    List<FileTypeCfg> getFileDocTypeList(FileTypeCfg cfg);

	/**
	 * @param sign_id
	 * @return
	 */
	Long getFileTypeId(Long rela_tab_id);
}