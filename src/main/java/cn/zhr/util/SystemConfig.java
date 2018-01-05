package cn.zhr.util;

import cn.zhr.util.PerprotiesUtils;

/**
 * Created by Administrator on 2017/5/16.
 */
public class SystemConfig {
    /**
     * 系统文件配置 加载。
     */
    private static PerprotiesUtils public_system = PerprotiesUtils.getPerprotiesUtils("/jdbc.properties");

    /**
     * 数据库驱动类
     */
    public final static String driver = public_system.getValue("driver");
	 /**
     * 数据库url
     */
    public final static String url = public_system.getValue("url");
	 /**
     * 数据库username
     */
    public final static String username = public_system.getValue("username");
	 /**
     * 数据库password
     */
    public final static String password = public_system.getValue("password");
}
