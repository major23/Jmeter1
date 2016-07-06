package org.attune;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.reporters.ResultCollector;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class RunJmeter {

	 public int execute(String file)
	 {
		final String JMETER_HOME ="/home/nikos/Downloads/jmeter/apache-jmeter-3.0";
	    	try
	    	{
		        // JMeter Engine
		        StandardJMeterEngine jmeter = new StandardJMeterEngine();
		        
		        
		        
		        
		        
		        // Initialize Properties, logging, locale, etc.
		        JMeterUtils.loadJMeterProperties(JMETER_HOME+"/bin/jmeter.properties");
		        JMeterUtils.setJMeterHome(JMETER_HOME);
			        
		        
		        Summariser summer = null;
		        String summariserName = JMeterUtils.getPropDefault("summariser.name", "summary");
		        if (summariserName.length() > 0) {
		            summer = new Summariser(summariserName);
		        }
		        
		        
		       
		        
		        
		        
		        
		        
		        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
		        JMeterUtils.initLocale();
			
		        // Initialize JMeter SaveService
		        SaveService.loadProperties();
		        
		        File jmxFile = new File(find(file));
		        HashTree testPlanTree = SaveService.loadTree(jmxFile);	
		        
		        
		        String logFile = System.getProperty("user.dir") +"/target/jmeter/report/"+file+".jtl";
		        ResultCollector logger = new ResultCollector(summer);
		        logger.setFilename(logFile);
		        testPlanTree.add(testPlanTree.getArray()[0], logger);
		        
		        
		        
		        // Run JMeter Test
		        jmeter.configure(testPlanTree);
		        System.out.println("Running jmeter test: "+jmxFile);
		        jmeter.run();
		        Thread.sleep(2000);
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
