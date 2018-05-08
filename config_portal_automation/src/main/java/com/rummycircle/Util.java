package com.rummycircle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jayway.restassured.RestAssured;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.rummycircle.restclient.RESTAssuredClient;
import com.sun.jna.platform.win32.WinDef.LONG;

public class Util {

	static ExtentTest extent_steps;
	protected static RESTAssuredClient rc;

	static String fileName = System.getProperty("user.dir")
			+ "/src/test/resources/" + "config.properties";

	private static Properties prop = new Properties();

	private static Logger logger = Logger.getLogger(Util.class);

	public static Map jsonToMapConverter(String jsonData) {

		HashMap<String, String> dataMap = new HashMap<String, String>();
		String jsondata = jsonData;

		if (jsonData.contains("|")) {
			String testdata[] = jsonData.split("\\|");

			for (int i = 0; i < testdata.length; i++) {
				String temp[] = testdata[i].split("=");
				dataMap.put(temp[0], temp[1]);
			}
			jsondata = dataMap.get("Verification");
			dataMap.remove("Verification");
		}

		try {
			Gson gson = new Gson();
			Map inspectionObj = gson.fromJson(jsondata, Map.class);
			logger.info("map:--" + inspectionObj);

			// put non json data in map
			Iterator datalocal = dataMap.entrySet().iterator();
			while (datalocal.hasNext()) {
				Map.Entry extradatamap = (Map.Entry) datalocal.next();
				System.out.println(extradatamap.getKey() + " = "
						+ extradatamap.getValue());
				inspectionObj.put(extradatamap.getKey(),
						extradatamap.getValue());
			}
			return inspectionObj;
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			logger.fatal("Exception in stringToJsonConverter For test Data "
					+ jsonData + "\n" + e);
		}
		return null;
	}

	/**
	 * @Description: This method returns "value" from the "Test Data sheet"
	 *               based on the Key passed.
	 * @return String.
	 * @param Map
	 *            ,String.
	 */

	public static String getTestDataStringValue(Map testDataHere, String keyName) {
		try {

			String ret = testDataHere.get(keyName).toString();
			return ret;
		} catch (Exception e) {
			logger.info(keyName + "is not present in test data = "
					+ testDataHere);
		}
		return keyName;

	}

