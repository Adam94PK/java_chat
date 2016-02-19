package client;

import java.util.Scanner;

public class ClientReader implements Runnable{
	private Scanner scaner;
	
	public ClientReader(Scanner scanner) {
		this.scaner = scanner;
	}
	
	@Override
	public void run() {
		while(true){
			String msq = scaner.nextLine();
			System.out.println(msq);		
		}
	}

}
