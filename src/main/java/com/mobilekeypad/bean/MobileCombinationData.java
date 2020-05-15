package com.mobilekeypad.bean;

import java.io.Serializable;
import java.util.List;

public class MobileCombinationData implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<List<String>> combinations;
	
	private String mobileNumber;

	public List<List<String>> getCombinations() {
		return combinations;
	}

	public void setCombinations(List<List<String>> combinations) {
		this.combinations = combinations;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}	
}
