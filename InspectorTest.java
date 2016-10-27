import static org.junit.Assert.*;

import org.junit.Test;

public class InspectorTest {
	Inspector object;
	@Test
	public void test() {
		Inspector object = new Inspector();
		//Testing if the first method retrieved in class A is correct
		String method = object.testingMethod(ClassA.class);
		assertEquals("run", method);
		method = object.testingMethod(ClassC.class);
		assertEquals("run", method);
		method = object.testingMethod(ClassD.class);
		assertEquals("toString", method);
		
	}
	@Test
	public void testForVariable(){
		Inspector object = new Inspector();
		//Testing if the value is correct for fields
		String variable= object.testingVariable(ClassA.class);
		assertEquals("val", variable);
		variable= object.testingVariable(ClassC.class);
		assertEquals("val2", variable);
		variable= object.testingVariable(ClassD.class);
		assertEquals("val", variable);

	}

	@Test
	public void testSuperClass(){
		Inspector object = new Inspector();
		String superClass = object.testSuperClass(ClassA.class);
		assertEquals("Object", superClass);
		superClass = object.testSuperClass(ClassB.class);
		assertEquals("ClassC", superClass);
		superClass = object.testSuperClass(ClassC.class);
		assertEquals("ClassD", superClass);


	}
}
