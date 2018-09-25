package application;

import controller.Statistics;
import util.InfoLoader.FileInfoLoader;
import util.InfoLoader.InfoLoader;

public class Application {

    public static void main(String[] args) {

        InfoLoader fileInfoLoader = new FileInfoLoader();

        Statistics statistics = new Statistics(fileInfoLoader);

        App myApp = new App(statistics);

        myApp.start();
    }

}
