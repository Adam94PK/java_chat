package client;

import java.util.Scanner;

public class ClientReader implements Runnable{
	private Scanner scaner;
	private boolean done;
	public ClientReader(Scanner scanner, boolean done) {
		this.scaner = scanner;
		this.done = done;
	}
	
	@Override
	public void run() {
		while(!done){
			if(scaner.hasNextLine()){
				String msq = scaner.nextLine();
				System.out.println(msq);
			}
		}
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
