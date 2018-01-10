package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import Controler.Routeur;

public final class TaskOtron3000 implements Runnable {
    private static final String QUIT = "quit";

    private Routeur routeur = new Routeur(this);
    private final BufferedReader in;
    private final PrintWriter out;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskOtron3000(in, out).run();
    }

    public TaskOtron3000(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            routeur.execute(command);
        }
    }

	public void actualiser(String msg) {
		out.print(msg + "\n");
        out.flush();
	}
}
