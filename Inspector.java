import java.lang.reflect.*;
public class Inspector {
	public void inspect(Object obj, boolean recursive){
		Class classObject = obj.getClass();
		String className = classObject.getName();
		// print the declaring class
		System.out.println("Declaring class: " + className);
		
		Class superClass = classObject.getSuperclass();
		className = superClass.getName();
		System.out.println("Super class: " + className);

		// obtaining the interfaces
		Class[] interfaces = classObject.getInterfaces();
		System.out.print("Interfaces:");
		for (int i = 0; i < interfaces.length; i++){
			System.out.print(" " + interfaces[i].getName() + ",");
		}
		System.out.println("");
		
		Method[] classMethods = classObject.getMethods();
		System.out.println("Class methods:");
		for(Method method : classMethods){
			System.out.println(method.getName() + ": ");
			Class[] methodParameters = method.getParameterTypes();
			for (Class parameter : methodParameters)
				System.out.print(" " + parameter.getName() + ",");
			Class[] methodException = method.getExceptionTypes();
			for (Class exception : methodException)
				System.out.print(" " + exception.getName() + ",");
			System.out.print(" " + method.getReturnType() + ",");
			System.out.println(" " + method.getModifiers() + "");

		}
		System.out.println("");

	}
}