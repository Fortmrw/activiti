package cn.zhr.entity;

import java.util.Date;

/**
 * @table:user_t
 * @Copyright(C) 2017-2018 北京睿银华信科技有限公司 
 * @author:ryhx.Zhanghaoran
 * @createTime:2017-06-21 11:26
 */
public class User {

    private Integer id;
    private String name;
    private String password;
    private Integer sex;
    private Integer age;
    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", password=").append(password);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}