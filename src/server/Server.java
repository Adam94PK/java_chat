package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
	
	private ServerSocket serverSocket;
	private Socket socket;
	private ArrayBlockingQueue<String> arrayBlockingQueue;
	private CopyOnWriteArrayList<ServerThread> threadList;
	private ServerSender sender;
	
	public Server() throws IOException{
		serverSocket = new ServerSocket(8989);
		arrayBlockingQueue = new ArrayBlockingQueue<String>(10);
		threadList = new CopyOnWriteArrayList<ServerThread>();
		sender = new ServerSender(arrayBlockingQueue, threadList);
	}
	
	public void start() throws IOException{
		
		while(true){
			socket = serverSocket.accept();
			ServerThread st = new ServerThread(socket, arrayBlockingQueue, threadList);
			Thread t = new Thread(st);
			t.start();
			threadList.add(st);
			Thread tSender = new Thread(sender);
			tSender.start();
		}
	
	}
	
	public static void main(String args[]) throws IOException{
		Server server = new Server();
		server.start();
	}
}
