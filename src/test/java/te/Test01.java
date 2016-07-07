package te;

import org.attune.RunJmeter;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test01 {
	

	@Parameters({"jmxFile"})
	@Test(priority=1)
	public void PerTest(@Optional String file) {
		RunJmeter ob=new RunJmeter();
		int a= ob.executeTest(file);
		Assert.assertEquals(1, a);
  }

	
	
	
}
