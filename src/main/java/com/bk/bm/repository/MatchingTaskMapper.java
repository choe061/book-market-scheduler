package com.bk.bm.repository;

import com.bk.bm.domain.History;
import com.bk.bm.domain.Matching;
import com.bk.bm.domain.SaleImage;
import com.bk.bm.domain.User;
import org.apache.ibatis.annotations.Param;
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

    ArrayList<Matching> getFinishedDealOfMatching();

    void backupMatchingHistory(@Param("histories") ArrayList<History> histories);

    void backupMatchingHistoryImage(@Param("images") ArrayList<SaleImage> images);

    void deleteMatching(@Param("matchings") ArrayList<Matching> matchings);

    ArrayList<Matching> getNewMatchingBooks();

    void matchingBooks(@Param("matchingList") ArrayList<Matching> matchingList);

    ArrayList<User> getFcmTokenOfUsers(@Param("uids") ArrayList<Integer> uids);

}
