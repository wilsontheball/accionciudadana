package ar.com.thinksoft.ac.wilsond;

import org.mortbay.jetty.Server;

import ar.com.thinksoft.ac.wilsond.log.LogManager;

public class Start {

	public static void main(String[] args) throws Exception {
		Server server = new Server(6060);
		LogManager.getInstance(Start.class).error("Inicio de wilsonD");
		server.setHandler(new RestHandler());
		server.start();
		server.join();
	}
}
