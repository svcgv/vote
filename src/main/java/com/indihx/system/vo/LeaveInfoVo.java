package com.indihx.system.vo;
/***
 * 
 * <p>描 述: 请假信息Vo</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月29日</p>
 * @author 谢追辉
 */
public class LeaveInfoVo extends BaseVo {
	
	private String voaDesc; //请假理由

    private String voaDate;  //请假时间
    
    private String volId; //请假ID
    
	public String getVolId() {
		return volId;
	}

	public void setVolId(String volId) {
		this.volId = volId;
	}

	public String getVoaDesc() {
		return voaDesc;
	}

	public void setVoaDesc(String voaDesc) {
		this.voaDesc = voaDesc;
	}

	public String getVoaDate() {
		return voaDate;
	}

	public void setVoaDate(String voaDate) {
		this.voaDate = voaDate;
	}
	
	
}
