package org.attune;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class RunJmeter {
	
	/* Get the Jmeter home directory of your system
	 * You can define it implicitly or make it an env. variable and retrieve it 
	 */
	//final String JMETER_HOME ="/home/nikos/Downloads/jmeter/apache-jmeter-3.0";
	 public static final String JMETER_HOME = System.getenv("JMETER_HOME");	
	 

	 public int executeTest(String file)
	 {		    	
		try
    	{
	        StandardJMeterEngine jmeter = new StandardJMeterEngine();
	        // Initialize Properties, logging, locale, etc.
	        JMeterUtils.loadJMeterProperties(JMETER_HOME+"/bin/jmeter.properties");
	        JMeterUtils.setJMeterHome(JMETER_HOME);
	        
	        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
	        JMeterUtils.initLocale();	
		
	        // Initialize JMeter SaveService
	        SaveService.loadProperties();
	        
	        //define jmx file for execution
	        File jmxFile = new File(find(file));
	        HashTree testPlanTree = SaveService.loadTree(jmxFile);		        
	        
	        //enable .jtl report generation for the .jmx run and define target location
	        Summariser summer = null;
	        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
	        if (summariserName.length() > 0) {
	            summer = new Summariser(summariserName);
	        }
	        String logFile = System.getProperty("user.dir") +"/target/jmeter/report/"+file+".jtl";
	        ResultCollector logger = new ResultCollector(summer);
	        logger.setFilename(logFile);
	        testPlanTree.add(testPlanTree.getArray()[0], logger);        
	        
	        // Run JMeter Test
	        jmeter.configure(testPlanTree);
	        System.out.println("Running jmeter test: "+jmxFile);
	        jmeter.run();	        	        
	        
	        //After test run generate an html report from the jtl report and place it on the same folder
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Source xslDoc = new StreamSource(JMETER_HOME+"/extras/jmeter-results-detail-report_21.xsl");
	        Source xmlDoc = new StreamSource(logFile);
	        String outputFileName = System.getProperty("user.dir") +"/target/jmeter/report/"+file+".html";
	        OutputStream htmlFile = new FileOutputStream(outputFileName);
	        Transformer transformer = tFactory.newTransformer(xslDoc);
	        transformer.transform(xmlDoc, new StreamResult(htmlFile));        
	        
	    	return 1;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	}
    }	    
	    
		@SuppressWarnings("rawtypes")
		public String find(String fileName) throws Exception {
		    File root = new File(System.getProperty("user.dir"));
		    boolean recursive = true;
		    Collection files = FileUtils.listFiles(root, null, recursive);
		    for (Iterator iterator = files.iterator(); iterator.hasNext();) {
		    	File file = (File) iterator.next();
		        if(file.getName().equals(fileName)){
		        	return file.getAbsolutePath();
		        }
		    }
		    return fileName;
		} 
	    
	}
