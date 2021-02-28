package com.snailwu.quickstart.mybatis.dao;

import static com.snailwu.quickstart.mybatis.dao.VideoDataDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.snailwu.quickstart.mybatis.model.VideoData;
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
public interface VideoDataMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, videoId, createTime, videoData.videoData);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<VideoData> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<VideoData> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("VideoDataResult")
    Optional<VideoData> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="VideoDataResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="video_id", property="videoId", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="video_data", property="videoData", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<VideoData> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, videoData, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, videoData, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(Long id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(VideoData record) {
        return MyBatis3Utils.insert(this::insert, record, videoData, c ->
            c.map(id).toProperty("id")
            .map(videoId).toProperty("videoId")
            .map(createTime).toProperty("createTime")
            .map(videoData.videoData).toProperty("videoData")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<VideoData> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, videoData, c ->
            c.map(id).toProperty("id")
            .map(videoId).toProperty("videoId")
            .map(createTime).toProperty("createTime")
            .map(videoData.videoData).toProperty("videoData")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(VideoData record) {
        return MyBatis3Utils.insert(this::insert, record, videoData, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(videoId).toPropertyWhenPresent("videoId", record::getVideoId)
            .map(createTime).toPropertyWhenPresent("createTime", record::getCreateTime)
            .map(videoData.videoData).toPropertyWhenPresent("videoData", record::getVideoData)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<VideoData> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, videoData, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<VideoData> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, videoData, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<VideoData> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, videoData, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<VideoData> selectByPrimaryKey(Long id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, videoData, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(VideoData record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(videoId).equalTo(record::getVideoId)
                .set(createTime).equalTo(record::getCreateTime)
                .set(videoData.videoData).equalTo(record::getVideoData);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(VideoData record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(videoId).equalToWhenPresent(record::getVideoId)
                .set(createTime).equalToWhenPresent(record::getCreateTime)
                .set(videoData.videoData).equalToWhenPresent(record::getVideoData);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(VideoData record) {
        return update(c ->
            c.set(videoId).equalTo(record::getVideoId)
            .set(createTime).equalTo(record::getCreateTime)
            .set(videoData.videoData).equalTo(record::getVideoData)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(VideoData record) {
        return update(c ->
            c.set(videoId).equalToWhenPresent(record::getVideoId)
            .set(createTime).equalToWhenPresent(record::getCreateTime)
            .set(videoData.videoData).equalToWhenPresent(record::getVideoData)
            .where(id, isEqualTo(record::getId))
        );
    }
}