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

    //Chief groups
    public final String barchief = "barchief";
    public final String musicchief = "musicchief";
    public final String lightchief = "lightchief";

    //Shift info
    public final String date = "date";

    public ColumnNameWorker(){
        setDanishMappings();
        setEnglishMappings();
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

        nameMap.put("personalegruppe: barhøvding/bar chief", barchief);
        nameMap.put("personalegruppe: busikhøvding/music chief", musicchief);
        nameMap.put("personalegruppe: lyshøvding", lightchief);

        nameMap.put("løn nr.", id);
        nameMap.put("dato", date);
    }

    private void setEnglishMappings(){
        nameMap.put("first name", firstName);
        nameMap.put("last name", lastName);
        nameMap.put("salary identifier", id);
        nameMap.put("mobile", number);

        nameMap.put("employee group: administration", adm);
        nameMap.put("employee group: bartender", bar);
        nameMap.put("employee group: musikfrivillige", music);
        nameMap.put("employee group: lysafvikler/light technician", light);

        nameMap.put("employee group: barhøvding/bar chief", barchief);
        nameMap.put("employee group: musikhøvding/music chief", musicchief);
        nameMap.put("employee group: lyshøvding", lightchief);

        //nameMap.put("salary identifier", id); //Already included
        nameMap.put("date", date);
    }
}
