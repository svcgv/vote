package com.indihx.baseTableUtil.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import com.indihx.baseTableUtil.dao.QueryOrgInfoMapper;
import com.indihx.baseTableUtil.entity.QueryOrgInfoEntity;
import com.indihx.baseTableUtil.service.QueryOrgInfoService;

@Service("QueryOrgInfoServiceImpl")
public class QueryOrgInfoServiceImpl implements QueryOrgInfoService {
	@Resource
	QueryOrgInfoMapper queryorgInfoMapper;

	public QueryOrgInfoEntity queryObject(BigDecimal id) {
		return queryorgInfoMapper.queryObject(id);
	}

	public int queryTotal() {
		return queryorgInfoMapper.queryTotal();
	}

	public List<QueryOrgInfoEntity> queryList(Map<String, Object> entity) {
		if (entity.get("isDelete") == null || "".equals(entity.get("isDelete"))) {
			entity.put("isDelete", "00");
		}
		return queryorgInfoMapper.queryList(entity);
	}

	public List<Map<String, Object>> queryChildrenOrg(BigDecimal orgNo) {
		return queryorgInfoMapper.queryChildrenOrg(orgNo);
	}

	/**
	 * 获取完整机构树
	 * 
	 * @param orgNo
	 * @return
	 */
	public Map<String, Object> getFullChildrenTree(BigDecimal orgNo) {
		QueryOrgInfoEntity topOrg = queryObject(orgNo);
		Map<String, Object> top = new HashMap<>();
		top.put("name", topOrg.getOrgName());
		top.put("orgId", topOrg.getOrgNo());

		forEachChildren(top);
		
		return top;
	}

	/**
	 * 传入当前节点，递归子节点
	 * @param parent
	 * @return
	 */
	public void forEachChildren(Map<String,Object> parent){
		List<Map<String,Object>> children = queryChildrenOrg(new BigDecimal( Integer.parseInt(parent.get("orgId").toString())));
		if(assertNull(children)) {
			parent.put("children", children);
			for(int i = 0;i<children.size();i++) {
				forEachChildren(children.get(i));
			}
		}
		
	}

	/**
	 * 判断子节点列表是否存在
	 * @param children
	 * @return
	 */
	public boolean assertNull(List<Map<String, Object>> children) {
		if(children!=null&&!children.isEmpty()) {
			return true;
		}
		return false;
	}
	
}
