import java.lang.reflect.*;
public class Inspector {
	public void inspect(Object obj, boolean recursive){
		obj = new Object();
		System.out.println(obj.getClass());
	}
}