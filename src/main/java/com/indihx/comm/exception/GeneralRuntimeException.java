package com.indihx.comm.exception;

/**
 * <p>标    题: 物业管理信息系统(PMS)</p>
 * <p>描    述: 自定义系统运行异常</p> 
 * <p>版    权: Copyright (c) 2018  </p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2018年1月29日 下午1:08:53</p>
 * <p>@author zhengwei</p>
 * <p>@version 1.0</p>
 * <p>GeneralRuntimeException.java</p>
 * <p>自定义全局Spring系统运行异常-Spring只对运行异常进行回滚，所以所有自定义异常都继承运行异常。</p>
 */
public class GeneralRuntimeException extends RuntimeException{
	static final long serialVersionUID = -3387516993124230001L;
	/**
	 * 终端用户提示
	 * When an GeneralException occur,pass the message to end user
	 */
	private String endUserTip = null;
    /** 
     * Constructs a new general exception with <code>null</code> as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
	public GeneralRuntimeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEndUserTip() {
		return endUserTip;
	}

	public void setEndUserTip(String endUserTip) {
		this.endUserTip = endUserTip;
	}

    /** 
     * Constructs a new general exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for 
     *          later retrieval by the {@link #getMessage()} method.
     */
    public GeneralRuntimeException(String message) {
    	super(message);
    	this.endUserTip=message;
    }

    /**
     * Constructs a new general exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * <code>cause</code> is <i>not</i> automatically incorporated in
     * this runtime exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public GeneralRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.endUserTip=message;
    }

    /** 
     * Constructs a new general exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.4
     */
    public GeneralRuntimeException(Throwable cause) {
    	super(cause);
    	this.endUserTip = cause.getMessage();
    }

}
