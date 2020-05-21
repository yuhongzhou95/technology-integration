package com.shadow.technology_integration.util;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 类描述：LocalDateTime -> String转换器
 *
 * @author shadow
 * @date 2020/5/12
 */
public class LocalDateTime2StringConverter implements Converter<LocalDateTime> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * 将excel对象导入成java对象
     *
     * @param cellData
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        return null;
    }

    /**
     * 将java对象到处成excel对象的字符串
     *
     * @param localDateTime
     * @param excelContentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public CellData convertToExcelData(LocalDateTime localDateTime, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        if (excelContentProperty == null || excelContentProperty.getDateTimeFormatProperty() == null) {
            // 默认的格式化格式
            return new CellData(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        // 自定义格式化格式
        return new CellData(DateTimeFormatter.ofPattern(excelContentProperty.getDateTimeFormatProperty().getFormat()).format(localDateTime));
    }
}
