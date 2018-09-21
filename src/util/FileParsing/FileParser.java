package util.FileParsing;

import wrappers.RawEntries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {
    private final String fileName;
    private final String parseToken;
    private RawEntries rawList;


    public FileParser(String fileName, String parseToken) {
        this.parseToken = parseToken;
        this.fileName = fileName;
        this.rawList = returnRawEntries(fileName, parseToken);
    }

    private RawEntries returnRawEntries(String fileName,
                                        String parseToken) {

        RawEntries rawEntries = new RawEntries();

        List<String> entries = new ArrayList<>();

        entries = returnFileEntries(fileName, entries);

        for (String rawEntry : entries) {

            addEntry(parseToken, rawEntries, rawEntry);
        }

        return rawEntries;
    }

    private List<String> returnFileEntries(String fileName,
                                           List<String> entries) {

        try {
            entries = Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {

            new IllegalArgumentException("Error Reading File : " + fileName);
        }
        return entries;
    }

    private void addEntry(String parseToken,
                          RawEntries rawEntries,
                          String rawEntry) {

        List<String> entry = Arrays.asList(rawEntry.split(parseToken));
        List<String> cleanEntry = returnCleanList(entry);

        rawEntries.add(cleanEntry);
    }

    private List<String> returnCleanList(List<String> entry) {
        List<String> entryList = new ArrayList<>();

        for (String element : entry) {
            entryList.add(element.trim());
        }
        return entryList;
    }


    public List<List<String>> getRawList() {
        return rawList.getListOfLists();
    }
}
