/**
 * 
 */
package com.indihx.activiti.entity;

import java.io.Serializable;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: ActNodeCfg.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月24日下午12:40:27</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>ActNodeCfg.java</p>
 * <p></p>
 */
public class ActNodeCfg implements Serializable{

	private Long id;

    private String nodeId;

    private Long orgId;

    private String orgType;
    
    private String appType; //流程类型

    private Long appId; //流程ID
	
    private String actId;  //工作流ID
}
