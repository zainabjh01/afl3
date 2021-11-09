import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextAnalysis {
  ArrayList<String> words = new ArrayList<String>();

  public TextAnalysis(String sourceFileName, int maxNoOfWords) {
    try {
      File file = new File(sourceFileName);
      Scanner reader = new Scanner(file);
      while (reader.hasNextLine()) {
        String line = reader.nextLine().toLowerCase();
        String[] split = line.split("[^a-zA-Z]+");
        ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(split));
        // Remove all empty entries from tokens
        tokens.removeAll(Collections.singleton(""));
        int nextSize = tokens.size() + this.words.size();
        if (nextSize > maxNoOfWords) {
          break;
        } else {
          // If below max words, add tokens to total words
          this.words.addAll(tokens);
        }
      }
      reader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Invalid file name");
      e.printStackTrace();
    }
  }

  public int wordCount() {
    return this.words.size();
  }

  // A set is not allowed to have duplicates.
  // Filter by adding to different collection!
  public int getNoOfDifferentWords() {
    Set<String> set = new LinkedHashSet<String>();
    set.addAll(this.words);
    return set.size();
  }

  public int getNoOfRepetitions() {
    int reps = 0;
    for (int i = 0; i < this.words.size() - 1; i++) {
      String next = this.words.get(i + 1);
      String here = this.words.get(i);
      if (here.equals(next))
        reps++;
    }
    return reps;
  }
}
