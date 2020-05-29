package com.admin.contract.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RundomUtils {
    /**
     * 生成6位随机数
     **/
    public static int rundomSix() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    /**
     * 根据单元格的值属性来获取excel单元格的值。 日期默认返回格式自己根据需求定，这里返回yyyy-MM-dd类型和HH:mm这两种。
     *
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String result = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                // 数字类型 +日期类型
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                        SimpleDateFormat sdf = null;
                        if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                                .getBuiltinFormat("h:mm")) {
                            sdf = new SimpleDateFormat("HH:mm");
                        } else {// 日期
                            sdf = new SimpleDateFormat("yyyy-MM-dd");
                        }
                        Date date = cell.getDateCellValue();
                        result = sdf.format(date);
                    } else if (cell.getCellStyle().getDataFormat() == 58) {
                        // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = org.apache.poi.ss.usermodel.DateUtil
                                .getJavaDate(value);
                        result = sdf.format(date);
                    } else {
                        DecimalFormat df = new DecimalFormat();
                        df.setGroupingUsed(false);
                        result = String.valueOf(df.format(cell
                                .getNumericCellValue()));
                    }
                    break;
                // String类型
                case HSSFCell.CELL_TYPE_STRING:
                    result = String.valueOf(cell.getStringCellValue());
                    break;
                case HSSFCell.CELL_TYPE_BLANK:
                    result = "";
                default:
                    result = "";
                    break;
            }
        }

        return result;
    }

    public static List<List<Object>> batchExportJiJin(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        boolean fl = false;
        int count = 0;
        // 获取总行数
        int rows = sheet.getPhysicalNumberOfRows();
        List<List<Object>> list = new ArrayList<List<Object>>();
        if (rows > 1) {
            for (int start = 1; start < rows; start++) {
                // 从第二行开始逐行获取
                Row row = sheet.getRow(start);
                if (row == null) {
                    continue;
                }
                List<Object> li = new ArrayList<Object>();
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    Cell cell = row.getCell(i);
                    String cellValue = getCellValue(cell);
                    li.add(cellValue);
                    /*if (i == 0) {
                        jinBo.setStudentName(cellValue);
                    }
                    if (i == 1) {
                        jinBo.setPhone(cellValue);
                    }
                    if (i == 2) {
                        jinBo.setGrade(cellValue);
                    }
                    if (i == 3) {
                        jinBo.setParentPhone(cellValue);
                    }*/
                }
                list.add(li);
            }
        }
        return list;
    }

    public static String getNowTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(new Date());
        return dateNowStr;
    }
    /**
     * @author:water
     * @Data 2019/09/06
     * @Description 生成唯一token
     */
    public static String getToken(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
