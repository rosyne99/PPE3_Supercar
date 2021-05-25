package Junit;

import junit.framework.TestCase;

public class totalTest extends TestCase {
	
	public void test() {
		Junit_testing test = new Junit_testing();
		int output =  test.total(3000,0,200);
		assertEquals(3200, output);
	}

}
