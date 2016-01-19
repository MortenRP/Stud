package Workers;

import java.util.HashMap;

/**
 * Created by GameMonkey on 19-01-2016.
 * This is a naive implementation of having several languages to consider.
 */
public class ColumnNameWorker {

    private HashMap<String, String> nameMap= new HashMap<String, String>();

    //User info
    public final String firstName = "first name";
    public final String lastName  ="last name";
    public final String id = "id";
    public final String number = "number";

    //Personnel groups.
    public final String bar = "bartender";
    public final String adm = "adm";
    public final String music = "music";
    public final String light = "light";

    //Shift info
    public final String date = "date";

    public ColumnNameWorker(){
        setDanishMappings();
    }

    public String mapColumnName(String name){
        return nameMap.get(name);
    }

    private void setDanishMappings() {
        nameMap.put("fornavn", firstName);
        nameMap.put("efternavn", lastName);
        nameMap.put("lønnummer", id);
        nameMap.put("mobil", number);

        nameMap.put("personalegruppe: administration", adm);
        nameMap.put("personalegruppe: bartender", bar);
        nameMap.put("personalegruppe: musikfrivillige", music);
        nameMap.put("personalegruppe: lysafvikler/light technician", light);

        nameMap.put("løn nr.", id);
        nameMap.put("dato", date);
    }
}
