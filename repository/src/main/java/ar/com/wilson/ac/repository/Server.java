package ar.com.wilson.ac.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	ServerSocket socket;

	public void start() {
		try {

			this.socket = new ServerSocket(5050);
			boolean closed = false;

			DataBase database = new DataBase();

			Socket client = null;
			BufferedWriter out = null;
			BufferedReader in = null;

			do {

				if (client == null || client.isClosed()) {

					client = this.socket.accept();
					out = this.createWriter(client);
					in = this.createReader(client);
					this.printWelcome(out);

				}
				this.write(out, "wilson@database: ");
				String line = this.readLine(in);

				if (line.equals("close"))
					closed = true;
				else if (line.equals("exit"))
					client.close();
				else if (line.equals("start"))
					this.startDataBase(out,database);
				else if (line.equals("stop"))
					this.stopDataBase(out, database);
				else if (line.equals("help"))
					this.printHelp(out);
				else
					this.write(out, "Command not found\n");

			} while (!closed);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startDataBase(BufferedWriter out, DataBase database) {
		database.start();
		this.write(out, "\tDataBase Started\n");
		
	}
	
	private void stopDataBase(BufferedWriter out, DataBase database) {
		database.stop();
		this.write(out, "\tDataBase Stoped\n");
		
	}

	private void printWelcome(BufferedWriter out) {
		this.write(out, "Consola de Administracion Wilson\n");
		this.write(out, "****************\n");
		this.write(out, "escribir el comando \"help\" para ayuda\n");
	}

	private void printHelp(BufferedWriter out) {
		this.write(out, "HELP\n");
		this.write(out, "--------------------\n");
		this.write(out, "close: Detiene el servidor y la base de datos\n");
		this.write(out, "start: Inicia la base de datos\n");
		this.write(out, "stop: Detiene la Base de datos\n");
		this.write(out, "exit: Cierra el socket de conexion con el servidor\n");
	}

	private void write(BufferedWriter out, String text) {
		try {
			out.write(text);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String readLine(BufferedReader in) {
		try {
			return in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	private BufferedWriter createWriter(Socket client) {
		try {
			return new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private BufferedReader createReader(Socket client) {
		try {
			return new BufferedReader(new InputStreamReader(
					client.getInputStream()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
