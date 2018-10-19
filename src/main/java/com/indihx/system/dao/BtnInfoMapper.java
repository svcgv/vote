package com.indihx.system.dao;
import java.util.List;

/***
 * 
 * <p>描 述: 按钮信息mapper</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年7月25日</p>
 * @author 严蒙蒙
 */
import com.indihx.system.entity.BtnInfo;

public interface BtnInfoMapper {
	/***
	 * 删除按钮信息
	 * @param btnId 主键
	 * @return int>0 success
	 */
    int deleteByPrimaryKey(String btnId);
    /***
	 * 新增按钮信息
	 * @param record 按钮实体
	 * @return int>0 success
	 */
    int insertSelective(BtnInfo record);
    /***
	 * 查询按钮信息
	 * @param btnId 主键
	 * @return 按键实体
	 */
    BtnInfo selectByPrimaryKey(String btnId);
    /***
   	 * 更新按钮信息
   	 * @param record 按钮实体
   	 * @return int>0 success
   	 */
    int updateByPrimaryKeySelective(BtnInfo record);
    /***
   	 * 查询所有按钮信息
   	 * @param record 按钮实体
   	 * @return int>0 success
   	 */
    List<BtnInfo>  qryBtnInfoAll(BtnInfo record);
    /***
   	 * 查询所有按钮信息(角色ID不为空)
   	 * @param record 按钮实体
   	 * @return int>0 success
   	 */
    List<BtnInfo>  qryRoleBtnAll(BtnInfo record);
    
}