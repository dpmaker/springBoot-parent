package com.mapper.dao;

import com.mapper.entity.TUser2;
import com.mapper.entity.TUser2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUser2Mapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int countByExample(TUser2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int deleteByExample(TUser2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int insert(TUser2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int insertSelective(TUser2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    List<TUser2> selectByExample(TUser2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    TUser2 selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TUser2 record, @Param("example") TUser2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TUser2 record, @Param("example") TUser2Example example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TUser2 record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user2
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TUser2 record);
}