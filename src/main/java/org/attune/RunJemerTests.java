package org.attune;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import java.io.FileInputStream;

public class RunJemerTests {

    public static void main(String[] argv) throws Exception {

    }
    
    public int checkPer()
    {
    	try
    	{
        // JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        System.out.println(1);
        // Initialize Properties, logging, locale, etc.
        JMeterUtils.loadJMeterProperties("F:/JMeter/apache-jmeter-3.0/bin/jmeter.properties");
        JMeterUtils.setJMeterHome("F:/JMeter/apache-jmeter-3.0");
        JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
        JMeterUtils.initLocale();

        // Initialize JMeter SaveService
        SaveService.loadProperties();
        System.out.println(2);


        // Load existing .jmx Test Plan
        FileInputStream in = new FileInputStream("src/test/jmeter/AttueUSers3.jmx");
        HashTree testPlanTree = SaveService.loadTree(in);
        in.close();

        // Run JMeter Test
        jmeter.configure(testPlanTree);
        System.out.println(3);
        jmeter.run();
        System.out.println(4);
    	return 1;
    	}
    	catch(Exception e)
    	{
    		return 0;
    	} 
    	}
    
}
