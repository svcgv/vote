package com.indihx.PmYearBudget.controller;


import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import com.indihx.system.entity.UsrInfo;
import com.indihx.util.BeanUtils;
import com.indihx.util.UserUtil;
import com.indihx.comm.util.R;
import com.indihx.comm.util.RandomUtil;
import com.indihx.comm.util.DateUtil;
import com.indihx.PmYearBudget.entity.PmYearBudgetEntity;
import com.indihx.PmYearBudget.service.PmYearBudgetService;
import com.indihx.comm.InitSysConstants;
/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-18 10:35:36
 */
@Controller
@RequestMapping("/pmyearbudget")
public class PmYearBudgetController {
    @Autowired
    private PmYearBudgetService pmYearBudgetService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmYearBudgetEntity> pmYearBudget = pmYearBudgetService.queryList(params);
        return R.ok().put("page", pmYearBudget);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("yearBudgetCode") String yearBudgetCode,HttpSession session){

		PmYearBudgetEntity entity = pmYearBudgetService.queryObject(yearBudgetCode);
        return R.ok().put("pmYearBudget", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmYearBudgetEntity pmYearBudget,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearBudget.setCreatorId(usesr.getUsrId());
    	pmYearBudget.setCreateTime(DateUtil.getDateTime());
    	pmYearBudget.setYearBudgetCode(RandomUtil.getCodeByType("YS"));
        pmYearBudgetService.insert(pmYearBudget);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmYearBudgetEntity pmYearBudget,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearBudget.setModifier(usesr.getUsrId());
    	pmYearBudget.setModifyTime(DateUtil.getDateTime());
        pmYearBudgetService.update(pmYearBudget);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody String yearBudgetCode,HttpSession session){
        pmYearBudgetService.delete(yearBudgetCode);
        return R.ok();
    }

    /**
     * 保存多个
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    @RequestMapping(value="/saveList",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> saveList(@RequestBody Map<String,Object> pmYearBudget,HttpSession session) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException{
    	UsrInfo usesr = UserUtil.getUser(session);
    	List<Map<String,Object>> listMap = (List<Map<String, Object>>) pmYearBudget.get("budgetList");
    	List<PmYearBudgetEntity> listBean = BeanUtils.MapList2BeanList(listMap, PmYearBudgetEntity.class);
        pmYearBudgetService.insertList(listBean);
        return R.ok();
    }
}
