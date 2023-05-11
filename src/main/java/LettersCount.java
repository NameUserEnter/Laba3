import java.io.IOException;
import java.util.*;

public class LettersCount {
    public DataReader service;

    public LettersCount(){
        this.service = new ReaderFile();
    }

    public void setService(DataReader s){
        this.service = s;
    }

    public HashSet<String> main() throws IOException {

        System.out.println("Enter filepath:");

        Scanner input = new Scanner(System.in);
        String filePath = input.nextLine();

        return FileReading(filePath);
    }
    public HashSet<String> FileReading(String path) throws IOException {

        HashSet<String> text = this.service.readWordsFromFile(path);
        HashSet<String> wordsInFile = new HashSet<>();
        wordsInFile.addAll(text);
        meetRequirement(wordsInFile);
        HashMap<String,Integer> wordsWithNumber = new HashMap<>();
        put(wordsWithNumber,wordsInFile);
        printer(wordsWithNumber);
        return wordsInFile;
    }
    public static void meetRequirement(HashSet<String> words)
    {
        for (var w : words)
        {
            w = w.length() > 30 ? w.substring(0, 30) : w;
        }
    }
    public static int numberOfLetters(String word)
    {
        int count = 0;
        for (var w : word.toCharArray())
        {
            if(!(w=='a'||w=='e'||w=='i'||w=='o'||w=='u'||w=='y'))
            {count++;}
        }
        return count;
    }
    public static void put(HashMap<String,Integer> wordsWithNumber, HashSet<String> wordsInFile)
    {
        for (var w : wordsInFile)
        {
            wordsWithNumber.put(w,numberOfLetters(w));
        }
    }
    public static void printer(HashMap<String,Integer> maps) {
        HashSet<Integer> uniqueMapsValues = new HashSet<>(maps.values());
        ArrayList<Integer> mapsValues = new ArrayList<>(uniqueMapsValues);
        Collections.sort(mapsValues);
        int i = 0;
        while (i != mapsValues.size()) {
            for (Map.Entry<String, Integer> entry : maps.entrySet()) {
                if (entry.getValue().equals(mapsValues.get(i))) {
                    System.out.println(entry.getKey());
                }
            }
            i++;
        }
    }
}
