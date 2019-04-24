
public class DemoIf {

    public DemoIf() {
    	
    	int a = 10;
    	int b = 10;
    	
    	if(a < b){

		System.out.println("a es menor que b");

	}else if(a > b){

			System.out.println("a es mayor que b");

		}else{

			System.out.println("a es igual que b");

		}

	}
    
    
    public static void main(String[] args){

	new DemoIf();

}
    
}