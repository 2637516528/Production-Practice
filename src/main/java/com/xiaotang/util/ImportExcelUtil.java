package com.xiaotang.util;

import cn.hutool.core.util.StrUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ImportExcelUtil {
 
    private static int totalRows = 0;// 总行数
 
    private static int totalCells = 0;// 总列数
 
    private static String errorInfo;// 错误信息
 
    /** 无参构造方法 */
    public ImportExcelUtil()
    {
    }
 
    public static int getTotalRows()
    {
        return totalRows;
    }
 
    public static int getTotalCells()
    {
        return totalCells;
    }
 
    public static String getErrorInfo()
    {
        return errorInfo;
    }
 
    /**
     *
     * 根据流读取Excel文件
     *
     *
     * @param inputStream
     * @param isExcel2003
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<List<String>> read(InputStream inputStream, boolean isExcel2003)
            throws IOException
    {
 
        List<List<String>> dataLst = null;
 
        /** 根据版本选择创建Workbook的方式 */
        Workbook wb = null;
 
        if (isExcel2003)
        {
            wb = new HSSFWorkbook(inputStream);
        }
        else
        {
            wb = new XSSFWorkbook(inputStream);
        }
        dataLst = readDate(wb);
 
        return dataLst;
    }
 
    /**
     *
     * 读取数据
     *
     * @param wb
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<List<String>> readDate(Workbook wb)
    {
 
        List<List<String>> dataLst = new ArrayList<List<String>>();
 
        /** 得到第一个shell */
        Sheet sheet = wb.getSheetAt(0);
 
        /** 得到Excel的行数 */
        totalRows = sheet.getPhysicalNumberOfRows();
 
        /** 得到Excel的列数 */
        if (totalRows >= 1 && sheet.getRow(0) != null)
        {
            totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
 
        /** 循环Excel的行 */
        for (int r = 1; r < totalRows; r++)
        {
            Row row = sheet.getRow(r);
            if (row == null)
            {
                continue;
            }
 
            List<String> rowLst = new ArrayList<String>();
 
            /** 循环Excel的列 */
            for (int c = 0; c < getTotalCells(); c++)
            {
 
                Cell cell = row.getCell(c);
                String cellValue = "";
 
                if (null != cell)
                {
                    // 以下是判断数据的类型
                    switch (cell.getCellTypeEnum())
                    {
                        case NUMERIC: // 数字
                            //如果是日期的话
                            if(cell != null && HSSFDateUtil.isCellDateFormatted(cell)){
                                Date d = cell.getDateCellValue();
                                DateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
                                String da = formater.format(d);
                                cellValue = da;
                                break;
                            }
                            cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                            break;
 
                        case STRING: // 字符串
                            cellValue = cell.getStringCellValue();
                            break;
 
                        case BOOLEAN: // Boolean
                            cellValue = cell.getBooleanCellValue() + "";
                            break;
 
                        case FORMULA: // 公式
                            cellValue = cell.getCellFormula() + "";
                            break;
 
                        case BLANK: // 空值
                            cellValue = "";
                            break;
 
                        case ERROR: // 故障
                            cellValue = "非法字符";
                            break;
 
                        default:
                            cellValue = "未知类型";
                            break;
                    }
                }
 
                rowLst.add(cellValue);
            }
 
            /** 保存第r行的第c列 */
            dataLst.add(rowLst);
        }
 
        return dataLst;
    }
 
    /**
     *
     * 根据Excel表格中的数据判断类型得到值
     *
     * @param cell
     * @return
     * @see [类、类#方法、类#成员]
     */
    /*private static String getCellValue(Cell cell)
    {
        String cellValue = "";
        if (null != cell)
        {
            // 以下是判断数据的类型
            switch (cell.getCellType())
            {
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    ;: // 数字
                    if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell))
                    {
                        Date theDate = cell.getDateCellValue();
                        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = dff.format(theDate);
                    }
                    else
                    {
                        DecimalFormat df = new DecimalFormat("0");
                        cellValue = df.format(cell.getNumericCellValue());
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    cellValue = cell.getCellFormula() + "";
                    break;
                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;
                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;
                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }*/
 
    /**
     *
     * 根据实体成员变量的类型得到成员变量的值
     *
     * @param realValue
     * @param fields
     * @param f
     * @param cellValue
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static Object getEntityMemberValue(Object realValue, Field[] fields, int f, String cellValue)
    {
        String type = fields[f].getType().getName();
        switch (type)
        {
            case "char":
            case "java.lang.Character":
            case "java.lang.String":
                realValue = cellValue;
                break;
            case "java.util.Date":
                realValue = StrUtil.isBlank(cellValue) ? null : DateUtil.strToDate(cellValue, DateUtil.YYYY_MM_DD);
                break;
            case "java.lang.Integer":
                realValue = StrUtil.isBlank(cellValue) ? null : Integer.valueOf(cellValue);
                break;
            case "int":
            case "float":
            case "double":
            case "java.lang.Double":
            case "java.lang.Float":
            case "java.lang.Long":
            case "java.lang.Short":
            case "java.math.BigDecimal":
                realValue = StrUtil.isBlank(cellValue) ? null : new BigDecimal(cellValue);
                break;
            default:
                break;
        }
        return realValue;
    }
 
    /**
     *
     * 根据路径或文件名选择Excel版本
     *
     *
     * @param filePathOrName
     * @param in
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    public static Workbook chooseWorkbook(String filePathOrName, InputStream in)
            throws IOException
    {
        /** 根据版本选择创建Workbook的方式 */
        Workbook wb = null;
        boolean isExcel2003 = ExcelVersionUtil.isExcel2003(filePathOrName);
 
        if (isExcel2003)
        {
            wb = new HSSFWorkbook(in);
        }
        else
        {
            wb = new XSSFWorkbook(in);
        }
 
        return wb;
    }
 
    static class ExcelVersionUtil
    {
 
        /**
         *
         * 是否是2003的excel，返回true是2003
         *
         *
         * @param filePath
         * @return
         * @see [类、类#方法、类#成员]
         */
        public static boolean isExcel2003(String filePath)
        {
            return filePath.matches("^.+\\.(?i)(xls)$");
 
        }
 
        /**
         *
         * 是否是2007的excel，返回true是2007
         *
         *
         * @param filePath
         * @return
         * @see [类、类#方法、类#成员]
         */
        public static boolean isExcel2007(String filePath)
        {
            return filePath.matches("^.+\\.(?i)(xlsx)$");
 
        }
 
    }
 
    public static class DateUtil
    {
 
        // ======================日期格式化常量=====================//
 
        public static final String YYYY_MM_DDHHMMSS = "yyyy-MM-dd HH:mm:ss";
 
        public static final String YYYY_MM_DD = "yyyy-MM-dd";
 
        public static final String YYYY_MM = "yyyy-MM";
 
        public static final String YYYY = "yyyy";
 
        public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
 
        public static final String YYYYMMDD = "yyyyMMdd";
 
        public static final String YYYYMM = "yyyyMM";
 
        public static final String YYYYMMDDHHMMSS_1 = "yyyy/MM/dd HH:mm:ss";
 
        public static final String YYYY_MM_DD_1 = "yyyy/MM/dd";
 
        public static final String YYYY_MM_1 = "yyyy/MM";
 
        /**
         *
         * 自定义取值，Date类型转为String类型
         *
         * @param date 日期
         * @param pattern 格式化常量
         * @return
         * @see [类、类#方法、类#成员]
         */
        public static String dateToStr(Date date, String pattern)
        {
            SimpleDateFormat format = null;
 
            if (null == date)
                return null;
            format = new SimpleDateFormat(pattern, Locale.getDefault());
 
            return format.format(date);
        }
 
        /**
         * 将字符串转换成Date类型的时间
         * <hr>
         *
         * @param s 日期类型的字符串<br>
         *            datePattern :YYYY_MM_DD<br>
         * @return java.util.Date
         */
        public static Date strToDate(String s, String pattern)
        {
            if (s == null)
            {
                return null;
            }
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try
            {
                date = sdf.parse(s);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            return date;
        }
    }
 
}