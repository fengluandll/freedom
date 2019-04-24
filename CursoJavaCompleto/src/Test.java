/**
 * @autor startOnline
 */

/**
 * 
 * 
 */
public  class Test {

	Integer num = 9;
	
	public static float returnFloat(){
		return 20.0f;
	}
	
	public static double returnFloat(int q){
		return 20.0d;
	}
	
	public static void main(String[] args) {
		System.out.println(returnFloat());
//		String num = "h";
//		num = null;
//		if (num instanceof String)
//			System.out.println("String");
//		else
//			System.out.println("No String");
		Test test = new Test();
		test.aMethod(test);
		System.out.println(test.num);
		Character c = 952;
		Character cr = 952;
		
		
//		char ca = 95;
//		if(c == ca){
//			System.out.println("Iguales");
//		}else{
//			System.out.println("No Iguales");
//		}
//		
//		int a = 0;
//		int b = (char)a;
//		System.out.println(c.hashCode());
//		System.out.println(cr.hashCode());
		int[] a = {1,2};
		
	}
	
	public void aMethod(Test a){
		a.num = 200;
	}
	
	


}
