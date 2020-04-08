package files;

import basics.Person;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FileWrapper
{
    

    public static void savePersonList(ArrayList list_persons) throws Exception // saves the List
    {
        ObjectOutputStream outputStream = null;

        outputStream = new ObjectOutputStream(new FileOutputStream("yourFile.dat"));

        for (int i = 0; i < list_persons.size(); i++)
        {
            outputStream.writeObject(list_persons.get(i));
        }
    }

    public static void readPersonList() throws Exception // reads the List
    {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("yourFile.dat"));

        Object obj = null;
        
        System.out.println("Reading from file.dat...\n");
        try
        {
            while ((obj = inputStream.readObject()) != null)
            {
                System.out.println(((Person) obj).getBirthDate());
//                if (obj instanceof Person)
//                {
//                    System.out.println(((Person) obj).toString());
//                }
            }
            inputStream.close();
        }
        catch (EOFException exc)
        {
            // end of stream
        }
    }
}
