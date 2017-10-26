package com.bk.bm.persistence;

import com.bk.bm.domain.Sale;
import com.bk.bm.domain.SaleArea;
import com.bk.bm.domain.SaleImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 10. 2. PM 9:00.
 */
@Repository
public interface SaleMapper {
    int duplicateBook(@Param("uid") int uid,
                      @Param("isbn10") String isbn10,
                      @Param("isbn13") String isbn13);

    int insertSaleBook(@Param("uid") int uid, @Param("sale") Sale sale);

    ArrayList<Sale> getAllSale(@Param("uid") int uid);

    Sale getSale(@Param("sale_id") int sale_id);

    ArrayList<SaleArea> getSaleAreas(@Param("sale_id") int sale_id);

    ArrayList<SaleImage> getSaleImages(@Param("sale_id") int sale_id);

    void updateSale(@Param("sale") Sale sale);

    void updateSaleAreas(@Param("sale_areas") ArrayList<SaleArea> saleAreas);

    void updateSaleImages(@Param("sale_images") ArrayList<SaleImage> saleImages);

    void deleteSale(@Param("sale_id") int sale_id);

    void deleteSaleAreas(@Param("sale_id") int sale_id);

    void deleteSaleImages(@Param("sale_id") int sale_id);
}
