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

    public int getColumnPos(String column){
        return columnsByName.get(column);
    }

    public String getCellContent(int row, int column){
        return sheet.getCell(row, column).getContents();
    }

    public int getRows(){
        return sheet.getRows();
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
