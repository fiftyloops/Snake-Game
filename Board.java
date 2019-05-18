import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Board extends JPanel {
   public static final int WIDTH = 400;
   public static final int HEIGHT = 300;
   public static final int DOT_SIZE = 10;
   public static final int SPEED = 150;
   
   private Random rand;
   private Snake snake;
   private Node food;
   private GameController gameController;
   private Timer timer;
   
   public Board() {
      gameController = new GameController();
      this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
      this.setBackground(Color.BLACK);
      this.setOpaque(true);
      this.setFocusable(true);      
      this.addKeyListener(gameController);
      init();
      timer = new Timer(SPEED, gameController);
      timer.start();
   }
   
   private void init() {
      // initSnake
      snake = new Snake();
      // initFood
      rand = new Random();
      food = new Node(rand.nextInt(WIDTH / DOT_SIZE) * DOT_SIZE,
                      rand.nextInt(HEIGHT / DOT_SIZE) * DOT_SIZE);
   }
   
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      draw(g);
   }

   private void draw(Graphics g) {
      g.setColor(Color.GREEN);
      for (Node node : snake.getBody()) {
         g.fillRect(node.getX(), node.getY(), DOT_SIZE, DOT_SIZE);
      }
      g.setColor(Color.RED);
      g.fillRect(food.getX(), food.getY(), DOT_SIZE, DOT_SIZE);
   }
   
   
   private class GameController implements ActionListener, KeyListener {
      public boolean running;
      
      public GameController() {
         running = true;
      }
      
      public void actionPerformed(ActionEvent e) {
         checkCollisions();
         if (running) {            
            if (snake.canEat(snake.getHead(), food)) {
               snake.eat(food);
               food = new Node(rand.nextInt(WIDTH / DOT_SIZE) * DOT_SIZE,
                               rand.nextInt(HEIGHT / DOT_SIZE) * DOT_SIZE);
            }
            snake.move();
            repaint();
         }         
      }
      
      private void checkCollisions() {
         Node head = snake.getHead();
         if (head.getX() < 0 || head.getX() > WIDTH  - DOT_SIZE) {
            running = false;
         } else if (head.getY() < 0 || head.getY() > HEIGHT - DOT_SIZE) {
            running = false;
         } else {
            LinkedList<Node> body = snake.getBody();
            for (int i = 1; i < body.size(); i++) {
               if (head.collideWith(body.get(i))) {
                  running = false;
                  break;
               }
            }
         }
         
         if (!running) {
            timer.stop();
         }
      }
      
      public void keyPressed(KeyEvent e) {
         int key = e.getKeyCode();
         switch(key) {
            case KeyEvent.VK_LEFT:
               snake.setDirection(Direction.LEFT);
               break;
            case KeyEvent.VK_RIGHT:
               snake.setDirection(Direction.RIGHT);
               break;
            case KeyEvent.VK_UP:
               snake.setDirection(Direction.UP);
               break;
            case KeyEvent.VK_DOWN:
               snake.setDirection(Direction.DOWN);
               break;
         }      
      }
      
      public void keyReleased(KeyEvent e) {
      }
      
      public void keyTyped(KeyEvent e) {
      }
   }
}