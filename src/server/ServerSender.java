package server;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerSender implements Runnable {
	private ArrayBlockingQueue<String> arrayBlockingQueue;
	private ArrayList<ServerThread> threadList;
	
	public ServerSender(ArrayBlockingQueue<String> arrayBlockingQueue, ArrayList<ServerThread> threadList) {
		this.arrayBlockingQueue = arrayBlockingQueue;
		this.threadList = threadList;
	}
	
	public void run() {
		
		while(true){
			try {
				String msg = arrayBlockingQueue.take();
				//System.out.println("Jestem w server sender" + msg);
				for(ServerThread st : threadList){
					st.send(msg);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
