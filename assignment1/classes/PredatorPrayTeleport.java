import java.util.Random;

public class PredatorPrayTeleport {
  private int grid;
  Vector position;
  int speed;
  int moves;

  public PredatorPrayTeleport(int grid_, int speed_) {
    this.speed = speed_;
    this.grid = grid_;
    this.position = randomCoordinates(0, this.grid);
    this.moves = 0;
  }

  public void move(Vector here) {
    Vector nextPosition = this.position.add(here);
    this.position = nextPosition.clamp(0, this.grid);
    this.moves++;
  }

  private static Vector randomCoordinates(int min, int max) {
    Vector out = new Vector(2);
    for (int i = 0; i < 2; i++)
      out.set(i, new Random().nextInt(max - min) + min);
    return out;
  }

  public static void main(String[] args) {
    runSimulation(23, 2, 20);
  }

  public static void runSimulation(int n, int s, int t) {
    System.out.println("n=" + n + " s=" + s + " t=" + t);
    if (n > 0 && s >= 2 && t >= 0) {
      PredatorPrayTeleport predator = new PredatorPrayTeleport(n - 1, s);
      PredatorPrayTeleport prey = new PredatorPrayTeleport(n - 1, s);
      System.out.println(prey.position + " " + predator.position);
      while (!Vector.equal(predator.position, prey.position) && t > predator.moves) {
        boolean xDivisibleBy_s = prey.position.get(0) % s == 0;
        boolean yDivisibleBy_s = prey.position.get(1) % s == 0;
        if (xDivisibleBy_s && yDivisibleBy_s) {
          // Teleport!
          prey.move(randomCoordinates(0, n - 1));
        } else {
          prey.move(randomCoordinates(-prey.speed, prey.speed));
        }

        Vector between = Vector.subtraction(prey.position, predator.position);
        Vector maximalDistance = Vector.clamp(between, -predator.speed, predator.speed);
        predator.move(maximalDistance);

        System.out.println(prey.position + " " + predator.position);
      }
      if (Vector.equal(predator.position, prey.position)) {
        System.out.println("Catch!");
      }
    } else {
      System.out.println("Illegal Parameters!");
    }
  }

  // Custom utility class for working with int vectors
  private static class Vector {
    private final int[] array;

    public Vector(int d) {
      this.array = new int[d];
    }

    public Vector(int[] a) {
      this.array = a;
    }

    public int get(int index) {
      return this.array[index];
    }

    public void set(int index, int value) {
      this.array[index] = value;
    }

    public int dimension() {
      return this.array.length;
    }

    // overwrite toString() method to print formatted vector
    // instead of primitive / native array.toString()
    public String toString() {
      String[] collect = new String[this.array.length];
      for (int i = 0; i < this.array.length; i++) {
        collect[i] = String.valueOf(this.array[i]);
      }
      return "[" + String.join(";", collect) + "]";
    }

    public Vector add(Vector V) {
      return addition(new Vector(this.array), V);
    }

    public Vector clamp(int min, int max) {
      return clamp(new Vector(this.array), min, max);
    }

    // an operation consists of: adding two vectors with weights w1 and w2
    // finally scaling by s
    // used to build addition, subtraction and scaling
    private static Vector operation(Vector A, Vector B, int w1, int w2, int s) {
      int[] result = new int[A.dimension()];
      for (int i = 0; i < result.length; i++) {
        result[i] = (A.get(i) * w1 + B.get(i) * w2) * s;
      }
      return new Vector(result);
    }

    public static Vector addition(Vector A, Vector B) {
      return operation(A, B, 1, 1, 1);
    }

    public static Vector subtraction(Vector A, Vector B) {
      return operation(A, B, 1, -1, 1);
    }

    // branchless approach just because i can
    // 😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎
    // converting boolean values to int using
    // bit shifting 😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎
    public static Vector clamp(Vector V, int min, int max) {
      int[] clamped = new int[V.dimension()];
      for (int i = 0; i < clamped.length; i++) {
        clamped[i] = (V.get(i) * (1 & Boolean.hashCode(min < V.get(i) && V.get(i) < max) >> 1))
            + (min * (1 & Boolean.hashCode(V.get(i) <= min) >> 1))
            + (max * (1 & Boolean.hashCode(V.get(i) >= max) >> 1));
        // simpler branched version with ternary operator:
        // clamped[i] = V.get(i) > max ? max : V.get(i) < min ? min : V.get(i);
      }
      return new Vector(clamped);
    }

    public static boolean equal(Vector A, Vector B) {
      return A.get(0) == B.get(0) && A.get(1) == B.get(1);
    }
  }
}
