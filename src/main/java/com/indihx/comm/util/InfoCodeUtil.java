package com.indihx.comm.util;

import java.io.Serializable;




import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.indihx.comm.dao.CommUtilMapper;
import com.indihx.util.StringUtils;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: InfoCodeUtil.java</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年2月8日下午4:05:44</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>InfoCodeUtil.java</p>
 * <p></p>
 */
@Repository
public class InfoCodeUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private  CommUtilMapper commUtilMapper;
	
	/**
	 * 获取指定前缀的定长信息编码
	 * @param prefix
	 * @param length
	 * @return
	 */
	public String getFormatCode(String prefix,int length){
        if (StringUtils.isEmpty(length)){
            length =8;
        }
        String seqStr = getSequenceByName(length);
        return new StringBuilder(prefix).append(seqStr).toString();
	}
	
	/**
     * @param length 需要的长度，格式例如：：8位不够的补0
     * @return String 格式化后的sequence值
     */
    public String getSequenceByName(int length){
    	String seq = commUtilMapper.getInfoCodeSequenceVal();
        if (seq != null){
            return String.format("%0" + length + "d", Long.valueOf(seq));
        }else{
            return "001";
        }
    }    
}
