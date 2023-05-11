import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReaderTest {

    @Mock
    DataReader mockDataService;

    @Spy
    LettersCount letters = new LettersCount();

    @InjectMocks
    LettersCount injectedWorld;

    @Before
    public void setup() throws IOException {
        letters.setService(mockDataService);
        HashSet<String> words = new HashSet<>();
        words.add("word1");
        words.add("word2");
        words.add("word3");
        words.add("word4");
        Mockito.when(mockDataService.readWordsFromFile("1")).thenReturn(words);
        Mockito.when(mockDataService.readWordsFromFile("incorrect_path")).thenThrow(new FileNotFoundException());
    }

    @Test
    public void testMaxLength() throws IOException {
        HashSet<String> expected = new HashSet<>();
        expected.add("word1");
        expected.add("word2");
        expected.add("word3");
        expected.add("word4");
        Assert.assertEquals(expected, letters.FileReading("1"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testFileNotFound() throws IOException {
        letters.FileReading("incorrect_path");
    }

    @Test
    public void testMultipleCallsToSameFile() throws IOException {
        letters.FileReading("1");
        letters.FileReading("1");
        verify(mockDataService, times(2)).readWordsFromFile("1");
    }

    @Test
    public void MaxLengthTestWithFileContainingEmptyLines() throws IOException {
        var emptyLinePath = "emptyLine.txt";
        HashSet<String> words = new HashSet<>();
        words.add("word1");
        words.add("word2");
        words.add("word3");
        words.add("word4");
        Mockito.when(mockDataService.readWordsFromFile(emptyLinePath)).thenReturn(words);
        HashSet<String> otherWords = new HashSet<>();
        otherWords.add("word1");
        otherWords.add("word2");
        otherWords.add("word3");
        otherWords.add("word4");
        var actual = injectedWorld.FileReading(emptyLinePath);
        Assert.assertEquals(otherWords, actual);
    }


}