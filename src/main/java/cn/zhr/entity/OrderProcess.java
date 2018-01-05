package cn.zhr.entity;

import java.util.Date;

public class OrderProcess extends OmOrder {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5152020283429526098L;

	// 任务id
	private String taskId;

	// 任务名称
	private String taskName;

	// 任务办理人
	private String assignee;

	// 流程实例id
	private String processInstanceId;

	// 业务标识
	private String businesskey;

	// 任务标识
	private String taskDefinitionKey;

	// 当前活动标识
	private String activityId;

	// 开始时间
	private Date startTime;
	
	// 结束时间
	private Date endTime;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getBusinesskey() {
		return businesskey;
	}

	public void setBusinesskey(String businesskey) {
		this.businesskey = businesskey;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "OrderProcess [taskId=" + taskId + ", taskName=" + taskName + ", assignee=" + assignee
				+ ", processInstanceId=" + processInstanceId + ", businesskey=" + businesskey + ", taskDefinitionKey="
				+ taskDefinitionKey + ", activityId=" + activityId + ", startTime=" + startTime + ", endTime=" + endTime
				+ "]";
	}
}
