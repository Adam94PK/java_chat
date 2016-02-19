package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class ServerThread implements Runnable{
	
	private ArrayBlockingQueue<String> arrayBlockingQueue;
	private Socket incoming;
	private PrintWriter writer;
	private Scanner reader;
	//private ServerSender sender;
	
	public ServerThread(Socket incoming, ArrayBlockingQueue<String> arrayBlockingQueue) {
		this.arrayBlockingQueue = arrayBlockingQueue;
		this.incoming = incoming;
	}
	
	@Override
	public void run() {
		try{
			reader = new Scanner(incoming.getInputStream());
			writer = new PrintWriter(incoming.getOutputStream(), true);
		}catch (IOException e){
			e.printStackTrace();
		}
		
		boolean done = false;
		while(!done){
			String msg = reader.nextLine();
			if(msg.trim().toUpperCase().equals("END"))
				done = true;
			else{
				//System.out.println(msg);
				try {
					arrayBlockingQueue.put(msg);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			incoming.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String message){
		writer.println(message);
	}

}
