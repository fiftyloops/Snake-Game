import java.util.*;

public class Snake {
   private LinkedList<Node> body;
   private Direction dir;
   
   public Snake() {
      body = new LinkedList<>();
      body.add(new Node(Board.WIDTH / 2, Board.HEIGHT / 2));
      for (int i = 1; i < 10; i++) {
         body.add(new Node(body.getLast().getX() + Board.DOT_SIZE, body.getLast().getY()));
      }
   }
   
   public void eat(Node food) {
      body.addFirst(food);
   }
   
   public boolean canEat(Node head, Node food) {
      if (dir != null) {
         switch (dir) {
            case LEFT:
               return head.getX() - food.getX() == Board.DOT_SIZE && 
                      head.getY() == food.getY();
            case RIGHT:
               return food.getX() - head.getX() == Board.DOT_SIZE && 
                      head.getY() == food.getY();
            case UP:
               return head.getX() == food.getX() && 
                      head.getY() - food.getY() == Board.DOT_SIZE;
            case DOWN:
               return head.getX() == food.getX() && 
                      food.getY() - head.getY() == Board.DOT_SIZE;
         }
      }
      return false;
   }
   
   public void setDirection(Direction dir) {
      this.dir = dir;
   }
   
   public void move() {
      if (dir != null) {
         switch (dir) {
            case LEFT:
               body.addFirst(body.removeLast().moveTo(body.getFirst().getX() - Board.DOT_SIZE,
                                                      body.getFirst().getY()));
               break;
            case RIGHT:
               body.addFirst(body.removeLast().moveTo(body.getFirst().getX() + Board.DOT_SIZE,
                                                      body.getFirst().getY()));
               break;
            case UP:
               body.addFirst(body.removeLast().moveTo(body.getFirst().getX(),
                                                      body.getFirst().getY() - Board.DOT_SIZE));
               break;
            case DOWN:
               body.addFirst(body.removeLast().moveTo(body.getFirst().getX(),
                                                      body.getFirst().getY() + Board.DOT_SIZE));
               break;
         }
      }
   }
   
   public Node getHead() {
      return body.getFirst();
   }
   
   public LinkedList<Node> getBody() {
      return body;
   }
}