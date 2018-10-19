package com.indihx.activiti;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.FixedValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.indihx.comm.util.ObjectUtil;
import com.indihx.system.dao.UsrInfoMapper;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.SpringContextBean;
/***
 * 
 * <p>描 述: 工作流监听，把处理人指定到角色上，所有需要到角色的节点，
 * 		        全部需要配置此监听，并设置角色ID【roleId】的值</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年3月30日</p>
 * @author 谢追辉
 */
@Repository("taskListenerImpl")
public class TaskListenerImpl implements TaskListener {
	private static final long serialVersionUID = 4620075482557717485L;
	private static Logger log = LoggerFactory.getLogger(TaskListenerImpl.class); 
	private  UsrInfoMapper usrInfoMapper;  
	private FixedValue  roleId;  //角色ID
	
	public FixedValue getRoleId() {
		return roleId;
	}

	public void setRoleId(FixedValue roleId) {
		this.roleId = roleId;
	}
	
	/***
	 * 分配任务到角色上
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		//得到当前节点角色
		String _roleId = ObjectUtil.toString(roleId.getValue(delegateTask));
		log.info("分配的角色ID--->"+_roleId);
		try {
			usrInfoMapper =  (UsrInfoMapper)SpringContextBean.getBean("usrInfoMapper");
			List<UsrInfo> usrList = usrInfoMapper.qryUsrInfoByRoleId(_roleId);
			List<String> str = new ArrayList<String>();
			for (UsrInfo usrInfo : usrList) {
				str.add(usrInfo.getUsrId().toString());
			}
			log.info("任务的用户ID--->"+str.toString());
			delegateTask.addCandidateGroup(_roleId); //把流程分配到角色
			//delegateTask.addCandidateUsers(str);  //指定处理人
			Map<String, Object> map= delegateTask.getVariables();
			log.info("--------->"+map.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
