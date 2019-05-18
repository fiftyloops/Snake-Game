import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

public class Game extends JFrame {
   public Game() {
      this.setTitle("Snake");
      this.add(new Board());
      
      this.setResizable(false);
      this.pack();
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setVisible(true);
   }
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            Game game = new Game();
         }
      });
   }
}