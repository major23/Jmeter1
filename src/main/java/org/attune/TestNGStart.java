package org.attune;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.testng.TestNG;

public final class TestNGStart {

	static org.slf4j.Logger logger 			= LoggerFactory.getLogger(TestNGStart.class);
	static TestNG tng 						= null;
	static String suitePath 				= "";
	static boolean buildFailedonErrors 		= false;
	
	private static String configurationFileName;
	private static String groupDraw 		= "group.draw";
	private static String groupSi 			= "group.si";
	private static String groupLocale 		= "group.locale";
	private static String groupBillingJob 	= "group.billing.job";
	private static String groupDryadGui		= "group.dryadgui";
	
	//Arguments
	private static Map<String,String> arguments = new HashMap<String,String>();
	public static String poolSizeKey			= "suites.threadPoolSize";
	public static String suitesFilenames 		= "suites.filenames";
	public static String serverXMLFile 			= "configuration.filename";
	public static String reportOutput			= "report.output";
	public static String suitesPath 			= "suites.path";
	public static String testListener 			= "test.listener";
	public static String suiteListener 			= "suite.listener";
	public static String failOnError 			= "buildFailedonError";
	public static String exGroupDraw 			= "exclude.group.draw";
	public static String exGroupSI				= "exclude.group.si";
	public static String exGroupLocale			= "exclude.group.locale";
	public static String exGroupStringBillingJob= "exclude.billing.job";
	public static String exGroupDryadGUI		= "exclude.dryadgui";
	
	private TestNGStart(){
		
	}
	
	public static void main(String[] args) throws IOException {
		logger.info("*** Initialize testNG ***");
		tng = new TestNG();
		arguments = getArguments(args);
		setThreadPoolSize(arguments.get(poolSizeKey));
		setSuitePath(arguments.get(suitesPath));
		setSuitesFileName(arguments.get(suitesFilenames));
		setServerXMLFile(arguments.get(serverXMLFile));
		setReportOutputDirectory(arguments.get(reportOutput));
		setListeners(arguments.get(testListener),arguments.get(suiteListener));
		setBuildFailedonError(arguments.get(failOnError));
		exludeGroups(arguments.get(exGroupDraw),
					 arguments.get(exGroupDryadGUI),
					 arguments.get(exGroupLocale),
					 arguments.get(exGroupSI),
					 arguments.get(exGroupStringBillingJob));
		tng.run();
		int status = tng.getStatus();
		logger.info("TestNG status is  : "+status);
		buildFailedonErrors(args,status);
	}
	
	private static Map<String,String> getArguments(String[] args){
		Map<String,String> arguments = new HashMap<String,String>();
		for(String temp : args){
			if(temp.startsWith(poolSizeKey)){
				arguments.put(poolSizeKey, temp.replace(poolSizeKey+":", ""));
			}else
			if(temp.startsWith(suitesFilenames)){
				arguments.put(suitesFilenames, temp.replace(suitesFilenames+":", ""));
			}else
			if(temp.startsWith(serverXMLFile)){
				arguments.put(serverXMLFile, temp.replace(serverXMLFile+":", ""));
			}else
			if(temp.startsWith(reportOutput)){
				arguments.put(reportOutput, temp.replace(reportOutput+":", ""));
			}else
			if(temp.startsWith(suitesPath)){
				arguments.put(suitesPath, temp.replace(suitesPath+":", ""));
			}else
			if(temp.startsWith(testListener)){
				arguments.put(testListener, temp.replace(testListener+":", ""));
			}else
			if(temp.startsWith(suiteListener)){
				arguments.put(suiteListener, temp.replace(suiteListener+":", ""));
			}else
			if(temp.startsWith(failOnError)){
				arguments.put(failOnError, temp.replace(failOnError+":", ""));
			}else
			if(temp.startsWith(exGroupDraw)){
				arguments.put(exGroupDraw, temp.replace(exGroupDraw+":", ""));
			}else
			if(temp.startsWith(exGroupSI)){
				arguments.put(exGroupSI, temp.replace(exGroupSI+":", ""));
			}else
			if(temp.startsWith(exGroupLocale)){
				arguments.put(exGroupLocale, temp.replace(exGroupLocale+":", ""));
			}else
			if(temp.startsWith(exGroupStringBillingJob)){
				arguments.put(exGroupStringBillingJob, temp.replace(exGroupStringBillingJob+":", ""));
			}else
			if(temp.startsWith(exGroupDryadGUI)){
				arguments.put(exGroupDryadGUI, temp.replace(exGroupDryadGUI+":", ""));
			}
		}
		return arguments;
	}
	
