package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Compilation extends Release implements Serializable
{

    ArrayList<Artist> albumParticipants = new ArrayList<>();

    public Compilation() {
    }

    public Compilation(String title, String language, Date releaseDate, String format, String id, boolean status, int trackCount, ArrayList<Media> mediaArrayList, String artistName) {
        super(title, language, releaseDate, format, id, status, trackCount, mediaArrayList, artistName);
    }

   

    

}
