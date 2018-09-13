package wrappers;

import java.util.ArrayList;
import java.util.List;


public class ListOfStringLists {

    private List<List<String>> listOfLists;

    public ListOfStringLists() {
        this.listOfLists = new ArrayList<>();
    }

    public void add(List<String> list) {
        this.listOfLists.add(list);
    }

    public List<List<String>> getListOfLists() {

        return this.listOfLists;
    }

    public int size() {
        return this.listOfLists.size();
    }

}