	private static void exludeGroups(String exGroupDraw,String exGroupDryadGUI,String exGroupLocale,String exGroupSI,String exGroupStringBillingJob) {
		String excludeGroups = "";
		try{
			if("true".equalsIgnoreCase(exGroupDraw)){
				logger.info("Exclude Group    : "+groupDraw);
				if(excludeGroups.isEmpty()){
					excludeGroups = groupDraw;
				}else{
					excludeGroups += ","+groupDraw;
				}
			}
		}catch(Exception ex){
			logger.debug("Error",ex);
		}
		try{
			if("true".equalsIgnoreCase(exGroupSI)){
				logger.info("Exclude Group    : "+groupSi);
				if(excludeGroups.isEmpty()){
					excludeGroups = groupSi;
				}else{
					excludeGroups += ","+groupSi;
				}
			}
		}catch(Exception ex){
			logger.debug("Error",ex);
		}
		
		try{
			if("true".equalsIgnoreCase(exGroupLocale)){
				logger.info("Exclude Group    : "+groupLocale);
				if(excludeGroups.isEmpty()){
					excludeGroups = groupLocale;
				}else{
					excludeGroups += ","+groupLocale;
				}
			}
		}catch(Exception ex){
			logger.debug("Error",ex);
		}
		
		try{
			if("true".equalsIgnoreCase(exGroupStringBillingJob)){
				logger.info("Exclude Group    : "+groupBillingJob);
				if(excludeGroups.isEmpty()){
					excludeGroups = groupBillingJob;
				}else{
					excludeGroups += ","+groupBillingJob;
				}
			}
		}catch(Exception ex){
			logger.debug("Error",ex);
		}
		
		try{
			if("true".equalsIgnoreCase(exGroupDryadGUI)){
				logger.info("Exclude Group    : "+groupDryadGui);
				if(excludeGroups.isEmpty()){
					excludeGroups = groupDryadGui;
				}else{
					excludeGroups += ","+groupDryadGui;
				}
			}
		}catch(Exception ex){
			logger.debug("Error",ex);
		}
		
		tng.setExcludedGroups(excludeGroups);
		
	}

	private static boolean setBuildFailedonError(String fail){
		try{
			if("true".equalsIgnoreCase(fail)){
				buildFailedonErrors = true;
			}
		}catch(Exception ex){
			logger.error("No Param for buildFailOnErrors, continue with default vaule : false");
			buildFailedonErrors = false;
		}
		logger.info("FailedonError    : "+buildFailedonErrors);
		return buildFailedonErrors;
	}
	
	private static void buildFailedonErrors(String[] args,int status){
		String failed = "false";
		if(args.length==7){
			failed = args[6];
			if("true".equalsIgnoreCase(failed) && status!=0){
				System.exit(-1);
			}
		}
		System.exit(0);
	}
	
	public static void setServerXMLFile(String serverXmlFile) {
		String configurationFileName = "";	
		//Read Configuration.xml from Template.xml or testNG.xml
		if(serverXmlFile == null || serverXmlFile.isEmpty()){
			configurationFileName = "Configuration_Default.xml";
		}else{
			configurationFileName = serverXmlFile;
		}
		logger.info("Configuration f  : "+configurationFileName);
		setConfigurationFileName(configurationFileName);
	}

