package cn.zhr.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OmOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 397618148845020246L;

	//主键ID
	private Long id;
	
	//订单编号
	private String orderNo;
	
	//订单时间
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
	private Date orderDate;
	
	//创建者
	private String creater;
	
	//流程实例ID
	private String processInstanceId;
	
	//类型
	private String type;
	
	//状态
	private String status;
	
	//订单金额
	private BigDecimal price;
	
	//订单备注
	private String remark;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "OmOrder [id=" + id + ", orderNo=" + orderNo + ", orderDate=" + orderDate + ", creater=" + creater
				+ ", processInstanceId=" + processInstanceId + ", type=" + type + ", status=" + status + ", price="
				+ price + ", remark=" + remark + "]";
	}
	
}
