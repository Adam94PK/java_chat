package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerThread implements Runnable{
	
	private ArrayBlockingQueue<String> arrayBlockingQueue;
	private Socket incoming;
	private PrintWriter writer;
	private Scanner reader;
	private ArrayList<ServerThread> threadList;
	//private ServerSender sender;
	
	public ServerThread(Socket incoming, ArrayBlockingQueue<String> arrayBlockingQueue, ArrayList<ServerThread> threadList) {
		this.arrayBlockingQueue = arrayBlockingQueue;
		this.incoming = incoming;
		this.threadList = threadList;
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
			if(reader.hasNext()){
				String msg = reader.nextLine();
				if(msg.trim().toUpperCase().equals("END")){
					done = true;
					threadList.remove(this);
				}
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
