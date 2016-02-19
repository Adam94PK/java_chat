package server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerSender implements Runnable {
	private ArrayBlockingQueue<String> arrayBlockingQueue;
	private CopyOnWriteArrayList<ServerThread> threadList;
	
	public ServerSender(ArrayBlockingQueue<String> arrayBlockingQueue, CopyOnWriteArrayList<ServerThread> threadList) {
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
