public class IntervalSearch {

  public static void main(String args[]) {
    System.out.println(intervalContains(5, 11, 1));

  }

  public static boolean intervalContains(int g1, int g2, int b) {
    // Turning the integers into doubles, as it is needed later
    double i = 0;
    double number = inttodouble(b);
    double power;
    double g1new;
    double g2new;

    // Putting the smallest number as the start of the interval
    if (g2 < g1) {
      g1new = inttodouble(g2);
      g2new = inttodouble(g1);
    } else {
      g1new = inttodouble(g1);
      g2new = inttodouble(g2);
    }

    // If the smallest number in the interval is not 1, but b is, then it will have
    // to return false or else it will create a never ending loop
    if (g1new != 1.0 && number == 1) {
      return false;
    } else {
      // If b is smaller than the lower limit, the power has to be increased
      do {
        i += 1;
        power = Math.pow(number, i);
      } while (power < g1new);

      // Now if the power is smaller than the top limit it returns true, if not, false
      if (g1new <= power && power <= g2new) {
        return true;
      } else {
        return false;
      }

    }

  }

  // Just a quick system to turn integers to doubles
  public static double inttodouble(int i) {
    double d = 1.0 * i;
    return d;
  }

}
