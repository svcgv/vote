package com.indihx.PmReviewInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-08 21:43:00
 */
public class PmReviewInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long reviewId;
	/**
	 * 
	 */

private long foreignId;
	/**
	 * 
	 */

private String foreignCode;
	/**
	 * 
	 */

private String reviewType;
	/**
	 * 
	 */

private String reviewUserName;
	/**
	 * 
	 */

private long reviewUserCode;
	/**
	 * 
	 */

private String result;
	/**
	 * 
	 */

private String isDelete;

	/**
	 * 设置：
	 */
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	/**
	 * 获取：
	 */
	public long getReviewId() {
		return reviewId;
	}
	/**
	 * 设置：
	 */
	public void setForeignId(long foreignId) {
		this.foreignId = foreignId;
	}
	/**
	 * 获取：
	 */
	public long getForeignId() {
		return foreignId;
	}
	/**
	 * 设置：
	 */
	public void setForeignCode(String foreignCode) {
		this.foreignCode = foreignCode;
	}
	/**
	 * 获取：
	 */
	public String getForeignCode() {
		return foreignCode;
	}
	/**
	 * 设置：
	 */
	public void setReviewType(String reviewType) {
		this.reviewType = reviewType;
	}
	/**
	 * 获取：
	 */
	public String getReviewType() {
		return reviewType;
	}
	/**
	 * 设置：
	 */
	public void setReviewUserName(String reviewUserName) {
		this.reviewUserName = reviewUserName;
	}
	/**
	 * 获取：
	 */
	public String getReviewUserName() {
		return reviewUserName;
	}
	/**
	 * 设置：
	 */
	public void setReviewUserCode(long reviewUserCode) {
		this.reviewUserCode = reviewUserCode;
	}
	/**
	 * 获取：
	 */
	public long getReviewUserCode() {
		return reviewUserCode;
	}
	/**
	 * 设置：
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取：
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置：
	 */
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：
	 */
	public String getIsDelete() {
		return isDelete;
	}
}
