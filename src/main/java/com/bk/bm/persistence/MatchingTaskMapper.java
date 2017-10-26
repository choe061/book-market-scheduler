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

    ArrayList<Matching> getNewMatchingBooks();

    void matchingBooks(@Param("matchingList") ArrayList<Matching> matchingList);

    ArrayList<User> getFcmTokenOfUsers(@Param("uids") ArrayList<Integer> uids);

}
