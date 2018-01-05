package cn.zhr.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenxiaoli on 2017/5/16.
 */
public class PerprotiesUtils {

    private Properties properties ;

    /**
     * 获取属性工具类实例
     * @param path 属性文件路径
     */
    private PerprotiesUtils(String path) {
        properties = new Properties();
        try {
            properties.load(PerprotiesUtils.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取属性工具类实例
     * @param path 属性文件路径
     * @return 属性文件获取器
     */
    public static PerprotiesUtils getPerprotiesUtils(String path){
        return new PerprotiesUtils(path);
    }

    /**
     * 根据 键 获取 值
     * @param key 键
     * @return  值
     */
    public String getValue(String key){
        return  properties.getProperty(key);
    }

    /**
     *  根据 键 获取 值
     * @param key  键
     * @param defaultValue 默认值
     * @return 值
     */
    public String getValue(String key , String defaultValue){
        return properties.getProperty(key,defaultValue);
    }
}
