package com.sg.bjftviewprotect.system.controllerDate;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sg.bjftviewprotect.system.common.Result;
import com.sg.bjftviewprotect.system.entity.ColumnConfig;
import com.sg.bjftviewprotect.system.entity.Columns;
import com.sg.bjftviewprotect.system.service.ColumnConfigService;
import com.sg.bjftviewprotect.system.util.StringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangshuo
 * @since 2024/05/29 14:16:02
 */
@RestController
@Tag(name = "组件表头查询")
@RequestMapping("/columnConfig")
public class ColumnConfigController {
    @Autowired
    private ColumnConfigService columnConfigService;

    @Operation(summary = "组件表头查询")
    @GetMapping("/searchColumn")
    public Result<Map<String,Object>> searchColumn(@RequestParam("moduleId") String moduleId) {
        Map<String,Object> map = new HashMap<>();
        ColumnConfig one = columnConfigService.getOne(new LambdaQueryWrapper<ColumnConfig>().eq(ColumnConfig::getModuleId, moduleId));
        map.put("columnConfig",one);
        if (one != null && StringUtils.isNotBlank(one.getTableName())) {
            List<Columns> columns = columnConfigService.searchColumn(one.getTableName());
            Iterator<Columns> iterator = columns.iterator();
            while (iterator.hasNext()) {
                Columns column = iterator.next();
                if (StringUtils.equals(column.getProp(), "id") || StringUtils.equals(column.getProp(), "create_time")){
                    iterator.remove();
                    continue;
                }
                String camelCase = StringUtil.toCamelCase(column.getProp());
                column.setProp(camelCase);
            }
            map.put("columns",columns);
            return Result.success("查询成功",map);
        }
        return Result.fail("查询失败");
    }

}
