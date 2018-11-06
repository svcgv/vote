package com.indihx.PmReviewCommentInfo.entity;



import com.indihx.BaseEntity;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author hb
 * @email hb1230123@hotmail.com
 * @date 2018-11-06 19:33:25
 */
public class PmReviewCommentInfoEntity extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

private long commentId;
	/**
	 * 
	 */

private String commentType;
	/**
	 * 
	 */

private long reviewId;
	/**
	 * 
	 */

private String reviewCode;
	/**
	 * 
	 */

private String result;
	/**
	 * 
	 */

private long wrotenUserId;
	/**
	 * 
	 */

private String wrotenUserName;
	/**
	 * 
	 */

private String commentDetail;
	/**
	 * 
	 */

private String writeTime;
	/**
	 * 
	 */

private String isDelete;

	/**
	 * 设置：
	 */
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	/**
	 * 获取：
	 */
	public long getCommentId() {
		return commentId;
	}
	/**
	 * 设置：
	 */
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	/**
	 * 获取：
	 */
	public String getCommentType() {
		return commentType;
	}
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
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	/**
	 * 获取：
	 */
	public String getReviewCode() {
		return reviewCode;
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
	public void setWrotenUserId(long wrotenUserId) {
		this.wrotenUserId = wrotenUserId;
	}
	/**
	 * 获取：
	 */
	public long getWrotenUserId() {
		return wrotenUserId;
	}
	/**
	 * 设置：
	 */
	public void setWrotenUserName(String wrotenUserName) {
		this.wrotenUserName = wrotenUserName;
	}
	/**
	 * 获取：
	 */
	public String getWrotenUserName() {
		return wrotenUserName;
	}
	/**
	 * 设置：
	 */
	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}
	/**
	 * 获取：
	 */
	public String getCommentDetail() {
		return commentDetail;
	}
	/**
	 * 设置：
	 */
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}
	/**
	 * 获取：
	 */
	public String getWriteTime() {
		return writeTime;
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
