package com.bk.bm.persistence;

import com.bk.bm.domain.Buy;
import com.bk.bm.domain.BuyArea;
import com.bk.bm.domain.BuyImage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:00.
 */
@Repository
public interface BuyMapper {

    @Select("SELECT COUNT(buy_id) FROM BUY WHERE buy_uid=#{uid} AND (isbn10=#{isbn10} OR isbn13=#{isbn13})")
    int duplicateBuyBook(@Param("uid") int uid,
                         @Param("isbn10") String isbn10,
                         @Param("isbn13") String isbn13);

    @Insert("INSERT INTO " +
            "BUY(buy_uid, isbn10, isbn13, title, price_min, price_max, comment) " +
            "VALUES(#{uid}, #{buy.isbn10}, #{buy.isbn13}, #{buy.title}," +
            " #{buy.price_min}, #{buy.price_max}, #{buy.comment})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "buy_id", resultType = int.class, before = false)
    int insertBuyBook(@Param("uid") int uid, @Param("buy") Buy buy);

    @Select("SELECT * FROM BUY WHERE buy_uid=#{uid}")
    ArrayList<Buy> getAllBuy(@Param("uid") int uid);

    @Select("SELECT * FROM BUY WHERE buy_id=#{buy_id}")
    Buy getBuy(@Param("buy_id") int buy_id);

    @Select("SELECT * FROM BUY_AREA WHERE buy_id=#{buy_id}")
    ArrayList<BuyArea> getBuyAreas(@Param("buy_id") int buy_id);

    @Select("SELECT * FROM BUY_IMAGE WHERE buy_id=#{buy_id}")
    ArrayList<BuyImage> getBuyImages(@Param("buy_id") int buy_id);

    @Update("UPDATE BUY SET isbn10=#{buy.isbn10}, isbn13=#{buy.isbn13}, title=#{buy.title}, " +
            "price_min=#{buy.price_min}, price_max=#{buy.price_max}, comment=#{buy.comment} " +
            "WHERE buy_id=#{buy.buy_id}")
    void updateBuyInfo(@Param("buy") Buy buy);

    @Delete("DELETE FROM BUY WHERE buy_id")
    void deleteBuy(@Param("buy_id") int buy_id);

    @Delete("DELETE FROM BUY_AREA WHERE buy_id=#{buy_id}")
    void deleteBuyAreas(@Param("buy_id") int buy_id);

    @Delete("DELETE FROM BUY_IMAGE WHERE buy_id=#{buy_id}")
    void deleteBuyImages(@Param("buy_id") int buy_id);

}
