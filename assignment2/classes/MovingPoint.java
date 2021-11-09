public class MovingPoint {
  Vector position;
  double direction;
  double speed;

  public void setDirection(double d) {
    double wrapped = d % 360;
    double clamped = clamp(wrapped, 0, 360);
    this.direction = wrapped < 0 ? 360 + wrapped : clamped;
  }

  public void setSpeed(double s) {
    this.speed = clamp(s, 0, 20);
  }

  public MovingPoint() {
    this.position = new Vector(2);
    this.direction = 90;
    this.speed = 0;
  }

  public MovingPoint(double x, double y, double direction, double speed) {
    this.position = new Vector(new double[] { x, y });
    this.setDirection(direction);
    this.setSpeed(speed);
  }

  public void move(double duration) {
    double rad = Math.toRadians(this.direction);
    double[] coords = new double[] { Math.cos(rad), Math.sin(rad) };
    Vector dir = new Vector(coords);
    this.position = this.position.add(dir.scale(this.speed * duration));
  }

  public void accelerateBy(double change) {
    this.setSpeed(this.speed + change);
  }

  public void turnBy(double angle) {
    this.setDirection(this.direction + angle);
  }

  public String toString() {
    return this.position.toString() + " " + this.direction + " " + this.speed;
  }

  static double clamp(double input, int min, int max) {
    return input > max ? max : input < min ? min : input;
  }

  static class Vector {
    private final double[] array;

    public Vector(int d) {
      this.array = new double[d];
    }

    public Vector(double[] a) {
      this.array = a;
    }

    public double get(int index) {
      return this.array[index];
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

    public Vector scale(double s) {
      return scale(new Vector(this.array), s);
    }

    public static Vector addition(Vector A, Vector B) {
      double[] result = new double[A.dimension()];
      for (int i = 0; i < result.length; i++) {
        result[i] = (A.get(i) + B.get(i));
      }
      return new Vector(result);
    }

    public static Vector scale(Vector V, double s) {
      double[] result = new double[V.dimension()];
      for (int i = 0; i < result.length; i++) {
        result[i] = V.get(i) * s;
      }
      return new Vector(result);
    }
  }
}
