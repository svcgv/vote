package com.indihx.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.indihx.activiti.ActivitiBusiness;
import com.indihx.activiti.ActivitiService;
import com.indihx.activiti.dao.ApplicationMapper;
import com.indihx.activiti.entity.Application;
import com.indihx.comm.util.ObjectUtil;
import com.indihx.comm.util.PrimaryKey;
import com.indihx.system.dao.leave.VolCopyMapper;
import com.indihx.system.dao.leave.VolHisMapper;
import com.indihx.system.dao.leave.VolMapper;
import com.indihx.system.entity.UsrInfo;
import com.indihx.system.entity.leave.Vol;
import com.indihx.system.entity.leave.VolCopy;
import com.indihx.system.entity.leave.VolHis;
import com.indihx.system.service.ILeaveInfoService;
import com.indihx.system.vo.LeaveInfoVo;
import com.indihx.util.ConstantStatic;
import com.indihx.util.EntityVoConverter;

/***
 * 
 * <p>
 * 描 述: 请假流程管理
 * </p>
 * <p>
 * 公 司: 上海泓智信息科技有限公司
 * </p>
 * <p>
 * 创建时间: 2017年3月29日
 * </p>
 * 
 * @author 谢追辉
 */
@Service
public class LeaveInfoServiceImpl extends ActivitiBusiness implements ILeaveInfoService {
	// private static Logger log =
	// LoggerFactory.getLogger(LeaveInfoServiceImpl.class);

	@Resource
	private VolCopyMapper volCopyMapper; // 副表
	@Resource
	private VolHisMapper volHisMapper; // 历史表
	@Resource
	private VolMapper volMapper; // 正表
	@Resource
	private ApplicationMapper appMapper;
	@Resource
	private PrimaryKey primaryKey;
	@Resource
	private ActivitiService activitiService; // 工作流服务

	@Override
	public VolCopy addLeave(LeaveInfoVo infoVo, UsrInfo usrInfo) {
		// 信息保存到副表
		VolCopy volCopy = new VolCopy();
		EntityVoConverter.Convert(infoVo, volCopy); // 把vo转换成entity
		volCopy.setVoaUserId(usrInfo.getUsrIdStr());
		String volId = primaryKey.getPrimaryKey();
		volCopy.setVoaId(ObjectUtil.toLong(volId)); // 副表主键
		volCopyMapper.insertSelective(volCopy);
		return volCopy;
	}

	@Override
	public Map<String, Object> qryWait(UsrInfo usrInfo, LeaveInfoVo vo) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageHelper.startPage(vo.getPages(), vo.getRows(), true);
		List<Vol> listVol = volMapper.qryAllVol();
		PageInfo<Vol> pageInfo = new PageInfo<Vol>(listVol);
		map.put("listVol", listVol);
		map.put("pageInfo", pageInfo);
		return map;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public Application startVol(UsrInfo usrInfo, LeaveInfoVo leaveInfoVo) {
		String appType = "LEAVE_HUIQIAN";
		//String appType = "LEAVE_PROCESS";
		// 启动当前流程
		Application app = activitiService.startProcess(usrInfo, null, leaveInfoVo.getVolId(), appType,
				usrInfo.getUsrIdStr());
		VolCopy copy = new VolCopy();
		copy.setAppId(app.getAppId());
		copy.setVoaId(ObjectUtil.toLong(leaveInfoVo.getVolId()));
		int num = volCopyMapper.updateByPrimaryKeySelective(copy);
		if (num < 1) {
			throw new RuntimeException("流程发起成功，业务数据绑定失败！");
		}
		saveVolHis(copy, app); // 往历史表中插入数据
		return app;
	}

	@Override
	public boolean rejectProcess(UsrInfo usrInfo, LeaveInfoVo leaveInfoVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commitVol(UsrInfo usrInfo, LeaveInfoVo leaveInfoVo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public VolCopy selectByLeaveCopy(Map<String, Object> reqMap) {
		return volCopyMapper.selectByPrimaryKey(reqMap);
	}

	@Override
	public void handCopy(Application app) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("appId", app.getAppId());
		VolCopy copy = volCopyMapper.selectByPrimaryKey(reqMap); // 查询副表的记录
		saveVolHis(copy, app); // 往历史表中插入数据
	}

	/***
	 * 根据副表往历史表中插入数据
	 * 
	 * @param copy
	 */
	public void saveVolHis(VolCopy copy, Application app) {
		// 往历史表中插入数据
		VolHis his = new VolHis();
		EntityVoConverter.Convert(copy, his); // 把copy表已有的字段转换到历史表
		his.setHisId(primaryKey.getPrimaryKeyLong());
		his.setTaskId(app.getCurrNodeId());
		int num = volHisMapper.insertSelective(his); // 往历史表中插入数据
		if (num < 1) {
			throw new RuntimeException("历史表数据移出错出错！！");
		}
	}

	@Override
	public void processEnd(Application app) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("appId", app.getAppId());
		VolCopy copy = volCopyMapper.selectByPrimaryKey(reqMap); // 查询副表的记录
		saveVolHis(copy, app); // 往历史表中插入数据
		// 往正表中插入数据
		Vol vol = new Vol();
		EntityVoConverter.Convert(copy, vol); // 把copy表已有的字段转换到历史表
		int num = volMapper.insertSelective(vol);
		if (num < 1) {
			throw new RuntimeException("正表迁移出错出错！！");
		}
		// 清空副表中的数据
		int cnt = volCopyMapper.deleteByPrimaryKey(copy.getVoaId()); // 清空历史表的记录
		if (cnt < 1) {
			throw new RuntimeException("副表数据清理错误！！");
		}
	}

	@Override
	public void rejectProcess(Application app) {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("appId", app.getAppId());
		VolCopy copy = volCopyMapper.selectByPrimaryKey(reqMap); // 查询副表的记录
		saveVolHis(copy, app); // 往历史表中插入数据
	}

	@Override
	public Map<String, Object> queryToDoInfo(Application app) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", app.getAppId());
		VolCopy volCopy = selectByLeaveCopy(map);
		map.put("vol", volCopy);
		map.put(ConstantStatic.URL, "/leave/handlLeaveProcess");
		return map;
	}



	@Override
	public Map<String, Object> handCommitFirst(Application app, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> handCommitFirst(Application app) {
		// TODO Auto-generated method stub
		return null;
	}

}
