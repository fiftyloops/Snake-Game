public class Node {
   private int x;
   private int y;
   
   public Node(int x, int y) {
      this.x = x;
      this.y = y;
   }
   
   public int getX() {
      return x;
   }
   
   public int getY() {
      return y;
   }
   
   public Node moveTo(int x, int y) {
      this.x = x;
      this.y = y;
      return this;
   }
   
   public boolean collideWith(Node other) {
      return this.x == other.x && this.y == other.y;
   }
}