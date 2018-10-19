package com.indihx.activiti.entity;
/***
 * 
 * <p>描 述: 节点信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月5日</p>
 * @author 谢追辉
 */
public class AppNodeInfo {
	
	private String nodeId; //节点ID
	
	private String nodeName; //节点名称
	
	private boolean isMulti; //是否为会签节点
	
	private String nodeType; // 01出去的节点  02 进入的节点
	
	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public boolean isMulti() {
		return isMulti;
	}

	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	
	
}
