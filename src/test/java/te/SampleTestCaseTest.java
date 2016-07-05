package te;


import org.attune.RunJemerTests;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTestCaseTest {

	@Parameters({"jmxFile"})
	@Test
	public void PerTest(@Optional String file) {
		RunJemerTests ob=new RunJemerTests();
		int a= ob.checkPer(file);
		System.out.println("value of a   "+a);
		Assert.assertEquals(1, a);
  }
}
