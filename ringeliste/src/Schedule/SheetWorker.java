package Schedule;

import jxl.Sheet;

import java.util.HashMap;

/**
 * Created by GameMonkey on 01-09-2015.
 */
public class SheetWorker {

    private Sheet sheet;
    private HashMap<String,Integer> columnsByName;


    public SheetWorker(Sheet sheet){
        this.sheet = sheet;

        findColumns();
    }

    public int getColumnsPos(String column){
        return columnsByName.get(column);
    }

    // Creates a HashMap of the columns by name and give their position.
    // Uses the sheet given to the constructor.
    private void findColumns(){
        columnsByName = new HashMap<String, Integer>();
        int columnSize = sheet.getColumns();

        for(int i = 0; i < columnSize; ++i){
            columnsByName.put(sheet.getCell(i, 0).getContents(), i);
        }

    }
}
