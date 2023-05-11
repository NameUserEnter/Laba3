import java.io.IOException;
import java.util.HashSet;
import java.io.FileNotFoundException;

public interface DataReader {
    public HashSet<String> readWordsFromFile(String filePath) throws IOException, FileNotFoundException;
}