	/*
	 * Trim Double data to 3 decimal to solve comparison problem like 3.000 &
	 * 3.0
	 */
	public static Map<String, String> convertDoubleValues(
			Map<String, String> originalMapData) {

		Map<String, String> finalReturnMap = new HashMap<String, String>();
		System.out.println("Input Map : " + originalMapData);
		Iterator it = originalMapData.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry originalData = (Map.Entry) it.next();
			System.out.println(originalData.getKey() + " = "
					+ originalData.getValue());

			DecimalFormat df = new DecimalFormat("#.###");
			df.setRoundingMode(RoundingMode.CEILING);

			Double x = Double.valueOf(String.valueOf(originalData.getValue()));
			System.out.println(df.format(x));

			finalReturnMap.put(String.valueOf(originalData.getKey()),
					String.valueOf(x));

		}
		System.out.println("Output Map : " + finalReturnMap);
		return finalReturnMap;
	}

	/* Convert generic MAP data to Map<String, String> type */

	public static Map<String, String> convertObjMapToStringMap(Map temp) {
		Map<String, String> retTemp = new HashMap<String, String>();
		Iterator it = temp.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry expectedData = (Map.Entry) it.next();
			System.out.println(expectedData.getKey() + " = "
					+ expectedData.getValue());
			retTemp.put(String.valueOf(expectedData.getKey()),
					String.valueOf(expectedData.getValue()));
		}
		return retTemp;
	}

	public static Map<String, String> getUserData(int user_id) {
		Map<String, String> allUserAttributes = new HashMap<String, String>();

		String endPoint = "ups/rest/userattributes/get/";
		try {
			InputStream is = new FileInputStream(fileName);
			prop.load(is);
			String ip = prop.getProperty("ui.base.url");
			String[] getUserData = ip.split("cms");
			String url = getUserData[0] + endPoint + user_id;

			// System.out.println("url is " +url);
			// HTTPResponse resp = rc.sendRequest(HTTPMethod.GET, "",
			// requestSpec);
			String resp = RestAssured.given().expect().statusCode(200).when()
					.get(url).getBody().asString();
			System.out.println("Response = " + resp);
			allUserAttributes = jsonTomap(resp);

			return allUserAttributes;

		} catch (Exception e) {
			extent_steps.log(LogStatus.WARNING, "FALIURE_GETTING_DB_DATA",
					e.getLocalizedMessage());
			e.printStackTrace();

		} catch (AssertionError e) {
			return null;
			// extent_steps.log(LogStatus.WARNING, "NO_USER_FOUND",
			// e.getLocalizedMessage());
			// e.printStackTrace();
		}
		return null;
	}

	public static Map<String, String> getUserAttributes(int user_id,
			String attribues) {
		Map<String, String> allUserAttributes = new HashMap<String, String>();

		String endPoint = "ups/rest/userattributes/get/" + user_id
				+ "?attributes=" + attribues + "";
		try {
			InputStream is = new FileInputStream(fileName);
			prop.load(is);
			String ip = prop.getProperty("ui.base.url");
			String[] getUserData = ip.split("cms");
			String url = getUserData[0] + endPoint;

			// System.out.println("url is " +url);
			// HTTPResponse resp = rc.sendRequest(HTTPMethod.GET, "",
			// requestSpec);
			String resp = RestAssured.given().expect().statusCode(200).when()
					.get(url).getBody().asString();
			System.out.println("Response = " + resp);
			allUserAttributes = jsonTomap(resp);

			return allUserAttributes;

		} catch (Exception e) {
			e.printStackTrace();

		} catch (AssertionError e) {
			return null;
			// extent_steps.log(LogStatus.WARNING, "NO_USER_FOUND",
			// e.getLocalizedMessage());
			// e.printStackTrace();
		}
		return null;
	}

	private static Map<String, String> jsonTomap(String resp) {
		Map<String, String> dataMap = new HashMap<String, String>();
		resp = resp.replace("{", "");
		resp = resp.replace("}", "");
		// resp = resp.replace("\"", "");
		String[] allKeyValues = resp.split(",\"");
		// System.out.println("Response = "+resp);

		for (int i = 0; i < allKeyValues.length; i++) {
			try {
				String temp = allKeyValues[i].replace("\"", "");
				String[] keyAndValue = temp.split(":");
				if (keyAndValue.length < 2)
					dataMap.put(keyAndValue[0], "");
				else
					dataMap.put(keyAndValue[0], keyAndValue[1]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return dataMap;
	}

	public static String removeDoubleValue(Map testDataHere, String keyName) {
		try {
			String ret = testDataHere.get(keyName).toString();
			Double doubleVal = Double.parseDouble(ret);
			return Integer.toString(doubleVal.intValue());
		} catch (Exception e) {
			logger.info(keyName + "is not present in test data = "
					+ testDataHere);
			// Return Default value if unable to covert to Double
			return String.valueOf(testDataHere.get(keyName));
		}
		// return "Key Not Found Error";
	}

	public static HashMap<String, String> removedDoubleValuesfromMap(Map data) {
		HashMap<String, String> returnMap = new HashMap<String, String>();

		Iterator it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry originalData = (Map.Entry) it.next();
			returnMap.put(
					String.valueOf(originalData.getKey()),
					removeDoubleValue(data,
							String.valueOf(originalData.getKey())));
		}
		return returnMap;
	}

	public static String readFile(String FileName) {
		String testdata = "";
		try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				testdata = testdata + sCurrentLine;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testdata;
	}

	public static Map<String, String> getAttributeInternalMap() {

		String data = readFile(System.getProperty("user.dir") + File.separator
				+ "src/test/resources/user_data.json");
		/*
		 * Sample data Personalization= %event_gaid% Internal Name mapping =
		 * "uiName":"event_gaid", "name":"event_googleAdvId",
		 */
		Map<String, String> attributeInternalMap = new HashMap<String, String>();
		JSONObject obj = new JSONObject(data);
		JSONArray arr = obj.getJSONArray("nonCampaignable");

		for (Iterator iterator = arr.iterator(); iterator.hasNext();) {
			JSONObject temp = (JSONObject) iterator.next();
			try {
				String uiName = String.valueOf(temp.get("uiName"));

				attributeInternalMap.put(
						uiName,
						String.valueOf(temp.get("name") + "||"
								+ String.valueOf(temp.get("defaultValue"))));
			} catch (JSONException e) {
				System.out.println("uiName key not found in user_data JSON");
			}
		}
		return attributeInternalMap;
	}

	public static Map<String, String> getPersonalizationData(int userID,
			String triggerEventObj) {
		Map<String, String> uItoInternalNameMap = getAttributeInternalMap();

		Map<String, String> personalizedValueMap = new HashMap<String, String>();

		Map<String, String> userData = getUserData(userID);

		JSONObject jsonObject = new JSONObject(triggerEventObj);
		jsonObject.get("value");
		String eventBody = (String) jsonObject.get("value");
		Map<String, String> eventMap = jsonTomap(eventBody);

		Set<String> temp = uItoInternalNameMap.keySet();

		for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			String uIData[] = (uItoInternalNameMap.get(key)).split("\\|\\|");
			String x;
			/*
			 * if(uIData[0].contains("event_cac"))
			 * System.out.println("##found");
			 */
			if (uIData[0].contains("event_"))
				x = eventMap.get(uIData[0].split("event_")[1]);
			else
				x = userData.get(uIData[0]);
			// For Default value
			if (x == null || x.equals("null")) {
				if (uIData.length < 2)
					x = "";
				else
					x = uIData[1];
			}
			if (uIData[0].contains("event_"))
				personalizedValueMap.put("%" + key + "%", x);
			// personalizedValueMap.put("%event_"+key+"%", x);
			else
				personalizedValueMap.put("%" + key + "%", x);
		}
		return personalizedValueMap;
	}

	public static String replacePersonalizationDataInMessage(int userID,
			String triggerEventObj, String message) {

		Map<String, String> useratrbValue = getPersonalizationData(userID,
				triggerEventObj);

		Set<String> keySet = useratrbValue.keySet();
		// specialPersonalizationAtrb contains all the date related attributes
		// and all attributes date and content is replaced in this method
		String specialPersonalizationAtrb = "%clubsttschngdt%-%lstLgnTmA%-%lstLgnTmD%-%lstLgnTmM%-%lstLgnTmN%-%club_validity%-%clubsttschngdt%-%registrationDate%-%lstCshPlyDtA%-%lstCshPlyDtD%-%lstPrmTornTmA%-%lstPrmTornTmD%-%lstPrmTornTmM%-%lstPrmTornTmN%-%dob%-%lstDpstTmD%-%lstDpstTmN%-%lstDpstTmA%-%lstDpstTmStmpD%-%lstDpstTmStmp%-%club_validity%-%lltI%-%ldtI%-%lctI%-%lpgtI%-%fcad30dI%-%fcad120dI%-%lpgdI%";

		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();

			if (specialPersonalizationAtrb.contains(key)
					&& (!useratrbValue.get(key).contains("NA"))) {
				message = message.replaceAll(key,
						dateconv(useratrbValue.get(key)));
			} else {
				message = message.replaceAll(key, useratrbValue.get(key));
			}

			// message=message.replaceAll(key, useratrbValue.get(key));
		}
		System.out.println(message);
		// return message;

		// List<String> attbWithTime=new ArrayList<>();
		/*
		 * String specialPersonalizationAtrb="%clubsttschngdt%";
		 * System.out.println("Rechecl"); for (@SuppressWarnings("rawtypes")
		 * Iterator iterator = keySet.iterator(); iterator.hasNext();) { String
		 * key = (String) iterator.next();
		 * 
		 * if(specialPersonalizationAtrb.contains(key)&&(!useratrbValue.get(key).
		 * contains("NA"))){ message=message.replaceAll(key,
		 * dateconv(useratrbValue.get(key))); }else{
		 * message=message.replaceAll(key, useratrbValue.get(key)); }
		 * 
		 * }
		 */
		return message;

	}

	public static String CovertexponentialToString(String value) {

		BigDecimal bd = new BigDecimal(value);
		System.out.println(bd.doubleValue());

		// If you are sure that's not a floating point number, then use
		System.out.println(bd.longValue());
		return String.valueOf(bd.longValue());
	}

	public static String dateconv(String longTime) {
		Date date = new Date(Long.valueOf(longTime));
		Format format = new SimpleDateFormat("dd-MMM-yyyy");
		return format.format(date);
	}

	/*
	 * public static void main(String[] args) {
	 * System.out.println("heyyyy "+dateconv("1505138350837")); }
	 */

	// this wil convert time in long format to date in dd-MMM-YYYY format
	public static String getPersonalDate(Long timeStamp) {
		Instant instant = Instant.ofEpochMilli(timeStamp);
		LocalDate date = LocalDateTime.ofInstant(instant,
				ZoneId.of("Asia/Kolkata")).toLocalDate();
		return date.format(DateTimeFormatter.ofPattern("dd-MMM-YYYY"));
	}

}