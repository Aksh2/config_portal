package dataprovider;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class RC_DataProvider {
	private static String methodName = "blank";
	private static GetTestDataFromExcel getTestDataFromExcel = new GetTestDataFromExcel();
	private static Logger logger = Logger.getLogger(RC_DataProvider.class);

	@DataProvider(name = "getTestDataForMyTest")
	public synchronized static Object[][] getTestDataForMyTest(ITestContext context, Method method) throws Exception {
		methodName = method.getName();
		System.out.println("Test Method that will be executed: " + methodName);
		String dataSet = context.getCurrentXmlTest().getParameter("testData");
		System.out.println(context.getCurrentXmlTest());
		System.out.println("Data Set from TestNG XML : " + dataSet);
		String[] sheetName = dataSet.split("\\|");
		String workingSheet = sheetName[0].toString();
		String workingDataSet = sheetName[1].toString();
		String workingExcel;
		System.out.println("Array Size is " +sheetName.length);
		if (sheetName.length >=3){
		    System.out.println("Came in if");
            workingExcel=sheetName[2].toString();
        }
		else {
		    workingExcel="testData.xlsx";
        }
		logger.info("-------------------------------------------------");
		logger.info("workingSheet:" + workingSheet);
		logger.info("workingDataSet:" + workingDataSet);
		logger.info("workingExcel:" + workingExcel);
		System.out.println("workingSheet:" + workingSheet);
		System.out.println("workingDataSet:" + workingDataSet);
		System.out.println("workingExcel:" + workingExcel);
		// System.out.println("Sheetname is :" + sheetName[0].toString());
		Object[][] testObjArray = getTestDataFromExcel.getTableArray("src/test/resources"+File.separator+workingExcel, workingSheet,
				workingDataSet, methodName);
		System.out.println("testObjArray" + testObjArray);
		logger.info("testObjArray" + testObjArray);
		return (testObjArray);
	}

}
