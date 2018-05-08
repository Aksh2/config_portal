package com.rummycircle;

import java.util.Properties;

import javax.swing.text.TabExpander;

import org.apache.log4j.Logger;

import com.rummycircle.model.TableResponse;
import com.rummycircle.model.tablerequest.TableRequest;
import com.rummycircle.restclient.HTTPBody;
import com.rummycircle.restclient.HTTPHeaders;
import com.rummycircle.restclient.HTTPMethod;
import com.rummycircle.restclient.HTTPParams;
import com.rummycircle.restclient.HTTPRequest;
import com.rummycircle.restclient.HTTPResponse;
import com.rummycircle.utils.testutils.BaseTest;
import com.rummycircle.utils.testutils.NewBaseTest;
import com.rummycircle.utils.testutils.PropertyReader;

public class APICalls extends NewBaseTest {
	
	protected Properties custom = null;
	protected Logger log = null;

	
	public APICalls() {
		custom = PropertyReader.loadCustomProperties("custom.properties");
		log = Logger.getLogger(APICalls.class);
	}
	
	public TableResponse getTablePropertiesByName(String name) {
		String endpoint = String.format("%s://%s%s", custom.getProperty("gpc.protocol"),
				custom.getProperty("gpc.host"), ServicesEndPoint.GPC_FETCH_TEMPLATES);
		log.info(String.format("The URL is:%s", endpoint));
		HTTPHeaders headers = new HTTPHeaders();
		HTTPParams params = new HTTPParams();
		params.addParam("templateName", name);
		

		HTTPRequest requestSpec = new HTTPRequest(params);
		HTTPResponse response = rc.sendRequest(HTTPMethod.GET, endpoint, requestSpec);

		log.info("Status code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().getBodyText());
		log.info("Reason pharse: " + response.getReasonPhrase());
		
		return deserializeJsonToJavaObject(response.getBody().getBodyText(), TableResponse.class);
		
	}
	
	public TableResponse createTableProperties(TableRequest tableRequest) {
		String endpoint = String.format("%s://%s%s", custom.getProperty("gpc.protocol"),
				custom.getProperty("gpc.host"), ServicesEndPoint.GPC_FETCH_TEMPLATES);
		log.info(String.format("The URL is:%s", endpoint));
		HTTPHeaders headers = new HTTPHeaders();
		headers.addHeader("Content-Type", "application/json");
		String body = gson.toJson(tableRequest);
		log.info("Created Json Body: " + body);
		HTTPRequest requestSpec = new HTTPRequest(headers,body);
		HTTPResponse response = rc.sendRequest(HTTPMethod.POST, endpoint, requestSpec);

		log.info("Status code: " + response.getStatusCode());
		log.info("Response Body: " + response.getBody().getBodyText());
		log.info("Reason pharse: " + response.getReasonPhrase());
		
		return deserializeJsonToJavaObject(response.getBody().getBodyText(), TableResponse.class);
		
	}



}
