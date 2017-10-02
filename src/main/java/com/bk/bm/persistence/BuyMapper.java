package com.bk.bm.persistence;

import com.bk.bm.domain.Buy;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:00.
 */
@Repository
public interface BuyMapper {

    @Insert("INSERT INTO " +
            "BUY(uid, isbn10, isbn13, title, price_min, price_max, status, comment) " +
            "VALUES(#{uid}, #{isbn10}, #{isbn13}, #{title}, #{price_min}, #{price_max}, #{status}, #{comment})")
    @SelectKey(statement = "SELECT buy_id FROM BUY", keyProperty = "buy_id", resultType = int.class, before = true)
    int insertBuyBook(Buy buy);

    @Select("SELECT * FROM BUY WHERE uid=#{uid}")
    ArrayList<Buy> getAllBuy(@Param("uid") int uid);

    @Select("SELECT * FROM BUY WHERE buy_id=#{buy_id}")
    Buy getBuy(@Param("buy_id") int buy_id);

    @Update("")
    void updateBuyInfo(Buy buy);

    @Delete("DELETE FROM BUY WHERE buy_id")
    void deleteBuy(@Param("buy_id") int buy_id);
}
