package com.mobilekeypad.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mobilekeypad.bean.MobileCombinationData;

@Service
public class MobileKeypadService {

	private final Map<Character, String> KEYPAD_COMBINATION = new HashMap<Character, String>();

	public MobileKeypadService() {
		KEYPAD_COMBINATION.put('0', "0");
		KEYPAD_COMBINATION.put('1', "1");
		KEYPAD_COMBINATION.put('2', "2ABC");
		KEYPAD_COMBINATION.put('3', "3DEF");
		KEYPAD_COMBINATION.put('4', "4GHI");
		KEYPAD_COMBINATION.put('5', "5JKL");
		KEYPAD_COMBINATION.put('6', "6MNO");
		KEYPAD_COMBINATION.put('7', "7PQRS");
		KEYPAD_COMBINATION.put('8', "8TUV");
		KEYPAD_COMBINATION.put('9', "9WXYZ");
	}
	
	public List<List<String>> convertResponseSubList(List<String> list){
		List<List<String>> retList = new ArrayList<List<String>>(list.size());
		for(String str:list) {
			List<String> tmpList = new LinkedList<String>();
			tmpList.add(str);
			retList.add(tmpList);
		}
		return retList;
	}

	@Cacheable(value="MobileDictionary", key = "#p0")
	public MobileCombinationData getAllPossibleAlphaNumericValue(String mobileNumber) {
		System.out.println("oh");
		List<String> combination = new ArrayList<String>();
		getAllCombinations(mobileNumber,mobileNumber.length()-1, "",combination);
		MobileCombinationData data = new MobileCombinationData();
		data.setCombinations(convertResponseSubList(combination));
		data.setMobileNumber(mobileNumber);
		return data;
	}

	public void getAllCombinations(String mobileNumber, int currentDigitIndex, String currentCombination, List<String> combination) {
		if (currentDigitIndex == -1)
		{
			combination.add(currentCombination);
			return;
		}

		char currentDigit = mobileNumber.charAt(currentDigitIndex);

		for (int i = 0; i < KEYPAD_COMBINATION.get(currentDigit).length(); i++) {
			getAllCombinations( mobileNumber, currentDigitIndex - 1, KEYPAD_COMBINATION.get(currentDigit).charAt(i) + currentCombination, combination);
		}
	}
}
