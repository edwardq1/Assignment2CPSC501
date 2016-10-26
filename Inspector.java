import java.lang.reflect.*;
import java.util.Vector;
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
		
		Method[] classMethods = classObject.getDeclaredMethods();
		// information for a method
		System.out.println("Class methods:");
		for(Method method : classMethods){
			System.out.println("	" + method.getName() + ": ");
			Class[] methodParameters = method.getParameterTypes();
			System.out.print("		Parameters: ");
			for (Class parameter : methodParameters)
				System.out.print(" " + parameter.getName());
			System.out.print("\n		Exceptions: ");
			Class[] methodException = method.getExceptionTypes();
			for (Class exception : methodException)
				System.out.print(" " + exception.getName());
			System.out.print("\n		Return type: " + method.getReturnType());
			int temp = method.getModifiers();
			System.out.println("\n		Modifier: " + Modifier.toString(temp));

		}
		System.out.println("");
		
		// Constructors
		Constructor[] constructors = classObject.getConstructors();
		System.out.println("Constructors:");
		for (Constructor constructor : constructors){
			int temp = constructor.getModifiers();
			System.out.print("		" + constructor.getName() + "\n");
			Class[] parameterList = constructor.getParameterTypes();
			System.out.print("			Parameters:");
			for(Class parameter : parameterList)
				System.out.print(" " + parameter.getName() + ", ");
			System.out.print("\n			Modifier: " + Modifier.toString(temp));
			System.out.println();
		}

		//obtain fields
		
		Field[] classFields = classObject.getDeclaredFields();
		System.out.println("Fields:");
		for(Field field : classFields){
			int temp = field.getModifiers();
			System.out.print("		" + Modifier.toString(temp)
					+ " " + field.getType() + " "+ field.getName() + ": ");
			field.setAccessible(true);
			try {
				System.out.print(field.get(obj)+ "\n");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// supposed to recurse but it doesnt work

		}

		
		// handle hiearchy
		/*
		if (classObject.getSuperclass() != null){
			inspect(classObject.getSuperclass(), false);
		}*/
	}
}