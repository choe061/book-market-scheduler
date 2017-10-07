package com.bk.bm.persistence;

import com.bk.bm.domain.Matching;
import com.bk.bm.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 9. 25. PM 4:15.
 * 1) BUY 테이블과 SALE 테이블의 조건이 일치하는 목록을 가져온다.
 *    (MATCHING 테이블에서 s_id, b_id 없는지 확인)
 * 2) 없으면 INSERT
 * 3) 모든 쿼리 완료 후 새로 등록된 레코드에 대해 유저들에게 FCM 전송
 */

@Repository
public interface MatchingTaskMapper {

    @Select("SELECT s.sale_id, b.buy_id, s.sale_uid, b.buy_uid " +
            "FROM SALE AS s JOIN BUY AS b " +
            "ON (s.isbn10 = b.isbn10 OR s.isbn13 = b.isbn13) AND (s.price >= b.price_min AND s.price <= b.price_max) " +
            "LEFT JOIN MATCHING AS m " +
            "ON m.sale_id = s.sale_id AND m.buy_id = b.buy_id " +
            "WHERE m.sale_id IS NULL OR m.buy_id IS NULL")
    @ResultType(Matching.class)
    ArrayList<Matching> getNewMatchingBooks();

    @Insert({
            "<script>" +
                    "INSERT INTO MATCHING (sale_id, buy_id) " +
                    "VALUES " +
                    "<foreach collection='matchingList' item='matching' separator=','>" +
                        "(#{matching.sale_id, jdbcType=INTEGER}, #{matching.buy_id, jdbcType=INTEGER})" +
                    "</foreach>" +
            "</script>"
    })
    void matchingBooks(@Param("matchingList") ArrayList<Matching> matchingList);

    @Select({
            "<script>" +
                    "SELECT uid, fcm_token " +
                    "FROM USER " +
                    "WHERE uid in " +
                    "<foreach collection='uids' item='uid' index='index' open='(' separator=',' close=')'>" +
                        "#{uid, jdbcType=INTEGER}" +
                    "</foreach>" +
            "</script>"
    })
    ArrayList<User> getFcmTokenOfUsers(@Param("uids") ArrayList<Integer> uids);

}
