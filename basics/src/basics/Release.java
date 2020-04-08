package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Release implements Serializable {

    private String title, language, format, id;
    private Date releaseDate;
    private boolean status;         //false = unofficial true = official
    private int trackCount;
    private ArrayList<Media> mediaArrayList;
    private String artistName;

    public Release() {

    }

    public Release(String title, String language, Date releaseDate, String format, String id, boolean status, int trackCount, ArrayList<Media> mediaArrayList, String artistName) {
        this.title = title;
        this.language = language;
        this.releaseDate = releaseDate;
        this.format = format;
        this.status = status;
        this.trackCount = trackCount;
        this.id = id;
        this.mediaArrayList = mediaArrayList;
        this.artistName = artistName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getFormat() {
        if (format == null) {
            format = "No Formats Found";
        }
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String isStatus() {
        String type;
        if (status == true) {
            type = "Official";
        } else {
            type = "Unofficial";
        }
        return type;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getMediaArrayList() {
        ArrayList<String> stringMedia = new ArrayList();
        if (mediaArrayList != null) {
            for (int i = 0; i < mediaArrayList.size(); i++) {
                stringMedia.add(mediaArrayList.get(i).getFormat());
            }
        } else {
            stringMedia.add("No Media Found");
        }
        return stringMedia;
    }

    public void setMediaArrayList(ArrayList<Media> mediaArrayList) {
        this.mediaArrayList = mediaArrayList;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public String toString() {
        return this.getTitle() + "," + this.artistName;
    }

}
