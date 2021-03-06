package com.indihx.PmYearBudgetProduct.controller;


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
import com.indihx.util.UserUtil;
import com.indihx.PmYearBudgetProduct.entity.PmYearBudgetProductEntity;
import com.indihx.PmYearBudgetProduct.service.PmYearBudgetProductService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-18 10:35:35
 */
@Controller
@RequestMapping("/pmyearbudgetproduct")
public class PmYearBudgetProductController {
    @Autowired
    private PmYearBudgetProductService pmYearBudgetProductService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmYearBudgetProductEntity> pmYearBudgetProduct = pmYearBudgetProductService.queryList(params);
        return R.ok().put("page", pmYearBudgetProduct);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("paymentId") long paymentId,HttpSession session){

		PmYearBudgetProductEntity entity = pmYearBudgetProductService.queryObject(paymentId);
        return R.ok().put("pmYearBudgetProduct", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmYearBudgetProductEntity pmYearBudgetProduct,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearBudgetProduct.setCreatorId(usesr.getUsrId());
    	pmYearBudgetProduct.setCreateTime(DateUtil.getDateTime());
        pmYearBudgetProductService.insert(pmYearBudgetProduct);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmYearBudgetProductEntity pmYearBudgetProduct,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearBudgetProduct.setModifier(usesr.getUsrId());
    	pmYearBudgetProduct.setModifyTime(DateUtil.getDateTime());
        pmYearBudgetProductService.update(pmYearBudgetProduct);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long paymentId,HttpSession session){
        pmYearBudgetProductService.delete(paymentId);
        return R.ok();
    }

}
