package Workers;

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

    public int getColumnPos(String column){
        return columnsByName.get(column);
    }

    public String getCellContent(int column, int row){
        return sheet.getCell(column, row).getContents();
    }

    public int getRows(){
        return sheet.getRows();
    }

    // Creates a HashMap of the columns by name and give their position.
    // Uses the sheet given to the constructor.
    private void findColumns(){
        columnsByName = new HashMap<String, Integer>();
        int columnSize = sheet.getColumns();

        //gets columns and replace danish letters
        for(int i = 0; i < columnSize; ++i){
            String columnName = sheet.getCell(i, 0)
                    .getContents()
                    .toLowerCase()
                    .replace("ø", "oe")
                    .replace("å", "aa")
                    .replace("æ", "ae");
            columnsByName.put(columnName, i);
        }

    }
}
