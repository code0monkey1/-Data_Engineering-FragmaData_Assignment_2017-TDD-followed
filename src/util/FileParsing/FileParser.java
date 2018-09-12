package util.FileParsing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
    List<List<String>> rawEntriesList;
    private String fileName;
    private String parseToken;

    public FileParser(String fileName, String parseToken) {
        this.rawEntriesList = returnEntriesList(fileName, parseToken);
        this.fileName = fileName;
        this.parseToken = parseToken;
    }

    public List<List<String>> returnEntriesList(String fileName, String parseToken) {
        rawEntriesList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)))) {

            String line = "";

            while ((line = bufferedReader.readLine()) != null) {

                String[] entry = line.split(parseToken);

                List<String> entryList = new ArrayList<>();

                for (String element : entry) {
                    entryList.add(element.trim());
                }
                rawEntriesList.add(entryList);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rawEntriesList;
    }


    public List<List<String>> getRawEntriesList() {
        return rawEntriesList;
    }
}
