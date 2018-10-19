package com.indihx.comm.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.indihx.comm.dao.CommUtilMapper;

/**
 * 
 * <p>描 述: 得到主键ID</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年5月25日</p>
 * @author 谢追辉
 */
@Repository
public class PrimaryKey {
	
	@Resource
	private  CommUtilMapper commUtilMapper;
	
	
	/***
	 * 得到序列的值,返回一个String类型的序列
	 * @return Stirng类型
	 */
	public  String getPrimaryKey(){
		return commUtilMapper.getPrimaryKey();
	}
	
	/***
	 * 得到序列的值，返回一个long类型的值
	 * @return Long类型
	 */
	public  Long getPrimaryKeyLong(){
		String key = getPrimaryKey();
		return new Long(key);
	}
	 
	/***
	 * 时间和序列组装成一个主键
	 * @return  String
	 */
	public  String getPrimaryKeyAddDate(){
		String key = getPrimaryKey();
		key = DateUtil.getDateTime()+key;
		return key;
	}
	
	/***
	 * 时间和序列组装成一个主键
	 * @return  Long
	 */
	public  Long getPrimaryKeyAddDateLong(){
		String key = getPrimaryKey();
		key = DateUtil.getSysDate()+key;
		return new Long(key);
	}
	
	/***
	 * 获取一个定长的以日期为前缀的主键
	 * @return  Long
	 */
	public  Long getPrefixDatePrimaryKey(int length){
		String seq = String.format("%0" + length + "d", getPrimaryKeyLong());
		String prefix = DateUtil.getSysDate();
		String key = prefix.substring(2)+seq;
		return new Long(key);
	}
	
	/***
	 * 6位日期+10位序列=16位的数字主键
	 * 获取一个定长的以日期为前缀的文件主键
	 * @return  Long
	 */
	public  Long getFileKey(){
		int length =10;//10位的序列值
		String seqVal = commUtilMapper.getPrimaryFileKey();
		String seq = String.format("%0" + length + "d", Long.valueOf(seqVal));
		String prefix = DateUtil.getSysDate();
		String key = prefix.substring(2)+seq;
		return new Long(key);
	}
	
	public String getKeyBySequenceName(String seqName)
	{
		String seqVal = commUtilMapper.getKeyBySequenceName(seqName);
		return seqVal;
	}
}
