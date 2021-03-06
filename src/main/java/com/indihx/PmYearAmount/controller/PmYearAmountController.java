package com.indihx.PmYearAmount.controller;


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
import com.indihx.PmYearAmount.entity.PmYearAmountEntity;
import com.indihx.PmYearAmount.service.PmYearAmountService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-26 17:00:52
 */
@Controller
@RequestMapping("/pmyearamount")
public class PmYearAmountController {
    @Autowired
    private PmYearAmountService pmYearAmountService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmYearAmountEntity> pmYearAmount = pmYearAmountService.queryList(params);
        return R.ok().put("page", pmYearAmount);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("yearAmountId") long yearAmountId,HttpSession session){

		PmYearAmountEntity entity = pmYearAmountService.queryObject(yearAmountId);
        return R.ok().put("pmYearAmount", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmYearAmountEntity pmYearAmount,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearAmount.setCreatorId(usesr.getUsrId());
    	pmYearAmount.setCreateTime(DateUtil.getDateTime());
        pmYearAmountService.insert(pmYearAmount);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmYearAmountEntity pmYearAmount,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmYearAmount.setModifier(usesr.getUsrId());
    	pmYearAmount.setModifyTime(DateUtil.getDateTime());
        pmYearAmountService.update(pmYearAmount);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long yearAmountId,HttpSession session){
        pmYearAmountService.delete(yearAmountId);
        return R.ok();
    }

}
