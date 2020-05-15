package com.mobilekeypad.endpoint;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobilekeypad.bean.MobileCombinationData;
import com.mobilekeypad.bean.Response;
import com.mobilekeypad.service.MobileKeypadService;

@RestController
@Validated
public class MobileKeypadEndpoint {

	@Autowired
	private MobileKeypadService service;

	@GetMapping("/getAllCombination/{mobileNo}")
	public Response getAllCombination(@PathVariable("mobileNo") @Pattern(regexp="\\d{7}|\\d{10}",message="Mobile Number must be in digits only and length must be either 7 or 10.") String mobileNumber,
			@RequestParam(value = "start", required = false, defaultValue = "0") int start,
			@RequestParam(value = "length", required = false, defaultValue = "10") int noOfRecords) {
		Response response = new Response();
		MobileCombinationData data = service.getAllPossibleAlphaNumericValue(mobileNumber);
		int fromIndex = start;
		int toIndex = start + noOfRecords;
		MobileCombinationData resultData = new MobileCombinationData();
		if (fromIndex > data.getCombinations().size()) {
			resultData.setCombinations(null);
		} else if (toIndex > data.getCombinations().size()) {
			toIndex = data.getCombinations().size();
			resultData.setCombinations(data.getCombinations().subList(fromIndex, toIndex));
		} else {
			resultData.setCombinations(data.getCombinations().subList(fromIndex, toIndex));
		}
		resultData.setMobileNumber(mobileNumber);
		response.setData(resultData);
		response.setRecordsTotal(data.getCombinations().size());
		response.setRecordsFiltered(data.getCombinations().size());
		return response;
	}

}
