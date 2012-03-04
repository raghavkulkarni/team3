package com.team3.allindiabankdir.utils;

import com.team3.allindiabankdir.database.adapters.BanksAdapter;

public enum WorkflowState {
	BANK("Enter Account", BanksAdapter.COL_BANK_NAME),
	CITY("Enter City", BanksAdapter.COL_DISTRICT),
	BRANCH("Enter Branch", BanksAdapter.COL_BRANCH),
	SHOWDETAILS("", "");
	
	private String labelMessage;
	private String fieldName;
	
	private WorkflowState(String labelMessage, String fieldName) {
		this.labelMessage = labelMessage;
		this.fieldName = fieldName;
	}

	public String getLabelMessage() {
		return labelMessage;
	}

	public String getFieldName() {
		return fieldName;
	}
}
