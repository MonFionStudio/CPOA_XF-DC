package View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import Controler.Routeur;

/**
 * La classe Task0tron3000 qui est la vue du programme.
 * N'est autre que l'affichage du programme.
 * @author Dimitri Calmels & Xavier Fernandez
 *
 */
public final class TaskOtron3000 implements Runnable {
    private static final String QUIT = "quit";
    private Routeur routeur = new Routeur(this);
    private final BufferedReader in;
    private final PrintWriter out;

    /**
     * L'execution du programme.
     * Ce qu'il ce passe lorsque l'on le lance.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskOtron3000(in, out).run();
    }

    /**
     * Le constructeur de TaskOtron3000 avec un bufferedreader et un printwriter.
     * Qui permet de lire et d'afficher ce qu'y est à l'écran.
     * @param reader
     * @param writer
     */
    public TaskOtron3000(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
    }

    /**
     * L'affichage du programme.
     */
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

    /**
     * Permet de savoir lorsque le routeur lui indique qu'il peut écrire quelque chose à l'écran.
     * @param msg
     */
	public void actualiser(String msg) {
		out.print(msg + "\n");
        out.flush();
	}
}
