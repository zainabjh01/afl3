public class NumberCheck {

  public static void main(String[] args) {
    System.out.println(check("3475"));
    // -> true
    System.out.println(check("41032"));
    // -> false
  }

  public static boolean check(String number) {
    int[] transformedDigits = transform(number);
    int checksum = sum(transformedDigits);
    return checksum % 10 == 0 ? true : false;
  }

  // Transform number, e.g. 3475 -> 6455
  private static int[] transform(String number) {
    char[] digitChars = number.toCharArray();
    int[] out = new int[number.length()];

    for (int index = 0; index < digitChars.length; index++) {
      // Traverse array backwards but retain
      // positive index incrementation.
      // Calculation of the index in the array, i:
      int i = (digitChars.length - 1) - index;

      // Apply algorithm to digit
      int digit = Character.getNumericValue(digitChars[i]);
      int twice = digit * 2;
      boolean isOddIndex = index % 2 == 0 ? false : true;
      if (isOddIndex) {
        if (twice < 10) {
          out[i] = twice;
        } else {
          out[i] = (twice % 10) + 1;
        }
      } else {
        out[i] = digit;
      }
    }
    return out;
  }

  private static int sum(int[] nums) {
    int out = 0;
    for (int i = 0; i < nums.length; i++) {
      out += nums[i];
    }
    return out;
  }
}
