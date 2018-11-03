package com.indihx.PmPaymentPoint.controller;


import java.util.Map;

import javax.servlet.http.HttpSession;

import java.util.List;
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
import com.indihx.PmPaymentPoint.entity.PmPaymentPointEntity;
import com.indihx.PmPaymentPoint.service.PmPaymentPointService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * 
 *
 * @author hb
 * @date 2018-11-03 17:11:55
 */
@Controller
@RequestMapping("/pmpaymentpoint")
public class PmPaymentPointController {
    @Autowired
    private PmPaymentPointService pmPaymentPointService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmPaymentPointEntity> pmPaymentPoint = pmPaymentPointService.queryList(params);
        return R.ok().put("page", pmPaymentPoint);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("paymentId") long paymentId,HttpSession session){

		PmPaymentPointEntity entity = pmPaymentPointService.queryObject(paymentId);
        return R.ok().put("pmPaymentPoint", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmPaymentPointEntity pmPaymentPoint,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmPaymentPoint.setCreatorId(usesr.getUsrId());
    	pmPaymentPoint.setCreateTime(DateUtil.getDateTime());
        pmPaymentPointService.insert(pmPaymentPoint);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmPaymentPointEntity pmPaymentPoint,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmPaymentPoint.setModifier(usesr.getUsrId());
    	pmPaymentPoint.setModifyTime(DateUtil.getDateTime());
        pmPaymentPointService.update(pmPaymentPoint);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long paymentId,HttpSession session){
        pmPaymentPointService.delete(paymentId);
        return R.ok();
    }

}
