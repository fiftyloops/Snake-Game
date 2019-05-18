import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class GameController implements Runnable, KeyListener {
   public void keyPressed(KeyEvent e) {
      int key = e.getKeyCode();
      switch(key) {
         case VK_LEFT:
            snake.move(Direction.LEFT);
            break;
         case VK_RIGHT:
            snake.move(Direction.RIGHT);
            break;
         case VK_UP:
            snake.move(Direction.UP);
            break;
         case VK_DOWN:
            snake.move(Direction.DOWN);
            break;
      }      
   }
   
   public void run() {
      while (running) {
      }
   }
}