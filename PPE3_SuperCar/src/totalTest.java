import Junit.Junit_testing;
import junit.framework.TestCase;

public class totalTest extends TestCase {
	public void test() {
		//pour tester si la calculation est correct
		Junit_testing test = new Junit_testing();
		int output =  test.total(3000,0,200);
		assertEquals(3200, output);
	}

}
