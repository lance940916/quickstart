package com.snailwu.mybatis.dao;

import static com.snailwu.mybatis.dao.MbgTableDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.snailwu.mybatis.model.MbgTable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface MbgTableMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, dateDate, tsDate, introBlob, introBlob2, introText);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<MbgTable> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<MbgTable> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("MbgTableResult")
    Optional<MbgTable> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="MbgTableResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="date_date", property="dateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ts_date", property="tsDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="intro_blob", property="introBlob", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="intro_blob2", property="introBlob2", jdbcType=JdbcType.LONGVARBINARY),
        @Result(column="intro_text", property="introText", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<MbgTable> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, mbgTable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, mbgTable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(MbgTable record) {
        return MyBatis3Utils.insert(this::insert, record, mbgTable, c ->
            c.map(id).toProperty("id")
            .map(dateDate).toProperty("dateDate")
            .map(tsDate).toProperty("tsDate")
            .map(introBlob).toProperty("introBlob")
            .map(introBlob2).toProperty("introBlob2")
            .map(introText).toProperty("introText")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<MbgTable> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, mbgTable, c ->
            c.map(id).toProperty("id")
            .map(dateDate).toProperty("dateDate")
            .map(tsDate).toProperty("tsDate")
            .map(introBlob).toProperty("introBlob")
            .map(introBlob2).toProperty("introBlob2")
            .map(introText).toProperty("introText")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(MbgTable record) {
        return MyBatis3Utils.insert(this::insert, record, mbgTable, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(dateDate).toPropertyWhenPresent("dateDate", record::getDateDate)
            .map(tsDate).toPropertyWhenPresent("tsDate", record::getTsDate)
            .map(introBlob).toPropertyWhenPresent("introBlob", record::getIntroBlob)
            .map(introBlob2).toPropertyWhenPresent("introBlob2", record::getIntroBlob2)
            .map(introText).toPropertyWhenPresent("introText", record::getIntroText)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MbgTable> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, mbgTable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MbgTable> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, mbgTable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<MbgTable> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, mbgTable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<MbgTable> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, mbgTable, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(MbgTable record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(dateDate).equalTo(record::getDateDate)
                .set(tsDate).equalTo(record::getTsDate)
                .set(introBlob).equalTo(record::getIntroBlob)
                .set(introBlob2).equalTo(record::getIntroBlob2)
                .set(introText).equalTo(record::getIntroText);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(MbgTable record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(dateDate).equalToWhenPresent(record::getDateDate)
                .set(tsDate).equalToWhenPresent(record::getTsDate)
                .set(introBlob).equalToWhenPresent(record::getIntroBlob)
                .set(introBlob2).equalToWhenPresent(record::getIntroBlob2)
                .set(introText).equalToWhenPresent(record::getIntroText);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(MbgTable record) {
        return update(c ->
            c.set(dateDate).equalTo(record::getDateDate)
            .set(tsDate).equalTo(record::getTsDate)
            .set(introBlob).equalTo(record::getIntroBlob)
            .set(introBlob2).equalTo(record::getIntroBlob2)
            .set(introText).equalTo(record::getIntroText)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(MbgTable record) {
        return update(c ->
            c.set(dateDate).equalToWhenPresent(record::getDateDate)
            .set(tsDate).equalToWhenPresent(record::getTsDate)
            .set(introBlob).equalToWhenPresent(record::getIntroBlob)
            .set(introBlob2).equalToWhenPresent(record::getIntroBlob2)
            .set(introText).equalToWhenPresent(record::getIntroText)
            .where(id, isEqualTo(record::getId))
        );
    }
}