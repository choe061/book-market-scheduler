package com.bk.bm.persistence;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.BuyArea;
import com.bk.bm.domain.BuyImage;
import com.bk.bm.exception.DuplicateBookException;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:00.
 */
@Repository
public interface BuyMapper {

    @Select("SELECT buy_id FROM BUY WHERE buy_uid=#{uid} AND (isbn10=#{isbn10} OR isbn13=#{isbn13})")
    int duplicateBuyBook(@Param("uid") int uid,
                         @Param("isbn10") String isbn10,
                         @Param("isbn13") String isbn13) throws BindingException;

    @Insert("INSERT INTO " +
            "BUY(buy_uid, isbn10, isbn13, title, price_min, price_max, status, comment) " +
            "VALUES(#{buy.buy_uid}, #{buy.isbn10}, #{buy.isbn13}, #{buy.title}," +
            " #{buy.price_min}, #{buy.price_max}, #{buy.comment})")
    @SelectKey(statement = "SELECT buy_id FROM BUY", keyProperty = "buy_id", resultType = int.class, before = true)
    int insertBuyBook(@Param("buy") Buy buy) throws SQLException;

    @Select("SELECT * FROM BUY WHERE buy_uid=#{uid}")
    ArrayList<Buy> getAllBuy(@Param("uid") int uid) throws SQLException;

    @Select("SELECT * FROM BUY WHERE buy_id=#{buy_id}")
    Buy getBuy(@Param("buy_id") int buy_id) throws SQLException;

    @Select("SELECT * FROM BUY_AREA WHERE buy_id=#{buy_id}")
    ArrayList<BuyArea> getBuyAreas(@Param("buy_id") int buy_id) throws SQLException;

    @Select("SELECT * FROM BUY_IMAGE WHERE buy_id=#{buy_id}")
    ArrayList<BuyImage> getBuyImages(@Param("buy_id") int buy_id) throws SQLException;

    @Update("")
    void updateBuyInfo(Buy buy) throws SQLException;

    @Delete("DELETE FROM BUY WHERE buy_id")
    void deleteBuy(@Param("buy_id") int buy_id) throws SQLException;

    @Delete("DELETE FROM BUY_AREA WHERE buy_id=#{buy_id}")
    void deleteBuyAreas(@Param("buy_id") int buy_id) throws SQLException;

    @Delete("DELETE FROM BUY_IMAGE WHERE buy_id=#{buy_id}")
    void deleteBuyImages(@Param("buy_id") int buy_id) throws SQLException;

}
