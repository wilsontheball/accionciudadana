package ar.com.thinksoft.ac.wilsond;

import org.mortbay.jetty.Server;

public class Start {

	public static void main(String[] args) throws Exception {
		Server server = new Server(6060);
		server.setHandler(new RestHandler());
		server.start();
		server.join();
	}
}
