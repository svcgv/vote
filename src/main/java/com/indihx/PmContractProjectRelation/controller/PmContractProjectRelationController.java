package com.indihx.PmContractProjectRelation.controller;


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
import com.indihx.PmContractProjectRelation.entity.PmContractProjectRelationEntity;
import com.indihx.PmContractProjectRelation.service.PmContractProjectRelationService;
import com.indihx.comm.util.R;
import com.indihx.comm.util.DateUtil;
import com.indihx.comm.util.PageUtils;
import com.indihx.comm.InitSysConstants;

/**
 * ${comments}
 *
 * @author hb
 * @date 2018-11-17 11:28:34
 */
@Controller
@RequestMapping("/pmcontractprojectrelation")
public class PmContractProjectRelationController {
    @Autowired
    private PmContractProjectRelationService pmContractProjectRelationService;

    /**
     * 列表
     */
    @RequestMapping(value="/list",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> list(@RequestBody Map<String, Object> params,HttpSession session){

		List<PmContractProjectRelationEntity> pmContractProjectRelation = pmContractProjectRelationService.queryList(params);
        return R.ok().put("page", pmContractProjectRelation);
    }

    /**
     * 列表
     */
    @RequestMapping(value="/listProject",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> listProject(@RequestBody Map<String, Object> params,HttpSession session){

        List<PmContractProjectRelationEntity> pmContractProjectRelation = pmContractProjectRelationService.queryListProject(params);
        return R.ok().put("page", pmContractProjectRelation);
    }


    /**
     * 信息
     */
    @RequestMapping(value="/info",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> info(@RequestParam("contractProjectRelationId") long contractProjectRelationId,HttpSession session){

		PmContractProjectRelationEntity entity = pmContractProjectRelationService.queryObject(contractProjectRelationId);
        return R.ok().put("pmContractProjectRelation", entity);
    }

    /**
     * 保存
     */
    @RequestMapping(value="/save",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> save(@RequestBody PmContractProjectRelationEntity pmContractProjectRelation,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmContractProjectRelation.setCreatorId(usesr.getUsrId());
    	pmContractProjectRelation.setCreateTime(DateUtil.getDateTime());
        pmContractProjectRelationService.insert(pmContractProjectRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> update(@RequestBody PmContractProjectRelationEntity pmContractProjectRelation,HttpSession session){
    	UsrInfo usesr = UserUtil.getUser(session);
    	pmContractProjectRelation.setModifier(usesr.getUsrId());
    	pmContractProjectRelation.setModifyTime(DateUtil.getDateTime());
        pmContractProjectRelationService.update(pmContractProjectRelation);//全部更新
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    public @ResponseBody Map<String,Object> delete(@RequestBody long contractProjectRelationId,HttpSession session){
        pmContractProjectRelationService.delete(contractProjectRelationId);
        return R.ok();
    }

}
