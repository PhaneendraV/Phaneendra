package com.mobilekeypad.bean;

import java.io.Serializable;

public class Response implements Serializable{

	private static final long serialVersionUID = 1L;

	private int recordsFiltered;
	
	private int recordsTotal;
	
	private MobileCombinationData data;

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public MobileCombinationData getData() {
		return data;
	}

	public void setData(MobileCombinationData data) {
		this.data = data;
	}
	
	
}
