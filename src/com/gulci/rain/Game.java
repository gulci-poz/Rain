package com.gulci.rain;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	private Thread thread;
    private JFrame frame;
    private boolean running = false;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        frame = new JFrame();
    }

    // wątek będzie powiązany z klasą gry, gra musi implementować Runnable
    // synchronized jest dla zapewnienia separacji wątku od innych wątków

    public synchronized void start() {
        // po starcie programu (klasy) jesteśmy tutaj
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
        // nastąpi wywołanie run
    }

    public synchronized void stop() {
        running = false;
        // łączymy wszystkie wątki, żeby wszystkie zostały zamknięte, a nie tylko jeden
        // zamykamy wątek
        try {
            thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // pętla gry
        while(running) {
            //System.out.println("Running...");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        // dodajemy canvas na jframe
        game.frame.add(game);
        // jframe będzie miał takie same rozmiary jak canvas
        game.frame.pack();
        // zamknięcie jframe spowoduje zamknięcie aplikacji
        // bez tego wątek będzie wisiał
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // centrowanie ramki na oknie
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();

        // gdzie wywołamy stop()? czy wykona się automatycznie po zamknięciu okna?
    }
	
}
