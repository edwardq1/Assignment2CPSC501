import java.lang.reflect.*;
import java.util.Vector;
public class Inspector {
	public void inspect(Object obj, boolean recursive){
		Class classObject = obj.getClass();
		inspectClass(classObject);
		inspectInterfaces(classObject);
		inspectMethods(classObject);
		inspectConstructors(classObject);
		inspectFields(classObject, obj);
		
	}
	
	public void inspectClass(Class classObject){
		String className = classObject.getSimpleName();
		// print the declaring class
		System.out.println("Declaring class: " + className);
		
		Class superClass = classObject.getSuperclass();
		className = superClass.getSimpleName();
		System.out.println("Super class: " + className);

	}
	
	public void inspectInterfaces(Class classObject){
		// obtaining the interfaces
		Class[] interfaces = classObject.getInterfaces();
		System.out.print("Interfaces:");
		if (interfaces.length != 0 ){
			for (int i = 0; i < interfaces.length; i++)
				System.out.print(" " + interfaces[i].getSimpleName() + ",");
		}
		else{
			System.out.print(" No interfaces");
		}
	}
	
	//Method that will handle grabbing methods of a class
	public void inspectMethods(Class classObject){
		System.out.println("");
		Method[] classMethods = classObject.getDeclaredMethods();
		// information for a method
		System.out.println("Class methods:");
		for(Method method : classMethods){
			System.out.println("	" + method.getName() + ": ");
			Class[] methodParameters = method.getParameterTypes();
			System.out.print("		Parameters: ");
			if (methodParameters.length != 0){
				for (Class parameter : methodParameters)
					System.out.print(" " + parameter.getSimpleName());
			}
			else
				System.out.print(" No parameters");
			System.out.print("\n		Exceptions: ");
			Class[] methodException = method.getExceptionTypes();
			if (methodException.length != 0){
				for (Class exception : methodException)
					System.out.print(" " + exception.getSimpleName());
			}
			else
				System.out.print(" No exceptions");
			System.out.print("\n		Return type: " + method.getReturnType());
			int temp = method.getModifiers();
			System.out.println("\n		Modifier: " + Modifier.toString(temp));
			System.out.println("");

		}
	}
	
	//Method that will handle grabbing the fields of a class
	public void inspectFields(Class classObject, Object object){
		//obtain fields
		Field[] classFields = classObject.getDeclaredFields();
		System.out.println("Fields:");
		for(Field field : classFields){
			field.setAccessible(true);
			if (field.getType().isArray()){
				try {
					System.out.println("	Array field: " + "\n		Component type: " + field.getType().getComponentType() + "\n		Name: " + 
							field.getName() + "\n		Length: " + Array.getLength(field.get(object)));
					
					System.out.print("		Contents: ");
					for (int i = 0; i < Array.getLength(field.get(object)); i++){
						System.out.print(Array.get(field.get(object), i) + ", ");
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				int temp = field.getModifiers();
				System.out.print("	" + Modifier.toString(temp)
						+ " " + field.getType() + " "+ field.getName() + " = ");
				try {
					System.out.print(field.get(object)+ "");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println();
			// supposed to recurse but it doesnt work
		}
	}
	//Method that will handle grabbing the constructors of a class
	public void inspectConstructors(Class classObject){
		// Constructors
		Constructor[] constructors = classObject.getConstructors();
		System.out.println("Constructors:");
		for (Constructor constructor : constructors){
			int temp = constructor.getModifiers();
			System.out.print("	" + constructor.getName() + ":\n");
			Class[] parameterList = constructor.getParameterTypes();
			System.out.print("		Parameters:");
			if (parameterList.length != 0){
				for(Class parameter : parameterList)
					System.out.print(" " + parameter.getName() + ", ");
			}
			else
				System.out.print(" No parameters");
			System.out.print("\n		Modifier: " + Modifier.toString(temp));
			System.out.println();
		}
	}
}