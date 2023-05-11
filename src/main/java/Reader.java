import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Reader {
    public static HashSet<String> main(String[] args) throws IOException {
        var s = new LettersCount();
        return(s.main());
    }
}