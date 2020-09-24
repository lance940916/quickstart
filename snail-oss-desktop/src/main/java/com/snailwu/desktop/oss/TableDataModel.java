package com.snailwu.desktop.oss;

import javax.swing.table.AbstractTableModel;

/**
 * @author 吴庆龙
 * @date 2020/8/21 11:33 上午
 */
public class TableDataModel extends AbstractTableModel {
    private String[] columnNames = {"keyName", "修改时间", "大小"};
    private String[][] columnValues = {
            {"abc.jpg", "2020-01-01 08:00:00", "4K"},
            {"def.jpg", "2020-01-01 10:00:00", "10M"},
            {"xyz.jpg", "2020-01-01 15:00:00", "3G"},
    };

    @Override
    public int getRowCount() {
        return columnValues.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return columnValues[rowIndex][columnIndex];
    }
}
