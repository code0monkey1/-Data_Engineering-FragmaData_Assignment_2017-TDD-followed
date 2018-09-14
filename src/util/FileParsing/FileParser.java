package util.FileParsing;

import wrappers.ListOfStringLists;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private final String fileName;
    private final String parseToken;
    private ListOfStringLists rawList;


    public FileParser(String fileName, String parseToken) {
        this.parseToken = parseToken;
        this.rawList = returnEntriesList(fileName, parseToken);
        this.fileName = fileName;

    }

    private ListOfStringLists returnEntriesList(String fileName, String parseToken) {
        ListOfStringLists tempRawEntriesList = new ListOfStringLists();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                String[] entry = line.split(parseToken);

                List<String> cleanEntriesList = returnCleanList(entry);

                tempRawEntriesList.add(cleanEntriesList);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempRawEntriesList;
    }

    private List<String> returnCleanList(String[] entry) {
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
