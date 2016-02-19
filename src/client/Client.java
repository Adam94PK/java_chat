package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private String login;
	
	public Client(String login){
		this.login = login;
	}
	
	public void start() throws UnknownHostException, IOException{
		Socket socket = new Socket(InetAddress.getLocalHost(), 8989);
		PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		Scanner reader = new Scanner(socket.getInputStream());
		Scanner input = new Scanner(System.in);
		
		Thread t = new Thread(new ClientReader(reader));
		t.start();
		
		//Przeslanie loginu do serwera
		writer.println(login + " : dolaczyl do czatu");
		
		boolean done = false;
		while(!done){
			String msg = input.nextLine();
			if(msg.trim().toUpperCase().equals("END"))
				done = true;
			writer.println(login + " : " + msg);
		}
		socket.close();
	}
	
	public static void main(String args[]) throws UnknownHostException, IOException{
		Scanner input = new Scanner(System.in);
		System.out.println("Podaj swoj login");
		String login = input.nextLine();
		Client client = new Client(login);
		client.start();
	}
}
