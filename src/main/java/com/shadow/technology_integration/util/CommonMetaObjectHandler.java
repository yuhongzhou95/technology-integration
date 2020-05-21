package com.shadow.technology_integration.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 类描述：公共元数据处理器，用来给系统默认字段赋值
 *
 * @author shadow
 * @date 2020/4/21
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("更新时，开始填充系统字段！");
        // 创建时间
        this.strictInsertFill(metaObject, "createTime",
                LocalDateTime.class, LocalDateTime.now());
        // 更新时间
        this.strictInsertFill(metaObject, "updateTime",
                LocalDateTime.class, LocalDateTime.now());
        // 创建者
        this.strictInsertFill(metaObject, "creator",
                String.class, "TODO 从上下文获取当前人");
        // 操作者
        this.strictInsertFill(metaObject, "operator",
                String.class, "TODO 从上下文获取当前人");
        // 逻辑删除标志
        this.strictInsertFill(metaObject, "isDeleted",
                Integer.class, 0);
        // 版本
        this.strictInsertFill(metaObject, "version",
                Long.class, 1L);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.strictInsertFill(metaObject, "updateTime",
                LocalDateTime.class, LocalDateTime.now());
        // 操作者
        this.strictInsertFill(metaObject, "operator",
                String.class, "TODO 从上下文获取caozuoren");
    }
}
