public class Vector {
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

  public Vector subtract(Vector V) {
    return subtraction(new Vector(this.array), V);
  }

  public Vector scale(int s) {
    return scale(new Vector(this.array), s);
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

  public static Vector scale(Vector V, int s) {
    Vector nullVector = new Vector(V.dimension());
    return operation(V, nullVector, 1, 1, s);
  }

  // branchless approach just because i can
  // 😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎
  // converting boolean values to int using
  // bit shifting 😎😎😎😎😎😎😎😎😎😎😎😎😎😎😎
  public static Vector clamp(Vector V, int min, int max) {
    int[] clamped = new int[V.dimension()];
    for (int i = 0; i < clamped.length; i++) {
      clamped[i] = (V.get(i) * (1 & Boolean.hashCode(min < V.get(i) && V.get(i) < max) >> 1))
          + (min * (1 & Boolean.hashCode(V.get(i) <= min) >> 1)) + (max * (1 & Boolean.hashCode(V.get(i) >= max) >> 1));
      // simpler branched version with ternary operator:
      // clamped[i] = V.get(i) > max ? max : V.get(i) < min ? min : V.get(i);
    }
    return new Vector(clamped);
  }

  public static boolean equal(Vector A, Vector B) {
    return A.get(0) == B.get(0) && A.get(1) == B.get(1);
  }

  public static void main(String[] args) {
    Vector first = new Vector(new int[] { 1, 2 });
    Vector second = new Vector(new int[] { 4, 2 });
    System.out.println(first + " " + second);
    System.out.println(Vector.addition(first, second));
    Vector added = first.add(second);
    System.out.println(added);
    Vector scaled = Vector.scale(added, 2);
    System.out.println(scaled);
    System.out.println(Vector.subtraction(scaled, added));
    System.out.println(Vector.clamp(new Vector(new int[] { 4, 8 }), -2, 2));
    System.out.println(Vector.clamp(new Vector(new int[] { -3, 8 }), -2, 2));
    System.out.println(Vector.clamp(new Vector(new int[] { -3, -1 }), -2, 2));
    System.out.println(Vector.clamp(new Vector(new int[] { 0, 0 }), -2, 2));
    System.out.println(Vector.clamp(new Vector(new int[] { -2000, 0 }), -2, 2));
    System.out.println(Vector.clamp(new Vector(new int[] { 0, 2000 }), -2, 2));
  }
}
