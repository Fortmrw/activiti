package cn.zhr.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

	/**
	 * ThreadLocal确保高并发下每个请求的request，response都是独立的
	 */
	private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();
	private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();
	private static ThreadLocal<ArrayList<Object>> getList = new ThreadLocal<ArrayList<Object>>() {
		protected ArrayList<Object> initialValue() {
			return new ArrayList<Object>();
		}
	};
	private static ThreadLocal<HashMap<String, Object>> getHashMap = new ThreadLocal<HashMap<String, Object>>() {
		protected HashMap<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};

	/**
	 * 线程安全初始化reque，respose对象
	 * 
	 * @param request
	 * @param response
	 */
	@ModelAttribute
	public void initReqAndRep(HttpServletRequest request, HttpServletResponse response) {
		currentRequest.set(request);
		currentResponse.set(response);
	}

	/**
	 * 共享空List实例,用于空实例
	 */
	public ArrayList<Object> getArrayList() {
		return getList.get();
	}

	/**
	 * 共享空Map实例,用于空实例
	 */
	public HashMap<String, Object> getHashMap() {
		return getHashMap.get();
	}

	/**
	 * 线程安全
	 * 
	 * @return
	 */
	public HttpServletRequest getHttpRequest() {
		return (HttpServletRequest) currentRequest.get();
	}
	
	/**
	 * 线程安全
	 * 
	 * @return
	 */
	public HttpSession getHttpSession() {
		HttpServletRequest req = (HttpServletRequest) currentRequest.get();
		return req.getSession() ;
	}
	
	/**
	 * 线程安全
	 * 
	 * @return
	 */
	public HttpServletResponse getHttpResponse() {
		return (HttpServletResponse) currentResponse.get();
	}

	/**
	 * 获取String类型参数 无值则返回null
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return getHttpRequest().getParameter(key);
	}

	/**
	 * 获取String类型参数 无值则返回"" .
	 * 
	 * @param key
	 * @return
	 */
	public String getString_UrlDecode_GBK(String key) {
		try {
			return new String(getString(key.toString()).getBytes("GBK"), "UTF-8");
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 根据参数名从HttpRequest中获取String类型的参数值，无值则返回"" .
	 * 
	 * @param key
	 *            .
	 * @return String .
	 */
	public String getString_UrlDecode_UTF8(String key) {
		try {
			return URLDecoder.decode(this.getString(key), "UTF-8");
		} catch (Exception e) {
			return "";
		}

	}

	/**
	 * 根据参数名从HttpRequest中获取String[] 类型的参数值 有 返回字符串数组 无 返回null;
	 * 
	 * @param key
	 *            .
	 * @return String[] or null .
	 */
	public String[] getStringArr(String key) {
		return getHttpRequest().getParameterValues(key);
	}
	// /**
	// * 获取分页参数，包含当前页、每页记录数.
	// *
	// * @return PageParam .
	// */
	// public PageParam getPageParam() {
	// return new PageParam(getPageNum(), getNumPerPage());
	// }
	// /**
	// * 获取当前页
	// * 如果没有值则默认返回1.
	// *
	// * @author WuShuicheng.
	// */
	// private int getPageNum() {
	// // 当前页数
	// String pageNumStr = getHttpRequest().getParameter("pageNum");
	// int pageNum = 1;
	// if (StringUtils.isNotBlank(pageNumStr)) {
	// pageNum = Integer.valueOf(pageNumStr);
	// }
	// return pageNum;
	// }
	// /**
	// * 获取每页记录数
	// * 如果没有值则默认返回10.
	// *
	// */
	// private int getNumPerPage() {
	// String numPerPageStr = getHttpRequest().getParameter("numPerPage");
	// int numPerPage = 10;
	// if (StringUtils.isNotBlank(numPerPageStr)) {
	// numPerPage = Integer.parseInt(numPerPageStr);
	// }
	// return numPerPage;
	// }

	/**
	 * 根据参数名从HttpRequest中获取Double类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return DoubleValue or null .
	 */
	public Double getDouble(String key) {
		String value = getHttpRequest().getParameter(key);
		if (!StringUtils.isEmpty(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Integer类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return IntegerValue or null .
	 */
	public Integer getInteger(String key) {
		String value = getHttpRequest().getParameter(key);
		if (!StringUtils.isEmpty(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * 根据参数名从HttpRequest中获取Long类型的参数值，无值则返回null .
	 * 
	 * @param key
	 *            .
	 * @return LongValue or null .
	 */
	public Long getLong(String key) {
		String value = getHttpRequest().getParameter(key);
		if (!StringUtils.isEmpty(value)) {
			return Long.parseLong(value);
		}
		return null;
	}
}
