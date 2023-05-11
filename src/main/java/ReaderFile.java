import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;

public class ReaderFile implements DataReader {

    public ReaderFile(){}
    public HashSet<String> readWordsFromFile(String path) throws IOException, FileNotFoundException
    {
            Path currentRelativePath = Paths.get("").toAbsolutePath();
            try(BufferedReader reader = Files.newBufferedReader(Path.of(("%s\\words.txt".formatted(currentRelativePath.toString())))))
            {
                HashSet<String> wordsInFile = new HashSet<>();
                while(reader.ready())
                {
                    String line = reader.readLine();
                    var wordsInLine = Arrays.asList(line.split("([^a-zA-Z])"));
                    wordsInFile.addAll(wordsInLine);

                reader.close();

        }
                return wordsInFile;
        }
    }}
