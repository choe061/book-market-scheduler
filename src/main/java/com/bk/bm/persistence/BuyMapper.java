package com.bk.bm.persistence;

import com.bk.bm.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:00.
 */
@Repository
public interface BuyMapper {

    int duplicateBook(@Param("uid") int uid,
                         @Param("isbn10") String isbn10,
                         @Param("isbn13") String isbn13);

    int insertBuyBook(@Param("uid") int uid, @Param("buy") Buy buy);
    void insertBuyAreas(@Param("uid") int uid,
                        @Param("buy_id") int buy_id,
                        @Param("buy_areas") ArrayList<BuyArea> buyAreas);
    void insertBuyImages(@Param("uid") int uid,
                         @Param("buy_id") int buy_id,
                         @Param("buy_images") ArrayList<BuyImage> buyImages);

    ArrayList<Buy> getAllBuy(@Param("uid") int uid);

    Buy getBuy(@Param("buy_id") int buy_id);
    ArrayList<BuyArea> getBuyAreas(@Param("buy_id") int buy_id);
    ArrayList<BuyImage> getBuyImages(@Param("buy_id") int buy_id);

    void updateBuy(@Param("buy") Buy buy);

    void deleteBuy(@Param("buy_id") int buy_id);
    void deleteBuyAreas(@Param("buy_id") int buy_id);
    void deleteBuyImages(@Param("buy_id") int buy_id);

}
