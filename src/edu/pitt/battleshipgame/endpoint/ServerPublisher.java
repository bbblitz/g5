package edu.pitt.battleshipgame.endpoint;

import javax.xml.ws.Endpoint;
import java.util.Scanner;
import edu.pitt.battleshipgame.server.ServerWrapper;

//Endpoint publisher
public class ServerPublisher {
    public static void main(String [] args) {
	while(true){
            try{
	        System.out.println("Enter address to bind to (empty for 'http://localhost:9999/battleship'):");
	        Scanner sc = new Scanner(System.in);
	        String host = sc.nextLine();
	        if(host.equals(""))
	            host = "http://localhost:9999/battleship";
                Endpoint.publish(host, new ServerWrapper());
		break;
	    }catch(Exception e){
	        System.out.println(e);
	    }
	}
    }
}
