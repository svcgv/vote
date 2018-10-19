package com.indihx.activiti.dao;

import java.util.List;

import com.indihx.activiti.entity.ApplicationIeda;
/***
 * 
 * <p>描 述: 流程意见</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月4日</p>
 * @author 谢追辉
 */
public interface ApplicationIedaMapper {
    int deleteByPrimaryKey(Long ideaId);

    int insert(ApplicationIeda record);

    int insertSelective(ApplicationIeda record);

    ApplicationIeda selectByPrimaryKey(Long ideaId);

    int updateByPrimaryKeySelective(ApplicationIeda record);

    int updateByPrimaryKey(ApplicationIeda record);
    
    /***
     * 查询流程跟踪的信息
     * @param appId 流程ID
     * @return 集合
     */
    List<ApplicationIeda>  selectProcessTrack(Long appId);
    
}