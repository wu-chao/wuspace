package com.github.wuchao.webproject.controller;

import com.github.wuchao.webproject.service.DatabaseTableSchemeService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class DatabaseTableSchemeExportController {

    @Autowired
    private DatabaseTableSchemeService databaseTableSchemeService;

    /**
     * 导出数据库表结构
     *
     * @param title
     * @param tableNames
     * @param response
     * @return
     */
    @GetMapping("/export/databaseTableSchemes")
    public ResponseEntity exportDamReportRelatedTables(@RequestParam("title") String title,
                                                       @RequestParam("tableNames") String tableNames,
                                                       HttpServletResponse response) throws IOException, SQLException {

        if (StringUtils.isNotBlank(tableNames)) {
            String[] tableNameList = tableNames.split(",");
            if (ArrayUtils.isNotEmpty(tableNameList)) {
                databaseTableSchemeService.exportDatabaseTableSchemes(title, tableNameList, response);
            }
        }

        return ResponseEntity.ok().build();
    }

}
