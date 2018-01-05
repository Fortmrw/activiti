package cn.zhr.exception;

public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2523339325165616987L;

	/**
	 * 数据库操作
	 */
	public static final ServiceException DB_SELECT_ERROR = new ServiceException("900100100", "数据库select操作异常");
	
	public static final ServiceException DB_INSERT_ERROR = new ServiceException("900100200", "数据库insert操作异常");
	
	public static final ServiceException DB_UPDATE_ERROR = new ServiceException("900100300", "数据库update操作异常");
	public static final ServiceException DB_UPDATE_ERROR_0 = new ServiceException("900100301", "数据更新条数为零");

	public static final ServiceException DB_DELETE_ERROR = new ServiceException("900100400", "数据库delete操作异常");
	
	public static final ServiceException DB_GET_SEQ_NEXT_VALUE_ERROR = new ServiceException("900100500", "数据库获取序列操作异常");

	/**
	 * 数据类型转换异常
	 */
	public static final ServiceException DT_CHANGE_ERROR = new ServiceException("900100600", "数据类型转换异常");
	public static final ServiceException DT_CHANGE_ERROR_OBJTOINT = new ServiceException("900100601", "数据类型转换异常,Object转换int操作异常");
	
	/**
	 * 参数为空异常
	 */
	public static final ServiceException PARAM_NULL_ERROR = new ServiceException("900100800", "参数为空");
	public static final ServiceException PARAM_NULL_ERROR_VERIFICATIONCODE = new ServiceException("900100801", "验证码不能为空");
	public static final ServiceException PARAM_NULL_ERROR_VERIFICATIONCODEERROR = new ServiceException("900100802", "验证码错误");
	
	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 具体异常码
	 */
	protected String code;

	public ServiceException(String code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public ServiceException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public String getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public ServiceException newInstance(String msgFormat, Object... args) {
		return new ServiceException(this.code, msgFormat, args);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message) {
		super(message);
	}
}
