package com.ios.notes;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GetValuesFromApplicationPropertiesFile {

	// application.properties file value :
	// mapValue={"ABC":'1',"cbd":'2',"xyd":'3',"ppq":'4',"AAA":'5'}
	@Value("#{${mapValue}}")
	private Map<String, Integer> mapValue;
	// this way we can get the data into the map from application.properties file

	// application.properties file value : integerValue=1,2,3,4,5,6,7,8,9
	@Value("${integerValue}")
	private int[] integerArray;
	// this way we can get the data into the integer array from
	// application.properties file

	// application.properties file value : integerList=11,22,33,44,55,66,77,88,99
	@Value("${integerList}")
	private List<Integer> integerList;
	// this way we can get the data into the List from application.properties file.

	// application.properties file value : integerSet=12,23,34,45,67,78,89,90

	@Value("${integerSet}")
	private Set<Integer> integerSet;
	// this way we can get the data into the set from application.properties file.

	// applicaton.properties value : booleanValue=true
	@Value("${booleanValue}")
	private boolean booleanValue;
	// this way we can get the boolean value from the application.properties value.

	public void printingAsLog() {
		log.info("* Map Values : {}", mapValue);
		log.info("* Integer Values Array : {}", integerArray);
		log.info("* Integer Values List : {}", integerList);
		log.info("* Integer Values Set : {}", integerSet);
		log.info("* Boolean Value : {}", booleanValue);
	}
}
