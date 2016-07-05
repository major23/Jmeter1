package org.attune;
import org.apache.commons.io.FileUtils;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;

public class RunJemerTests {


    public int checkPer(String file)
    {
    	try
    	{
	        // JMeter Engine
	        StandardJMeterEngine jmeter = new StandardJMeterEngine();
	        // Initialize Properties, logging, locale, etc.
	        JMeterUtils.loadJMeterProperties("/home/nikos/Downloads/jmeter/apache-jmeter-3.0/bin/jmeter.properties");
	        JMeterUtils.setJMeterHome("/home/nikos/Downloads/jmeter/apache-jmeter-3.0");
	        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
	        JMeterUtils.initLocale();
	
	        // Initialize JMeter SaveService
	        SaveService.loadProperties();
	
	        // Load existing .jmx Test Plan
//	        FileInputStream in = new FileInputStream("src/test/jmeter/AttueUSers3.jmx");
//	        HashTree testPlanTree = SaveService.loadTree(in);
//	        in.close();
	        
	        File jmxFile = new File(find(file));//      "/src/test/jmeter/Test1.jmx");
	        HashTree testPlanTree = SaveService.loadTree(jmxFile);
	        
	       
	
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
