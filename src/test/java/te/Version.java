package te;

import org.attune.RunJmeter;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Version {
	

	@Parameters({"jmxFile"})
	@Test
	public void PerTest(@Optional String file) {
		RunJmeter ob=new RunJmeter();
		int a= ob.execute(file);
		Assert.assertEquals(1, a);
  }

}
