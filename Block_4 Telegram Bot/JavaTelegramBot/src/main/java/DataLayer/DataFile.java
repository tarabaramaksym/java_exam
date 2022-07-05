package DataLayer;

import java.util.Vector;

public class DataFile {
    public Vector<String> getNameFiles()
    {
        Vector<String> names = new Vector<>(1000);
        //
        names.add("data.txt");
        names.add("import.xml");
        names.add("user.json");
        //
        return names;
    }
}
