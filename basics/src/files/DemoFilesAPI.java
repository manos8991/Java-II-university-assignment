package files;

import db.Database;
import gui.BasicJFrame;
import java.io.IOException;
import org.json.JSONException;

public class DemoFilesAPI {

    public static void main(String[] args) throws IOException, JSONException, Exception {

        Database.ConnectionTest();          //Opens DB connection
        
//        Database.DropTableMyArtists();
//        Database.DropTableMyReleases();
//        Database.DropTablePerson();
//        Database.DropTableSearchName();
//        Database.DropTableSearchTitle();
//        Database.DropTableSearchTitle();
        
        // Checking if tables exists otherwise it creates them
        Database.CreateTablePerson();
        Database.CreateTableSearchName();
        Database.CreateTableRelease();
        Database.CreateTableSearchTitle();
        Database.CreateTableMyArtists();
        Database.CreateTableMyReleases();
        
        //Refreshing the ID-COUNT after closing the program 
        
        Database.refreshID_Count_Release();
        Database.refreshID_Count_Person();
        Database.refreshID_Count_Release();
        Database.refresh_count_myartist();
        Database.refresh_count_myrelease();

        BasicJFrame.showBasicWindow();      //Opens the GUI

    }
}
