package wftac.example.wss001c.clientsample;

import wftac.example.wss001c.*;

public class ClientSample {

	public static void main(String[] args) {
		String name1 = "Beautiful";
		String name2 = "Gorgeous";
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        HelloService service1 = new HelloService();
	        System.out.println("Create Web Service...");
	        Hello port1 = service1.getHelloPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.greet(name1));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        Hello port2 = service1.getHelloPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.greet(name2));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
