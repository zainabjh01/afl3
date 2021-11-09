import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class TextAnalysis {
  String sourceFileName;
  static int maxNoOfWords;
  // int count;

  public TextAnalysis(String sourceFileName, int maxNoOfWords) {
    this.sourceFileName = sourceFileName;
    this.maxNoOfWords = maxNoOfWords;

    // this.count = count;

  }

  public static void main(String[] args) {
    Scanner fileInput = new Scanner("text17_01.txt");
    String sourceFileName = fileInput.next();
    // Her laver vi en instans af klassen,
    // så vi kan få adgang til ikke-statiske metoder
    TextAnalysis tt = new TextAnalysis(sourceFileName, maxNoOfWords);

    // Vi kalder vores metode, så vi kan printe svarene
    // wordCount
    int count = tt.wordCount();
    System.out.println("word count = " + count);

    // getNoOfDifferentWord
    int withoutDup = tt.getNoOfDifferentWords();
    System.out.println("different words = " + withoutDup);

    // getNoOfRepetitions
    // int countRep = tt.getNoOfRepetitions();
    // System.out.println("repetitions = " + countRep);

  }

  // ------------------------------------------------------------------//
  // Returnerer antallet af ord i filen
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
  // Returnerer antallet af forskellige ord i filen
  public int getNoOfDifferentWords() {
    // Vi laver to arrays
    // Første array er alle ord med gentagelser
    // Andet array er uden gentagelser
    List<String> withDup = new ArrayList<>();
    List<String> withoutDup = new ArrayList<>();

    // På samme måde som wordCount(), laver vi en exception med try/catch
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

  // ------------------------------------------------------------------//
  // Returnerer antallet af øjeblikkelige gentagelser
  // public int getNoOfRepetitions() {}

}
