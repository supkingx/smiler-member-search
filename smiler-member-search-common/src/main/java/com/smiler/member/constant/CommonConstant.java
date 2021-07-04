package com.smiler.member.constant;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/5/16
 */
public class CommonConstant {

    public static final String PRIMARY_DATA_SOURCE_NAME = "primaryDataSource";
    public static final String PRIMARY_SQL_SESSION_FACTORY = "primarySqlSessionFactory";
    public static final String PRIMARY_DATA_SOURCE_TRANSACTION_MANAGER = "primaryDataSourceTransactionManager";
    public static final String PRIMARY_DAO_PATH = "com.smiler.member.dao";

    /**
     * smiler_user sharding数据源
     */
    public static final String SHARDING_DATA_SOURCE_NAME = "shardingDataSource";
    public static final String SHARDING_SQL_SESSION_FACTORY = "shardingSqlSessionFactory";
    public static final String SHARDING_DATA_SOURCE_TRANSACTION_MANAGER = "shardingDataSourceTransactionManager";
    public static final String SHARDING_DAO_TWO_PATH = "com.smiler.member.dao2";

    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String HTTP = "http";
    public static final String QUERY_REST_HIGH_LEVEL_CLIENT = "queryRestHighLevelClient";
    public static final String INDEX_REST_HIGH_LEVEL_CLIENT = "indexRestHighLevelClient";
    public static final String QUERY_REST_CLIENT = "queryRestClient";
    public static final String INDEX_REST_CLIENT = "indexRestClient";

    public static final int QUERY_SIZE = 100;
}
