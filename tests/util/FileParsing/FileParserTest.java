package util.FileParsing;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileParserTest {
    private FileParser fileParser;

    @Before
    public void setUp() throws Exception {
        this.fileParser = new FileParser("mockMovies.dat", "::");
    }

    @Test
    public void list_10ElementsInList() {
        assertEquals(10, fileParser.getRawList().size());
    }

    @Test
    public void list_allElementsHaveValidFieldSize() {
        int fieldSize = Integer.MAX_VALUE;

        for (List<String> entry : fileParser.getRawList()) {
            fieldSize = Math.min(fieldSize, entry.size());
        }
        assertEquals(3, fieldSize);
    }


}