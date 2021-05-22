package com.snailwu.mybatis.dao;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class MbgTableDynamicSqlSupport {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final MbgTable mbgTable = new MbgTable();

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Integer> id = mbgTable.id;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> dateDate = mbgTable.dateDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<Date> tsDate = mbgTable.tsDate;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<byte[]> introBlob = mbgTable.introBlob;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<byte[]> introBlob2 = mbgTable.introBlob2;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final SqlColumn<String> introText = mbgTable.introText;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public static final class MbgTable extends SqlTable {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<Date> dateDate = column("date_date", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> tsDate = column("ts_date", JDBCType.TIMESTAMP);

        public final SqlColumn<byte[]> introBlob = column("intro_blob", JDBCType.LONGVARBINARY);

        public final SqlColumn<byte[]> introBlob2 = column("intro_blob2", JDBCType.LONGVARBINARY);

        public final SqlColumn<String> introText = column("intro_text", JDBCType.LONGVARCHAR);

        public MbgTable() {
            super("mbg_table");
        }
    }
}