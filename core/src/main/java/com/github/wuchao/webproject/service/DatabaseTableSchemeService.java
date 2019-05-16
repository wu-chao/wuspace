package com.github.wuchao.webproject.service;

import com.github.wuchao.webproject.util.plutext.Docx4jUtils;
import com.github.wuchao.webproject.util.FileUtils;
import com.github.wuchao.webproject.util.poi.POIUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseTableSchemeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询 SQL Server 数据库表的结构
     * 参考：https://blog.csdn.net/josjiang1/article/details/80558068
     *
     * @param tableName
     * @return
     */
    public List<Map<String, String>> showMssqlTableScheme(String tableName) {
        List<Map<String, String>> list = new ArrayList<>();

        if (StringUtils.isNotBlank(tableName)) {
            String sql = "SELECT " +
                    " tb.name AS tableName, " +
                    " ( " +
                    "  SELECT TOP 1 ind.is_primary_key  " +
                    "  FROM " +
                    "   sys.index_columns ic " +
                    "   LEFT JOIN sys.indexes ind ON ic.object_id = ind.object_id  " +
                    "   AND ic.index_id= ind.index_id AND ind.name LIKE 'PK_%'  " +
                    "  WHERE " +
                    "  ic.object_id = tb.object_id AND ic.column_id= col.column_id  " +
                    " ) AS isPrimaryKey, " +
                    " col.name AS columnName, " +
                    " col.max_length AS length, " +
                    " col.is_nullable AS isNullable, " +
                    " t.name as type, " +
                    " CONVERT(varchar(200), com.value) AS comment " +
                    " FROM " +
                    " sys.TABLES tb " +
                    " INNER JOIN sys.COLUMNS col ON col.object_id = tb.object_id " +
                    " LEFT JOIN sys.types t ON t.user_type_id = col.user_type_id " +
                    " LEFT JOIN sys.extended_properties com ON com.major_id = col.object_id " +
                    " AND com.minor_id = col.column_id  " +
                    " WHERE tb.NAME = '" + tableName + "'";

            list = jdbcTemplate.query(sql, (rs, rowNum) -> {
                Map<String, String> map = new HashMap<>();
                map.put("tableName", rs.getString("tableName"));
                map.put("columnName", rs.getString("columnName"));
                map.put("type", rs.getString("type"));
                map.put("length", String.valueOf(rs.getInt("length")));
                map.put("isPrimaryKey", rs.getObject("isPrimaryKey") != null && rs.getInt("isPrimaryKey") == 1 ? "是" : "否");
                map.put("isNullable", rs.getObject("isNullable") != null && rs.getInt("isNullable") == 1 ? "是" : "否");
                map.put("comment", rs.getString("comment"));
                return map;
            });

        }

        return list;
    }

    public void exportDatabaseTableScheme(String title,
                                          String tableName,
                                          String descLocation,
                                          HttpServletResponse response) throws SQLException {

        Map dataMap = new HashMap<String, Object>();
        Map parametersMap = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(tableName)) {
            parametersMap.put("tableName", tableName);
            parametersMap.put("title", title);
            List<Map<String, String>> tableSchemeList = null;

            // [How to determine database type for a given JDBC connection?](https://stackoverflow.com/questions/254213/how-to-determine-database-type-for-a-given-jdbc-connection)
            String databaseType = jdbcTemplate.getDataSource().getConnection().getMetaData().getDatabaseProductName();
            if (databaseType.contains("Microsoft SQL Server")) {
                // sql server
                tableSchemeList = showMssqlTableScheme(tableName);
            }

            if (CollectionUtils.isNotEmpty(tableSchemeList)) {
                dataMap.put("tableSchemeList", tableSchemeList);
                dataMap.put("parametersMap", parametersMap);

                if (response != null) {

                    POIUtil.exportDocument("office-templates/export-database-table-scheme.docx", dataMap,
                            title + ".docx", response);

                } else if (StringUtils.isNotBlank(descLocation)) {

                    if (StringUtils.isNotBlank(title)) {

                        POIUtil.exportDocument("office-templates/export-database-table-scheme.docx", dataMap, descLocation);

                    } else {

                        POIUtil.exportDocument("office-templates/export-database-table-schemes.docx", dataMap, descLocation);
                    }
                }
            }
        }

    }

    public void exportDatabaseTableSchemes(String title,
                                           String[] tableNames,
                                           HttpServletResponse response) throws IOException, SQLException {

        if (ArrayUtils.isNotEmpty(tableNames)) {

            String descPath = System.getProperty("user.dir") + File.separator;
            List<String> fileLocations = new ArrayList<>(tableNames.length);

            if (StringUtils.isBlank(title)) {
                title = "数据库表结构";
            }
            String descLocation = descPath + title + ".docx";

            for (int i = 0; i < tableNames.length; i++) {
                fileLocations.add(descPath + i + ".docx");

                if (i == 0) {
                    exportDatabaseTableScheme(title, tableNames[i], fileLocations.get(i), null);
                } else {
                    exportDatabaseTableScheme("", tableNames[i], fileLocations.get(i), null);
                }
            }

            Docx4jUtils.mergeDoc(fileLocations, descLocation);
            fileLocations.add(descLocation);

            POIUtil.exportDocument(descLocation, title + ".docx", response);

            FileUtils.delete(fileLocations);

        }

    }
}
