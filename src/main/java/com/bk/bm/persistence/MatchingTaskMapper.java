package com.bk.bm.persistence;

import com.bk.bm.domain.Matching;
import com.bk.bm.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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

    @Select("SELECT s.id, b.id, s.uid, b.uid " +
            "FROM SALE AS s JOIN BUY AS b " +
            "ON (s.isbn10 = b.isbn10 OR s.isbn13 = b.isbn13) AND (s.price >= b.price_min AND s.price <= b.price_max) " +
            "LEFT JOIN MATCHING AS m " +
            "ON m.s_id = s.id AND m.b_id = b.id " +
            "WHERE m.s_id IS NULL OR m.b_id IS NULL")
    ArrayList<Matching> getNewMatchingBooks();

    @Insert({
            "<script>" +
                    "INSERT INTO MATCHING (s_id, b_id) " +
                    "VALUES " +
                    "<foreach collection='matchingList' item='matching' separator=','>" +
                        "(#{matching.s_id, jdbcType=INTEGER}, #{matching.b_id, jdbcType=INTEGER})" +
                    "</foreach>" +
            "</script>"
    })
    void matchingBooks(@Param("matchingList") ArrayList<Matching> matchingList);

    @Select({
            "<script>" +
                    "SELECT uid, fcm_token " +
                    "FROM USER " +
                    "WHERE uid IN " +
                    "<foreach collection='uids' item='uid' separator=',' open='(' close=')'>" +
                        "#{uid}" +
                    "</foreach>" +
            "</script>"
    })
    ArrayList<User> getFcmTokenOfUsers(@Param("uids") ArrayList<Integer> uids);


}
