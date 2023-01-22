package com.bcm.truckingdeliverystatussapi.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
	private String errorCode;
	private String messageHeader;
	private String messageDetail;
	private LocalDateTime timeStamp;
	
	public ErrorDetails(String errorCode, String messageHeader, String messageDetail, LocalDateTime timeStamp) {
		super();
		this.errorCode = errorCode;
		this.messageHeader = messageHeader;
		this.messageDetail = messageDetail;
		this.timeStamp = timeStamp;
	}
	
	public String getMessageHeader() {
		return messageHeader;
	}

	public void setMessageHeader(String messageHeader) {
		this.messageHeader = messageHeader;
	}

	public String getMessageDetail() {
		return messageDetail;
	}

	public void setMessageDetail(String messageDetail) {
		this.messageDetail = messageDetail;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}	
	
}
