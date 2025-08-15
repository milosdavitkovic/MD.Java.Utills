package milos.davitkovic.utills.services.impl.utils.rest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;


/**
 * 
 * @author Milos Davitkovic
 *
 */
@Service
public class RestFn {

	public RestObject GET_Request(String url) {
		RestTemplate rest = new RestTemplate();
		RestObject restObject = rest.getForObject(url, RestObject.class);
		System.out.println("RestFn: Get resposne " + restObject.toString());
		return restObject;
	}
	
	public String GET_Request_Value(String url) {
		RestTemplate rest = new RestTemplate();
		RestObject restObject = rest.getForObject(url, RestObject.class);
		System.out.println("RestFn: Get resposne value" + restObject.values);
		return restObject.values;
	}
	
	public RestObject POST_Request(String url, String finalString) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne " + response.toString());
		return response;
	} 
	
	public String POST_Request_Value(String url, String finalString) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne value" + response.values);
		return response.values;
	} 
	
	/**
	 * 
	 * @param url URL of Rest Server
	 * @param jsonObj Object for senting towards Rest Server.
	 * 		JSONObject objectForSending = new JSONObject();
			objectForSending.put("DeviceIdentification", userLicence.getLicence());
			String listOfSensorInstanceDTOStr = new Gson().toJson(sensorInstanceDTO);
			objectForSending.put("ListOfSensors", listOfSensorInstanceDTOStr);
	 * @return
	 */
	public String POST_Request(String url, JSONObject jsonObj) {
		JSONObject objectForSending = jsonObj;
		String finalString = objectForSending.toJSONString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne value" + response.values);
		return response.values;
	} 
	
	public <T> T POST_Request(String url, JSONObject jsonObj, Class<T> outputClass) throws JsonParseException, JsonMappingException, IOException {
		JSONObject objectForSending = jsonObj;
		String finalString = objectForSending.toJSONString();
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
		System.out.println("RestFn: Post resposne value" + response.values);
		ObjectMapper mapper = new ObjectMapper();
		T obj = mapper.readValue(response.values, outputClass);
		return obj;
	} 
	
//	public <T> T[] POST_Request_Array(String url, JSONObject jsonObj, Class<T> outputClass) throws JsonParseException, JsonMappingException, IOException {
//		JSONObject objectForSending = jsonObj;
//		String finalString = objectForSending.toJSONString();
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.setAccept(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
//		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
//		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
//		System.out.println("RestFn: Post resposne value" + response.values);
//		ObjectMapper mapper = new ObjectMapper();
////		List<T> tmpArray = new ArrayList<T>();
////		List<outputClass> tmpArray = new ArrayList<outputClass>();
////		T tmpArray[] = new ArrayList<T>();
//		T[] obj = (T[]) mapper.readValue(response.values, outputClass);
////		T[] obj = mapper.readValue(response.values, outputClass);
//		return obj;
//	}
	
//	public Object POST_Request(String url, String objectName, Object tmpObj) {
//		JSONObject objectForSending = new JSONObject();
//		String tmpObjJSON = new Gson().toJson(tmpObj);
//		objectForSending.put(objectName, tmpObjJSON);
//		String finalString = objectForSending.toJSONString();
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> entity = new HttpEntity<String>(finalString, headers);
//		RestObject response = restTemplate.postForObject(url, entity, RestObject.class);
//		System.out.println("RestFn: Post resposne value" + response.values);
//		return response.values;
//	}
//
//	public <T> String ObjecttoJSON(Class<T> outputClass) {
//		Gson gson = new Gson();
//		String jsonInString = gson.toJson(outputClass);
//		return jsonInString;
//	}
	
	
}
