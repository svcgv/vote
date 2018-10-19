package com.indihx.system.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.indihx.util.ConstantStatic;

/***
 * 
 * <p>描 述: 基础实体VO必须继承</p>
 * <p>公 司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2017年2月20日</p>
 * @author 谢追辉
 */
public class BaseVo  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nameCounts;  //前段字段个数 
	private String rows;  //每页显示条数
	private String pages; //当前页数
	private String isSelect; //是否选中  1 选中
	
	public String getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(String isSelect) {
		this.isSelect = isSelect;
	}

	public String getNameCounts() {
		return nameCounts;
	}

	public void setNameCounts(String nameCounts) {
		this.nameCounts = nameCounts;
	}
	
	public int getRows() {
		return StringUtils.isEmpty(rows)==true ? ConstantStatic.ROWS : Integer.parseInt(rows);
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public int getPages() {
		return StringUtils.isEmpty(pages)==true ? ConstantStatic.PAGES : Integer.parseInt(pages);
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	
}
