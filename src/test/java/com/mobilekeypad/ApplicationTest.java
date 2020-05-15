package com.mobilekeypad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import com.mobilekeypad.bean.Response;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@ParameterizedTest
	@MethodSource("generateData")
	public void testAllCombination(String mobileNumber, int start, int noOfRecords, List<List<String>> result) {
		Response data = this.restTemplate.getForObject("http://localhost:" + port + "/getAllCombination/" + mobileNumber
				+ "?start=" + start + "&length=" + noOfRecords, Response.class);
		assertEquals(result, convertTestableList(data.getData().getCombinations()));
	}

	public List<String> convertTestableList(List<List<String>> list) {
		List<String> retList = new ArrayList<String>();
		for (List<String> strList : list) {
			retList.add(strList.get(0));
		}
		return retList;
	}

	static Stream<Arguments> generateData() {
		String[][] list = {
				{ "9810", "W810", "X810", "Y810", "Z810", "9T10", "WT10", "XT10", "YT10", "ZT10", "9U10", "WU10",
						"XU10", "YU10", "ZU10", "9V10", "WV10", "XV10", "YV10", "ZV10" },
				{ "8813", "T813", "U813", "V813", "8T13", "TT13", "UT13", "VT13", "8U13", "TU13", "UU13", "VU13",
						"8V13", "TV13", "UV13", "VV13", "881D", "T81D", "U81D", "V81D", "8T1D", "TT1D", "UT1D", "VT1D",
						"8U1D", "TU1D", "UU1D", "VU1D", "8V1D", "TV1D", "UV1D", "VV1D", "881E", "T81E", "U81E", "V81E",
						"8T1E", "TT1E", "UT1E", "VT1E", "8U1E", "TU1E", "UU1E", "VU1E", "8V1E", "TV1E", "UV1E", "VV1E",
						"881F", "T81F", "U81F", "V81F", "8T1F", "TT1F", "UT1F", "VT1F", "8U1F", "TU1F", "UU1F", "VU1F",
						"8V1F", "TV1F", "UV1F", "VV1F" } };

		return Stream.of(Arguments.of("9810", 0, 10, Arrays.asList(list[0]).subList(0, 10)),
				Arguments.of("9810", 10, 10, Arrays.asList(list[0]).subList(10, 20)),
				Arguments.of("9810", 3, 2, Arrays.asList(list[0]).subList(3, 5)),
				Arguments.of("8813", 0, 1000, Arrays.asList(list[1])));
	}
}
