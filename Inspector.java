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
		//obtain constructor
		// not complete yet
		Constructor constructor = null;
		Constructor constructor1 = null;
		try {
			if (classObject.getConstructor() != null)
				constructor = classObject.getConstructor();
			//constructor1 = classObject.getConstructor(int.class);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (constructor != null){
			System.out.println("Constructor: " + constructor.getName());
			Class[] parameters = constructor.getParameterTypes();
			for (Class parameter : parameters)
				System.out.print("\n		" + parameter.getName());
		}
		/*if (constructor1 != null){
			System.out.print("Constructor: " + constructor1.getName());
			Class[] parameters = constructor1.getParameterTypes();
			for (Class parameter : parameters)
				System.out.println("\n		Parameter: " + parameter.getName());
		}*/
		
		
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
			if ((recursive == true) && (field.getType() == Type.class)){
				try {
					inspect(field.get(obj), true);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		
		
		
	}
}