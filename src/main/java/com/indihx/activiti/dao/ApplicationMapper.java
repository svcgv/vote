package com.indihx.activiti.dao;

import java.util.List;
import java.util.Map;

import com.indihx.activiti.entity.Application;
import com.indihx.activiti.vo.ApplicationVo;
/***
 * 
 * <p>描 述: 流程总表信息</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月4日</p>
 * @author 谢追辉
 */
public interface ApplicationMapper {
    int deleteByPrimaryKey(Long appId);

    /***
     * 保存流程信息
     * @param record 实体
     * @return
     */
    int insertSelective(Application record);
    
    /***
     * 根据流程ID查询当前流程信息
     * @param appId 流程ID
     * @return 流程信息
     */
    Application selectByPrimaryKey(Long appId);
    
    /***
     * 更新流程总表中的数据
     * @param record 流程对象
     * @return 成功 or 失败
     */
    int updateByPrimaryKeySelective(Application record);

    
    /**
     * 查询代办的流程信息
     * @param app 查询条件
     * @return 集合
     */
    List<Application> queryToDo(ApplicationVo vo);
    
    /***
     * 查询当前用户已办理的任务
     * @param vo 条件
     * @return 集合
     */
    List<Application> queryCompToDo(ApplicationVo vo);
    
    /***
     * 根据流程和节点，查询处理当前节点的用户信息，按时间倒序
     * @param actId  流程ID
     * @param nodeId 节点ID
     * @return 用户信息
     */
    List<Map<String, Object>> queryUsrInfoByFlowNode(String actId,String nodeId);
}