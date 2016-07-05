package te;


import org.attune.RunJemerTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTestCaseTest {

  @Test
  public void MyTest1() {
	  int a=10;
		Assert.assertEquals(10, a);
  }

  @Test
  public void PerTest() {
	  RunJemerTests ob=new RunJemerTests();
		int a= ob.checkPer();
		System.out.println("value of a   "+a);
		Assert.assertEquals(1, a);
  }
}
