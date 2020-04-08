package files;

import basics.Aliases;
import basics.Credits;
import basics.Media;
import basics.Person;
import basics.Release;
import basics.Tag;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIWrapper {

    public static ArrayList<Release> getRelease(String Release, String choice) throws IOException, JSONException, ParseException {
        ArrayList<Release> list_release = new ArrayList();

        try
        {
        JSONObject jsonObject = new JSONObject(jsonPrint(Release, choice));

        JSONArray json_release_list = jsonObject.getJSONArray("releases");

        for (int i = 0; i < json_release_list.length(); i++) {
            JSONObject json_release = json_release_list.getJSONObject(i);
            Release r1 = new Release();

            if (json_release.has("title")) // Title
            {
                r1.setTitle(json_release.getString("title"));
            }
            if (json_release.has("text-representation")) // Language
            {
                if (json_release.has("language")) {
                    r1.setLanguage(json_release.getJSONObject("text-representation").getString("language"));
                } else {
                    r1.setLanguage("");
                }
            }

            if (json_release.has("release-events")) // Release Date [if null show "not dead" lol]
            {
                JSONArray releaseJson = json_release.getJSONArray("release-events");
                JSONObject element = releaseJson.getJSONObject(0);

                try {
                    String stringDate;
                    if (element.getString("date").compareTo("") == 0) {
                        Date date2 = new SimpleDateFormat("yyyy").parse("0000");
                        r1.setReleaseDate(date2);
                    } else {
                        stringDate = element.getString("date");
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
                        r1.setReleaseDate(date1);
                    }
                } catch (java.text.ParseException e) {
                    String stringDate = element.getString("date");
                    Date date = new SimpleDateFormat("yyyy").parse(stringDate);
                    r1.setReleaseDate(date);
                }

            }

            if (json_release.has("media")) // Format
            {
                ArrayList<Media> mediaArrayList = new ArrayList();
                JSONArray mediaJson = json_release.getJSONArray("media");

                for (int j = 0; j < mediaJson.length(); j++) {
                    JSONObject elementMedia = mediaJson.getJSONObject(j);
                    Media media = new Media();
                    try {
                        media.setFormat(elementMedia.getString("format"));
                    } catch (org.json.JSONException exc) {
                        media.setFormat("no media found");
                    }
                    mediaArrayList.add(media);
                }

                r1.setMediaArrayList(mediaArrayList);
            }

            if (json_release.has("artist-credit")) {
                JSONArray creditsJson = json_release.getJSONArray("artist-credit");
                JSONObject elementCredits = creditsJson.getJSONObject(0);
                Credits cred = new Credits();

                r1.setArtistName(elementCredits.getJSONObject("artist").getString("name"));
            }
            if (json_release.has("id")) // Id
            {
                r1.setId(json_release.getString("id"));
            }

            if (json_release.has("status")) // Status
            {
                if (json_release.getString("status").compareTo("Official") == 0) {
                    r1.setStatus(true);
                } else {
                    r1.setStatus(false);
                }
            }

            if (json_release.has("track-count")) // Track-count
            {
                r1.setTrackCount(json_release.getInt("track-count"));
            }

            list_release.add(r1);
        }

        //debugging-------------------------------------------------------
//        int index = 22;
//
//        System.out.println("-List of objects: " + list_release);
//
//        System.out.println("-Object #" + index + ": " + list_release.get(index));
//        System.out.println("-Title of the object #" + index + ": " + list_release.get(index).getTitle());
//        System.out.println("-Language of the object #" + index + ": " + list_release.get(index).getLanguage());
//        System.out.println("-Release Date of the object #" + index + ": " + datetoString(list_release.get(index).getReleaseDate()));
//
//        if (list_release.get(index).getMediaArrayList() != null) {
//            for (int c = 0; c < list_release.get(index).getMediaArrayList().size(); c++) {
//                System.out.println("-Format of the object #" + (c + 1) + ": " + list_release.get(index).getMediaArrayList().get(c).getFormat());
//            }
//        }
//
//        System.out.println("-Id of the object #" + index + ": " + list_release.get(index).getId());
//
//        if (list_release.get(index).isStatus() == true) {
//            System.out.println("-Status of the object #" + index + ": " + "Official");
//        } else {
//            System.out.println("-Status of the object #" + index + ": " + "Unofficial");
//        }
//
//        System.out.println("-Tracks of the object #" + index + ": " + list_release.get(index).getTrackCount());
        // ---------------------------------------------------------------
        return list_release;
        
                }
        catch (org.json.JSONException ex)
        {
            System.out.println("API P RO B LE M ");
        }
                return list_release;

    }

    public static ArrayList<Person> getPerson(String textName, String choice) throws IOException, JSONException, ParseException {

        ArrayList<Person> list_artist = new ArrayList();

        JSONObject jsonObject = new JSONObject(jsonPrint(textName, choice));

        JSONArray json_artist_list = jsonObject.getJSONArray("artists");

        for (int i = 0; i < json_artist_list.length(); i++) {
            JSONObject json_artist = json_artist_list.getJSONObject(i);
            Person p1 = new Person();
            if (json_artist.has("type")) {
                if (json_artist.getString("type").equalsIgnoreCase("Person")) {
                    if (json_artist.has("gender")) {
                        p1.setGender(json_artist.getString("gender"));
                    }
                }
            }
            if (json_artist.has("name")) // Name
            {
                p1.setName(json_artist.getString("name"));
            }
            if (json_artist.has("area")) // Country
            {
                p1.setCountry(json_artist.getJSONObject("area").getString("name"));
            }
            if (json_artist.has("begin-area")) // Begin City
            {
                p1.setBeginCity(json_artist.getJSONObject("begin-area").getString("name"));
            }
            if (json_artist.has("end-area")) // End City
            {
                p1.setEndCity(json_artist.getJSONObject("end-area").getString("name"));
            }
            if (json_artist.has("aliases")) {
                ArrayList<Aliases> aliasesArrayList = new ArrayList();
                JSONArray aliasesJson = json_artist.getJSONArray("aliases");
                for (int n = 0; n < aliasesJson.length(); n++) {
                    JSONObject elementAliases = aliasesJson.getJSONObject(n);
                    Aliases alias = new Aliases();
                    alias.setName(elementAliases.getString("name"));
                    aliasesArrayList.add(alias);
                }
                p1.setAliases(aliasesArrayList);

            }

            if (json_artist.has("life-span")) // birthDate
            {
                if (json_artist.getJSONObject("life-span").toString().contains(",")) {
                    try {
                        String stringDate = json_artist.getJSONObject("life-span").getString("begin");
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
                        p1.setBirthDate(date1);

                    } catch (java.text.ParseException e) {
                        String stringDate = json_artist.getJSONObject("life-span").getString("begin");
                        Date date = new SimpleDateFormat("yyyy").parse(stringDate);
                        p1.setBirthDate(date);
                    }

                }

            }

            if (json_artist.has("life-span")) // deathDate
            {
                if (json_artist.getJSONObject("life-span").toString().contains("\"end\"")) {
                    try {
                        String stringDate = json_artist.getJSONObject("life-span").getString("end");
                        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
                        p1.setDeathDate(date1);

                    } catch (java.text.ParseException e) {
                        String stringDate = json_artist.getJSONObject("life-span").getString("end");
                        Date date = new SimpleDateFormat("yyyy").parse(stringDate);
                        p1.setDeathDate(date);
                    }

                }
            }

            if (json_artist.has("id")) // ID
            {
                p1.setId(json_artist.getString("id"));
            }

            if (json_artist.has("tags")) // Tags
            {
                ArrayList<Tag> tags = new ArrayList();
                JSONArray tagsJson = json_artist.getJSONArray("tags");
                for (int j = 0; j < tagsJson.length(); j++) {
                    JSONObject element = tagsJson.getJSONObject(j);
                    Tag tag = new Tag();
                    tag.setName(element.getString("name"));
                    tags.add(tag);
                }
                p1.setTags(tags);

            }

            list_artist.add(p1);

        }

        //debugging -----------------------------------------------------------------------------------------------
        int index = 1;
//        System.out.println("-List of objects: " + list_artist);
//
//        System.out.println("Gender : " + list_artist.get(index).getGender());
//
//        System.out.println("-Object #" + index + ": " + list_artist.get(index));
//
//        System.out.println("----Name of the object #" + index + ": " + list_artist.get(index).getName());
//
//        System.out.println("-Country of the object #" + index + ": " + list_artist.get(index).getCountry());
//
//        System.out.println("-Birth Date of the object #" + index + ": " + datetoString(list_artist.get(index).getBirthDate()));
//
//        System.out.println("-Death Date of the object #" + index + ": " + datetoString(list_artist.get(index).getDeathDate()));
//
//        System.out.println("-Begin City of the object #" + index + ": " + list_artist.get(index).getBeginCity());
//
//        System.out.println("-End City of the object #" + index + ": " + list_artist.get(index).getEndCity());
//
//        System.out.println("");

//        if (list_artist.get(index).getTags() != null) {
//            for (int c = 0; c < list_artist.get(index).getTags().size(); c++) {
//                System.out.println("Tag #" + (c + 1) + ": " + list_artist.get(index).getTags().get(c).getName());
//            }
//        }
//        System.out.println("");
//        if (list_artist.get(index).getAliases() != null) {
//            for (int n1 = 0; n1 < list_artist.get(index).getAliases().size(); n1++) {
//                System.out.println("Aliases names of the object #" + (n1 + 1) + ": " + list_artist.get(index).getAliases().get(n1).getName());
//            }
//
//        }
        // --------------------------------------------------------------------------------------------------------
        return list_artist;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }
    }

    public static String linkBuilder(String search, String choice) throws MalformedURLException {
        String link;
        search = search.replaceAll(" ", "+");
        URL aURL = new URL("http://musicbrainz.org/ws/2/?query=fred&fmt=json");
        String[] build = new String[20];
        build[0] = aURL.getProtocol();
        build[1] = aURL.getAuthority();
        build[2] = aURL.getPath();
        build[3] = "://";
        build[4] = search;
        build[5] = "&fmt=json";
        build[6] = "?query=";
        build[7] = "artist/";
        build[8] = "release/";
        build[9] = "album:";
        build[10] = "compilation:";
        build[11] = "%20AND%20";
        build[12] = "release-group/";

        switch (choice) {
            case "Album":
                link = build[0] + build[3] + build[1] + build[2] + build[12] + build[6] + build[9] + search + build[5];
                ;
                return link;
            case "Artist":
                link = build[0] + build[3] + build[1] + build[2] + build[7] + build[6] + search + build[5];
                return link;
            case "Compilation":
                link = build[0] + build[3] + build[1] + build[2] + build[12] + build[6] + build[10] + search + build[5];
                return link;
            case "Release":
                link = build[0] + build[3] + build[1] + build[2] + build[8] + build[6] + search + build[5];
                return link;
            default:
                link = "ERROR";
                return link;

        }

    }

    public static String jsonPrint(String search, String choice) throws MalformedURLException, IOException {

        URL oracle = new URL(APIWrapper.linkBuilder(search, choice));
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Json String: " + inputLine);
            System.out.println("");
            return inputLine;

        }
        in.close();
        return inputLine;

    }

    public static void JsonReturn(String choice) throws IOException {
        Scanner sc = new Scanner(System.in);
        switch (choice) {
            case "Artist":
                System.out.println("Which album do you want to search");
                String albumSearch = sc.nextLine();
                albumSearch = albumSearch.replaceAll(" ", "+");
                System.out.println(linkBuilder(albumSearch, choice));

                jsonPrint(albumSearch, choice);

                break;
            case "Release":
                System.out.println("Which artist do you want to search?");
                String artistSearch = sc.nextLine();
                artistSearch = artistSearch.replaceAll(" ", "+");
                System.out.println(linkBuilder(artistSearch, choice));

                jsonPrint(artistSearch, choice);
                break;
            case "Album":
                System.out.println("Which compilation do you want to search?");
                String compilationSearch = sc.nextLine();
                compilationSearch = compilationSearch.replaceAll(" ", "+");
                System.out.println(linkBuilder(compilationSearch, choice));

                jsonPrint(compilationSearch, choice);
                break;
            case "Compilation":
                System.out.println("Which release do you want to search?");
                String releaseSearch = sc.next();
                releaseSearch = releaseSearch.replaceAll(" ", "+");
                System.out.println(linkBuilder(releaseSearch, choice));

                jsonPrint(releaseSearch, choice);
                break;

        }
    }

    public static String datetoString(Date date) {
        String stringDate;

        if (date != null) {
            stringDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
            return stringDate;
        } else // if (date == null)
        {
            return stringDate = "not dead";
        }
    }
}
