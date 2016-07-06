package te;

import java.io.IOException;

import org.testng.annotations.Test;

public class AAA {
	
	
	@Test
	public void test1() throws IOException, InterruptedException{
		final String JMETER_HOME ="/home/nikos/Downloads/jmeter/apache-jmeter-3.0/bin/jmeter";
//		Process p = Runtime.getRuntime().exec("jmeter -n -t /path/to/your/test.jmx -l /path/to/results/file.jtl");
	
		Process p = Runtime.getRuntime().exec(JMETER_HOME+" -n -t "+System.getProperty("user.dir")+"/src/test/jmeter/Test1.jmx -l "+ System.getProperty("user.dir")+"/target/jmeter/report/file.jtl");
		
		p.waitFor();
	}

}
