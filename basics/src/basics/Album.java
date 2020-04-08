package basics;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Album extends Release implements Serializable
{

    private Artist albumArtist; //Person or Group
    private String artist;

    public Album(String title, String language, Date releaseDate, String format, String id, boolean status, int trackCount, ArrayList<Media> mediaArrayList, String artistName) {
        super(title, language, releaseDate, format, id, status, trackCount, mediaArrayList, artistName);
    }

    public Album()
    {

    }

   


    

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public Artist getAlbumArtist()
    {
        return albumArtist;
    }

    public void setAlbumArtist(Artist albumArtist)
    {
        this.albumArtist = albumArtist;
    }

}
