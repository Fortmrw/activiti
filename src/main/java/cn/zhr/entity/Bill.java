package cn.zhr.entity;

/**
 * @table:bill
 * @Copyright(C) 2017-2018 北京睿银华信科技有限公司 
 * @author:ryhx.Zhanghaoran
 * @createTime:2017-06-21 11:26
 */
public class Bill {

    /**
     * 主键
     */
    private Long id;
    /**
     * 账单编号
     */
    private String billNo;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建者名称
     */
    private String creator;
    /**
     * 创建者ID
     */
    private Long creatorId;
    /**
     * 标题
     */
    private String title;
    /**
     * 详细内容
     */
    private String content;
    /**
     * 总金额
     */
    private String totalCost;
    /**
     * 交易类型
     */
    private Integer tranType;
    /**
     * 支出类型
     */
    private Integer payType;
    /**
     * 收入方式
     */
    private Integer earningType;
    /**
     * 交易对象
     */
    private String tranTarget;
    /**
     * 状态
     */
    private Integer status;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
    public Integer getIsDelete() {
        return isDelete;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }
    public Long getCreatorId() {
        return creatorId;
    }
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    public String getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost == null ? null : totalCost.trim();
    }
    public Integer getTranType() {
        return tranType;
    }
    public void setTranType(Integer tranType) {
        this.tranType = tranType;
    }
    public Integer getPayType() {
        return payType;
    }
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    public Integer getEarningType() {
        return earningType;
    }
    public void setEarningType(Integer earningType) {
        this.earningType = earningType;
    }
    public String getTranTarget() {
        return tranTarget;
    }
    public void setTranTarget(String tranTarget) {
        this.tranTarget = tranTarget == null ? null : tranTarget.trim();
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
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
        sb.append(", billNo=").append(billNo);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", remark=").append(remark);
        sb.append(", creator=").append(creator);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", totalCost=").append(totalCost);
        sb.append(", tranType=").append(tranType);
        sb.append(", payType=").append(payType);
        sb.append(", earningType=").append(earningType);
        sb.append(", tranTarget=").append(tranTarget);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}