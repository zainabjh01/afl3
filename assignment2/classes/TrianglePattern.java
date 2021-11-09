import java.util.Arrays;

public class TrianglePattern {
  private int h, n;
  private int[] initial;
  private int[][] grid;

  public static void main(String[] args) {
    int n = 44;
    int h = 20;
    int[] init = new int[] { 30, 2, 17 };
    TrianglePattern tp = new TrianglePattern(n, h, init);
    System.out.println(n);
    System.out.println(h);
    System.out.println(Arrays.toString(init));

    System.out.println(tp.getN());
    System.out.println(tp.getH());
    System.out.println(Arrays.toString(tp.getInitial()));

    for (int r = 0; r < tp.getH(); r++) {
      for (int c = 0; c < tp.getN(); c++) {
        System.out.print(tp.getValueAt(r, c));
      }
      System.out.println("");
    }

  }

  public TrianglePattern(int n, int h, int[] initial) {
    // Først initaliserer den et 2d array med alle værdier gemt i
    int[][] grid = new int[h][n];
    // Så laver den den første række baseret på initial
    for (int c = 0; c < grid[0].length; c++)
      for (int j = 0; j < initial.length; j++) {
        if (c == initial[j]) {
          grid[0][c] = 1;
        } else {
          // Her sørger den for ikke at lave dubletter eller overskride forrige værdier i
          // initial
          if (grid[0][c] == 1 || grid[0][c] == 0) {
            continue;
          } else {
            grid[0][c] = 0;
          }
        }
      }
    // Så fra række 2 går den igennem alle colonner og rækker, og giver dem værdier
    // i griddet
    for (int r = 1; r < h; r++) {
      for (int c = 0; c < n; c++) {
        // Den første og sidste værdi vil altid være 0
        if (c == 0 || c == n - 1) {
          grid[r][c] = 0;
        } else {
          // Ellers er det lavet på et trekantsmønster
          if (grid[r - 1][c - 1] == 0) {
            if (grid[r - 1][c] == 0 && grid[r - 1][c + 1] == 0) {
              grid[r][c] = 0;
            } else {
              grid[r][c] = 1;
            }
          } else {
            if (grid[r - 1][c] == 0 && grid[r - 1][c + 1] == 0) {
              grid[r][c] = 1;
            } else {
              grid[r][c] = 0;
            }
          }
        }
      }
    }
    // Til sidst gør jeg så man kan kalde alle variable
    this.n = n;
    this.h = h;
    this.initial = initial;
    this.grid = grid;
  }

  // Laver funktionerder kan give de overordnede værdier for griddet, samt de
  // individuelle værdier inde i den.
  public int getN() {
    int column = n;
    return column;
  }

  public int getH() {
    int row = h;
    return row;
  }

  public int[] getInitial() {
    int[] init;
    init = initial;
    return init;
  }

  public int getValueAt(int r, int c) {
    int value;
    value = grid[r][c];
    return value;
  }

}
