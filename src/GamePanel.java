import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDHT = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    PlayManager pm;


    public GamePanel(){
        this.setPreferredSize((new Dimension(WIDHT,HEIGHT)));
        this.setBackground(Color.black);
        this.setLayout(null);

        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        pm = new PlayManager();

    }
    public void LaunchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterwal = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null){

            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterwal;
            lastTime = currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;

            }

        }




    }
    private void update(){
        if(!KeyHandler.pausePressed && !pm.gameOver){
            pm.update();
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);

    }
}
