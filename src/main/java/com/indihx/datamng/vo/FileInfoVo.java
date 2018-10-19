package com.indihx.datamng.vo;

import com.indihx.system.vo.BaseVo;

/***
 * 
 * <p>
 * 描 述: 已上传文件信息
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
public class FileInfoVo extends BaseVo {
	private String fileAddre; // 文件地址
	private String fileId; // 文件ID

	public String getFileAddre() {
		return fileAddre;
	}

	public void setFileAddre(String fileAddre) {
		this.fileAddre = fileAddre;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
