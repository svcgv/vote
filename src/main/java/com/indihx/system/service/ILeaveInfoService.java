package com.indihx.system.service;

import java.util.Map;

import com.indihx.activiti.entity.Application;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.entity.leave.VolCopy;
import com.indihx.system.vo.LeaveInfoVo;

/***
 * 
 * <p>描 述: 请假流程管理</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月29日</p>
 * @author 谢追辉
 */
public interface ILeaveInfoService {
	
	/***
	 * 保存请假信息
	 * @return
	 */
	VolCopy addLeave(LeaveInfoVo infoVo,UsrInfo usrInfo);
	
	/***
	 * 查询代办
	 * @param usrInfo
	 * @return
	 */
	Map<String, Object> qryWait(UsrInfo usrInfo,LeaveInfoVo infoVo);
	
	/***
	 * 启动流程
	 * @param usrInfo
	 * @param leaveInfoVo
	 * @return
	 */
	Application startVol(UsrInfo usrInfo,LeaveInfoVo leaveInfoVo);
	
	/***
	 * 提交流程
	 * @param usrInfo
	 * @param leaveInfoVo
	 * @return
	 */
	boolean commitVol(UsrInfo usrInfo,LeaveInfoVo leaveInfoVo);
	/***
	 * 审核
	 * @param usrInfo
	 * @param leaveInfoVo
	 * @return
	 */
	boolean rejectProcess(UsrInfo usrInfo,LeaveInfoVo leaveInfoVo);
	
	
	/***
     * 根据查询条件查询副表的记录
     * @param reqMap
     * @return
     */
    VolCopy selectByLeaveCopy(Map<String, Object> reqMap);

	Map<String, Object> handCommitFirst(Application app);
}
