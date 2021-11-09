import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

import javax.sound.sampled.SourceDataLine;

public class Texttry {
  String sourceFileName;
  static int maxNoOfWords;
  // int count;

  public Texttry(String sourceFileName, int maxNoOfWords) {
    this.sourceFileName = sourceFileName;
    this.maxNoOfWords = maxNoOfWords;

    // this.count = count;

  }

  public static void main(String[] args) {
    Scanner fileInput = new Scanner("text17_00.txt");
    String sourceFileName = fileInput.next();
    // Her laver vi en instans af klassen,
    // så vi kan få adgang til ikke-statiske metoder
    Texttry tt = new Texttry(sourceFileName, maxNoOfWords);

    int count = tt.wordCount();
    System.out.println("word count = " + count);

    int withoutDup = tt.getNoOfDifferentWords();
    System.out.println("different words = " + withoutDup);

    int countRep = tt.getNoOfRepetitions();
    System.out.println("repetitions = " + countRep);

    // System.out.println("repetitions = " + e.nextElement());

    // System.out.println(prev.get(i));
  }

  // ------------------------------------------------------------------//

  public int wordCount() {
    int count = 0;
    try {
      Scanner fileInput = new Scanner(new File(sourceFileName));

      while (fileInput.hasNext()) {

        // Tag fat i næste ord
        String nextWord = fileInput.next();
        count++;
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Uglydig fil");
    }
    return count;
  }

  // ------------------------------------------------------------------//

  public int getNoOfDifferentWords() {
    // Vi laver to arrays
    // Første array er alle ord med gentagelser
    // Andet array er uden gentagelser
    List<String> withDup = new ArrayList<>();
    List<String> withoutDup = new ArrayList<>();

    // På samme måde som wordCount(), laver vi en exception
    // Vi bruger desuden samme metode til at scanne filen
    try {
      Scanner fileInput = new Scanner(new File(sourceFileName));
      // Vi tilføjer alle ord til vores array
      // og fjerner tegn, der ikke er bogstaver eller mellemrum
      while (fileInput.hasNext()) {
        withDup.add(fileInput.next().replaceAll("[^A-Za-z]", ""));
      }

      withoutDup = withDup.stream().distinct().collect(Collectors.toList());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return withoutDup.size();
  }

  public int getNoOfRepetitions() {
    List<String> allWords = new ArrayList<>();
    int countRep = 0;
    int aSize = allWords.size();
    // String current;
    // String next;
    // String prev;

    try {
      Scanner fileInput = new Scanner(new File(sourceFileName));

      // Vi tilføjer alle ord til vores array
      // og fjerner tegn, der ikke er bogstaver eller mellemrum
      while (fileInput.hasNext()) {
        allWords.add(fileInput.next().replaceAll("[^A-Za-z]", ""));

        for (int i = 0; i < aSize; i++) {
          if (allWords.get(i).equals(allWords.get(i + 1)))
            countRep++;
        }

        // if (i > 0) {
        // Forrige element i array
        // prev = allWords.get(i - 1);

        // Nuværende element i array
        // current = allWords.get(i);

        // if (i < aSize - 1) {
        // next = allWords.get(i + 1);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return countRep;
  }
}
