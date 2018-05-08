package com.rummycircle.utils;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Utils {

    private static Logger logger = Logger.getLogger(Utils.class);
    
    public static Map jsonToMapConverter(String jsonData) {

        HashMap<String, String> dataMap = new HashMap<String, String>();
        String jsondata = jsonData;

        if (jsonData.contains("|")) {
            String testdata[] = jsonData.split("\\|");

            for (int i = 0; i < testdata.length; i++) {
                // String temp[];
                if (testdata[i].contains("Verification=")) {
                    String temp[] = testdata[i].split("Verification=");
                    dataMap.put("Verification", temp[1]);
                } else if (testdata[i].contains("BODY=")) {
                    String temp[] = testdata[i].split("BODY=");
                    dataMap.put("BODY", temp.length > 1 ? temp[1] : "");
                }
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
                logger.info(extradatamap.getKey() + " = " + extradatamap.getValue());
                inspectionObj.put(extradatamap.getKey(), extradatamap.getValue());
            }
            return inspectionObj;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            logger.fatal("Exception in stringToJsonConverter For test Data " + jsonData + "\n" + e);
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
            logger.info(keyName + "is not present in test data = " + testDataHere);
//            return "false";
        }
         return "Key Not Found Error";
    }

    /*
     * Trim Double data to 3 decimal to solve comparison problem like 3.000 &
     * 3.0
     */
    public static Map<String, String> convertDoubleValues(Map<String, String> originalMapData) {

        Map<String, String> finalReturnMap = new HashMap<String, String>();
        logger.info("Input Map : " + originalMapData);
        Iterator it = originalMapData.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry originalData = (Map.Entry) it.next();
            logger.info(originalData.getKey() + " = " + originalData.getValue());

            DecimalFormat df = new DecimalFormat("#.###");
            df.setRoundingMode(RoundingMode.CEILING);

            try {
                if (originalData.getKey().equals("last_updated") || originalData.getKey().equals("update_time")
                        || originalData.getKey().equals("globalTxn_update_time")) {
                    finalReturnMap.put(String.valueOf(originalData.getKey()),
                            dateStringToLong(String.valueOf(originalData.getValue())));
                } else {
                    Double x = Double.valueOf(String.valueOf(originalData.getValue()));
                    logger.info(df.format(x));
                    finalReturnMap.put(String.valueOf(originalData.getKey()), String.valueOf(x));
                }
            } catch (NumberFormatException e) {
                finalReturnMap.put(String.valueOf(originalData.getKey()), String.valueOf(originalData.getValue()));
                e.printStackTrace();
            }
        }
        logger.info("Output Map : " + finalReturnMap);
        return finalReturnMap;
    }

    /* Convert generic MAP data to Map<String, String> type */

    public static Map<String, String> convertObjMapToStringMap(Map temp) {
        Map<String, String> retTemp = new HashMap<String, String>();
        Iterator it = temp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry expectedData = (Map.Entry) it.next();
            logger.info(expectedData.getKey() + " = " + expectedData.getValue());
            retTemp.put(String.valueOf(expectedData.getKey()), String.valueOf(expectedData.getValue()));
        }
        return retTemp;
    }

    public static String dateStringToLong(String originalDate) {
        try {
            // String today = "2016-11-18 16:39:17.0";

            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            Date date = formatter.parse(originalDate);
            long dateInLong = date.getTime();

            logger.info("date = " + date);
            logger.info("dateInLong = " + dateInLong);
            return String.valueOf(Double.valueOf(dateInLong));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return originalDate;
    }

    public static Map<String, String> convertKeystoLowerCase(Map<String, String> inputMap) {

        Map<String, String> resultMap = new HashMap<String, String>();

        Set<String> x1 = inputMap.keySet();
        for (Iterator iterator = x1.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            resultMap.put(key.toLowerCase(), inputMap.get(key));
        }

        return resultMap;
    }


}
