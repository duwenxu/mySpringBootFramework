package com.example.springboot.database.jpaext

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment

/**
 * 表名和行名转化类
 */
class TableStrategy : PhysicalNamingStrategyStandardImpl() {

    /**
     * 转化表名方法
     * @param name Identifier
     * @param context JdbcEnvironment?
     * @return Identifier
     */
    override fun toPhysicalTableName(name: Identifier, context: JdbcEnvironment?): Identifier {
        val sb = StringBuffer()
        for (char in name.text){
            if(sb.isNotEmpty() && char.isUpperCase()){
                sb.append('_')
            }
            sb.append(char.toUpperCase())
        }
        val tableName = sb.toString()
        return Identifier.toIdentifier(tableName)
    }

    /**
     * 转化列名方法
     * @param name Identifier
     * @param context JdbcEnvironment?
     * @return Identifier
     */
    override fun toPhysicalColumnName(name: Identifier, context: JdbcEnvironment?): Identifier {
        val sb = StringBuffer()
        for (char in name.text){
            if(sb.isNotEmpty() && char.isUpperCase()){
                sb.append('_')
            }
            sb.append(char.toUpperCase())
        }
        val columnName = sb.toString()
        return Identifier.toIdentifier(columnName)
    }

}