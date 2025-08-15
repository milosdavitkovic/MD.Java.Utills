package milos.davitkovic.javautil.utills.services.impl.utils.rest;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import milos.davitkovic.javautil.utills.annotations.UtilClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 *
 * @author Milos Davitkovic
 *
 */
@Slf4j
@UtilClass
@NoArgsConstructor
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

}