	private static void setSuitePath(String suitesPath) {
		logger.info("Suites path      : "+suitesPath);
		suitePath = suitesPath;
	}

	private static void setReportOutputDirectory(String string) throws IOException {
		tng.setOutputDirectory(string);
		File dir = new File(tng.getOutputDirectory());
		deleteDirectory(dir);
		//Initialize Path
		tng.setOutputDirectory(string);
		//Create Directory
		File f = new File(tng.getOutputDirectory());
		if(f.mkdir()){
			logger.info("Output Directory : "+f.getCanonicalPath().toString());
		}else{
			logger.info("Output Directory : "+f.getCanonicalPath().toString());
		}
	}

	private static boolean deleteDirectory(File dir) throws IOException {
	    if(! dir.exists() || !dir.isDirectory())    {
	        return false;
	    }
	    String[] files = dir.list();
	    for(int i = 0, len = files.length; i < len; i++){
	        File f = new File(dir, files[i]);
	        if(f.isDirectory()) {
	            deleteDirectory(f);
	        }
	    }
	    return dir.delete();
	}
	
	private static List<String> setSuitesFileName(String arguments){
		List<String> suites = new ArrayList<String>();
		String[] suiteFileNames = null;
		if(arguments!=null){
			suiteFileNames = arguments.replaceAll("\n", " ").split(" ");
		}
		if(suiteFileNames.length>0){
			for(int i=0;i<suiteFileNames.length;i++){
				suites.add(suitePath+suiteFileNames[i]);
			}
			tng.setTestSuites(suites);
			tng.setPreserveOrder(true);
			tng.setRandomizeSuites(false);
			System.setProperty("Total.Suites", Integer.toString(suites.size()));
		}else{
			logger.warn("No suites selected");
		}
		return suites;
	}
	
	private static void setThreadPoolSize(String poolSize){
		logger.info("Thread poolSize  : "+poolSize);
		tng.setSuiteThreadPoolSize(Integer.parseInt(poolSize));
	}
	
	@SuppressWarnings("rawtypes")
	private static void setListeners(String testListener,String suiteListener){
		//Insert Default Listeners for ReportNG
		List<Class> classes= new ArrayList<Class>();
		Class htmlListener = org.uncommons.reportng.HTMLReporter.class;
		Class xmlReporter  = org.testng.reporters.XMLReporter.class;
		tng.setUseDefaultListeners(false);
		classes.add(htmlListener);
		classes.add(xmlReporter);
		logger.info("Listener         : "+htmlListener.getCanonicalName());
		logger.info("Listener         : "+xmlReporter.getCanonicalName());
//		//Insert Optional Listeners
//		Class listener = null;
//		try{
//			if("true".equals(testListener)){
//				listener = com.upstreamsystems.dryad.testcore.controllers.TestListener.class;
//				classes.add(listener);
//				logger.info("Listener         : "+listener.getCanonicalName());
//			}
//		}catch(Exception ex){
//			logger.debug("Error ",ex);	
//		}
//		try{
//			if("false".equals(suiteListener)){
//				listener = com.upstreamsystems.dryad.testcore.controllers.SuiteListener.class;
//				classes.add(listener);
//				logger.info("Listener         : "+listener.getCanonicalName());
//			}
//		}catch(Exception ex){
//			logger.debug("Error ",ex);	
//		}
//		tng.setListenerClasses(classes);
	}
	
	public static TestNG getTng() {
		return tng;
	}

	public static void setTng(TestNG tng) {
		TestNGStart.tng = tng;
	}

	public static String getConfigurationFileName() {
		return configurationFileName;
	}

	public static void setConfigurationFileName(String configurationFileName) {
		TestNGStart.configurationFileName = configurationFileName;
	}
	
}