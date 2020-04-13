package db;

import basics.Person;
import basics.Release;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    static Connection connection = null;
    static Statement stmt = null;

    public static int id_count_person = 0;
    public static int id_count_release = 0;

    public static int count_myartist = 0;
    public static int count_myrelease = 0;

    public static void ConnectionTest() {

        try {
            //load database driver
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception : "+ex.toString());
            Logger.getLogger("Driver not found");
            System.out.println("INSIDE FIRST TRY");
        }
        try {
            
            connection = DriverManager.getConnection(
                    "*******", "******",
                    "******");
            System.out.println("Connection ok");
        } catch (SQLException ex) {
            Logger.getLogger("Server not found");
        }
    }

    public static void CreateTablePerson() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("CREATE TABLE PERSON (ID_COUNT INT,ID VARCHAR2(100) NOT NULL,OBJ_PERSON LONG RAW,PRIMARY KEY (ID))");
            System.out.println("Table Person has been Created.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table Person already exists");
        }
    }

    public static void DropTablePerson() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("DROP TABLE PERSON");
            System.out.println("Table Person has been Dropped.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table PERSON not Found");
        }
    }

    public static void CreateTableSearchName() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("CREATE TABLE SEARCHNAME (NAME VARCHAR(100),ID_COUNT INT NOT NULL,PRIMARY KEY(ID_COUNT))");
            System.out.println("Table SearchName has been Created.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table Search_name already exists");
        }
    }

    public static void DropTableSearchName() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("DROP TABLE SearchName");
            System.out.println("Table SearchName has been Dropped.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table SEARCHNAME not Found");
        }
    }

    public static void CreateTableRelease() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("CREATE TABLE RELEASE (ID_COUNT INT,ID VARCHAR2(100) NOT NULL,OBJ_RELEASE LONG RAW,PRIMARY KEY(ID))");
            System.out.println("Table Release has been Created.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table Release already exists");
        }
    }

    public static void DropTableRelease() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("DROP TABLE RELEASE");
            System.out.println("Table release has been Dropped.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table release not Found");
        }
    }

    public static void CreateTableSearchTitle() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("CREATE TABLE SEARCHTITLE(TITLE VARCHAR(100),ID_COUNT INT NOT NULL,PRIMARY KEY(ID_COUNT))");
            System.out.println("Table SearchTitle has been Created.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table SearchTitle already exists");
        }
    }

    public static void DropTableSearchTitle() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("DROP TABLE SEARCHTITLE");
            System.out.println("Table SEARCHTITLE has been Dropped.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table SEARCHTITLE not Found");
        }
    }

    public static void CreateTableMyArtists() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("CREATE TABLE MYARTISTS (MYOBJECT LONG RAW,COUNT INT NOT NULL,PRIMARY KEY (COUNT))");
            System.out.println("Table MyArtists has been Created.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table MyArtists already exists");
        }
    }

    public static void DropTableMyArtists() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("DROP TABLE MYARTISTS");
            System.out.println("Table MyArtists has been Dropped.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table MyArtists not Found");
        }
    }

    public static void CreateTableMyReleases() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("CREATE TABLE MYRELEASES (MYOBJECT LONG RAW,COUNT INT NOT NULL,PRIMARY KEY (COUNT))");
            System.out.println("Table MyReleases has been Created.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table MyReleases already exists");
        }
    }

    public static void DropTableMyReleases() throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ResultSet rs = stmt.executeQuery("DROP TABLE MYRELEASES");
            System.out.println("Table MyReleases has been Dropped.");

        } catch (java.sql.SQLSyntaxErrorException ex) {
            System.out.println("Table MyReleases not Found");
        }
    }

    public static void InsertToPerson(ArrayList<Person> ArrayListPerson) throws Exception {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //
            int counter = 0;

            PreparedStatement pstmt = connection.prepareStatement("insert into PERSON (ID_COUNT,ID, OBJ_PERSON) values (?, ?, ?)");
            for (int i = 0; i < ArrayListPerson.size(); i++) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);

                oos.writeObject(ArrayListPerson.get(i));
                oos.flush();
                byte[] PersonAsBytes = bos.toByteArray();
                //

                ByteArrayInputStream bais = new ByteArrayInputStream(PersonAsBytes);

                pstmt.setInt(1, id_count_person);
                pstmt.setString(2, ArrayListPerson.get(i).getId());
                pstmt.setBinaryStream(3, bais);
                try {
                    pstmt.executeUpdate();
                    counter++;
                } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
                    //
                }
            }
            pstmt.close();

            if (counter == 0) {
                System.out.println("Already inserted in DB");
            }

            System.out.println(counter + " artists inserted\n");

//            Statement stmt = connection.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM PERSON");
//            while (rs.next()) {
//
//                byte[] st = (byte[]) rs.getObject(3); // einai to 3 row pou kanei return
//                ByteArrayInputStream baip = new ByteArrayInputStream(st);
//                ObjectInputStream ois = new ObjectInputStream(baip);
//                Person p = (Person) ois.readObject();
//            }
            stmt.close();
//            rs.close();
            //          connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void InsertToSearchNamePerson(String name, int id) { //paramnetro

        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO SEARCHNAME (NAME,ID_COUNT) VALUES (?,?)");
            pstmt.setString(1, name);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            pstmt.close();
            id_count_person++;

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void InsertToRelease(ArrayList<Release> ArrayListRelease) throws IOException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            //
            int counter = 0;

            PreparedStatement pstmt = connection.prepareStatement("insert into RELEASE (ID_COUNT,ID, OBJ_RELEASE) values (?, ?, ?)");
            for (int i = 0; i < ArrayListRelease.size(); i++) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);

                oos.writeObject(ArrayListRelease.get(i));
                oos.flush();
                byte[] ReleaseAsBytes = bos.toByteArray();
                //

                ByteArrayInputStream bais = new ByteArrayInputStream(ReleaseAsBytes);

                pstmt.setInt(1, id_count_release);
                pstmt.setString(2, ArrayListRelease.get(i).getId());
                pstmt.setBinaryStream(3, bais);
                try {
                    pstmt.executeUpdate();
                    counter++;
                } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
                    //
                }
            }
            pstmt.close();

            if (counter == 0) {
                System.out.println("Already inserted in DB");
            }

            System.out.println(counter + " Releases inserted\n");

            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void InsertToSearchTitle(String title, int id) { //paramnetro

        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO SEARCHTITLE (TITLE,ID_COUNT) VALUES (?,?)");
            pstmt.setString(1, title);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            pstmt.close();
            id_count_release++;

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void InsertToMyArtists(Person p) throws IOException { //paramnetro

        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO MYARTISTS (MYOBJECT,COUNT) VALUES (?,?)");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(p);
            oos.flush();
            byte[] PersonAsBytes = bos.toByteArray();
            //

            ByteArrayInputStream bais = new ByteArrayInputStream(PersonAsBytes);
            pstmt.setBinaryStream(1, bais);
            pstmt.setInt(2, count_myartist);

            pstmt.executeUpdate();
            count_myartist++;
            System.out.println("Artist Inserted");
            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void InsertToMyReleases(Release r) throws IOException { //paramnetro

        try {
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO MYRELEASES (MYOBJECT,COUNT) VALUES (?,?)");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(r);
            oos.flush();
            byte[] PersonAsBytes = bos.toByteArray();
            //

            ByteArrayInputStream bais = new ByteArrayInputStream(PersonAsBytes);
            pstmt.setBinaryStream(1, bais);
            pstmt.setInt(2, count_myrelease);

            pstmt.executeUpdate();
            count_myrelease++;
            System.out.println("Release Inserted");
            pstmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static boolean ifSearchNameExists(String name) throws SQLException, IOException, ClassNotFoundException {    //Returns true if it finds the name in SEARCHNAME TABLE
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEARCHNAME");

        while (rs.next()) {

            if (name.compareToIgnoreCase(rs.getString(1)) == 0) {
                return true;
            }

        }

        stmt.close();
        rs.close();
        //   connection.close();

        return false;
    }

    public static boolean ifSearchTitleExists(String title) throws SQLException, IOException, ClassNotFoundException {    //Returns true if it finds the name in SEARCHNAME TABLE
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEARCHTITLE");

        while (rs.next()) {

            if (title.compareToIgnoreCase(rs.getString(1)) == 0) {
                return true;
            }

        }

        stmt.close();
        rs.close();
        //   connection.close();

        return false;
    }

    public static int getIdCountFromSearchName(String name) throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEARCHNAME");

        while (rs.next()) {

            if (name.compareToIgnoreCase(rs.getString(1)) == 0) {
                return rs.getInt(2);
            }

        }

        stmt.close();
        rs.close();
        //   connection.close();

        return 0;

    }

    public static int getIdCountFromSearchTitle(String title) throws SQLException {
        try {
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEARCHTITLE");

        while (rs.next()) {

            if (title.compareToIgnoreCase(rs.getString(1)) == 0) {
                return rs.getInt(2);
            }

        }

        stmt.close();
        rs.close();
        //   connection.close();

        return 0;

    }

    public static ArrayList<Person> selectFromPerson(int id_count) throws SQLException, IOException, ClassNotFoundException // epistrefei tous artist pou exoun to katallhlo ID_count
    {
        ArrayList<Person> list_artist = new ArrayList();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM PERSON where Id_COUNT  = " + id_count);

        while (rs.next()) {

            byte[] st = (byte[]) rs.getObject(3); // einai to 3 row pou kanei return
            ByteArrayInputStream baip = new ByteArrayInputStream(st);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Person p = (Person) ois.readObject();

            list_artist.add(p);
        }

        return list_artist;
    }

    public static ArrayList<Release> selectFromRelease(int id_count) throws SQLException, IOException, ClassNotFoundException // epistrefei tous artist pou exoun to katallhlo ID_count
    {
        ArrayList<Release> list_release = new ArrayList();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM RELEASE where Id_COUNT  = " + id_count);

        while (rs.next()) {

            byte[] st = (byte[]) rs.getObject(3); // einai to 3 row pou kanei return
            ByteArrayInputStream baip = new ByteArrayInputStream(st);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Release r = (Release) ois.readObject();

            list_release.add(r);
        }

        return list_release;
    }

    public static ArrayList<Person> getPersonsfromDB() throws SQLException, IOException, ClassNotFoundException // epistrefei tous artist pou exoun to katallhlo ID_count
    {
        ArrayList<Person> list_artist = new ArrayList();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT myobject FROM myartists");

        while (rs.next()) {

            byte[] st = (byte[]) rs.getObject(1); // einai to 3 row pou kanei return
            ByteArrayInputStream baip = new ByteArrayInputStream(st);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Person p = (Person) ois.readObject();

            list_artist.add(p);
        }

        return list_artist;
    }

    public static ArrayList<Release> getReleasesfromDB() throws SQLException, IOException, ClassNotFoundException // epistrefei tous artist pou exoun to katallhlo ID_count
    {
        ArrayList<Release> list_releases = new ArrayList();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT myobject FROM myreleases");

        while (rs.next()) {

            byte[] st = (byte[]) rs.getObject(1); // einai to 3 row pou kanei return
            ByteArrayInputStream baip = new ByteArrayInputStream(st);
            ObjectInputStream ois = new ObjectInputStream(baip);
            Release r = (Release) ois.readObject();

            list_releases.add(r);
        }

        return list_releases;
    }

    public static void refreshID_Count_Person() throws SQLException {
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEARCHNAME ORDER BY ID_COUNT ASC");

        while (rs.next()) {
            id_count_person = rs.getInt(2);
        }

        id_count_person += 1;

    }

    public static void refreshID_Count_Release() throws SQLException {
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM SEARCHTITLE ORDER BY ID_COUNT ASC");

        while (rs.next()) {
            id_count_release = rs.getInt(2);
        }

        id_count_release += 1;

    }

    public static void refresh_count_myartist() throws SQLException {
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM MYARTISTS ORDER BY COUNT ASC");

        while (rs.next()) {
            count_myartist = rs.getInt(2);
        }

        count_myartist += 1;

    }

    public static void refresh_count_myrelease() throws SQLException {
        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM MYRELEASES ORDER BY COUNT ASC");

        while (rs.next()) {
            count_myrelease = rs.getInt(2);
        }

        count_myrelease += 1;

    }

    public static void deleteFromMyArtitst(int count) throws SQLException {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM MYARTISTS WHERE COUNT=?");
            pstmt.setInt(1, count + 1);

            pstmt.executeUpdate();

            pstmt.close();
            refreshIDsFromMyArtists();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteFromMyReleases(int count) throws SQLException {
        try {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM MYRELEASES WHERE COUNT=?");
            pstmt.setInt(1, count + 1);

            pstmt.executeUpdate();

            pstmt.close();
            refreshIDsFromMyReleases();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void refreshIDsFromMyArtists() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("UPDATE myartists SET COUNT = ROWNUM");
    }

    public static void refreshIDsFromMyReleases() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("UPDATE myreleases SET COUNT = ROWNUM");
    }

}
