package com.bk.bm.persistence;

import com.bk.bm.domain.Matching;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Created by choi on 2017. 9. 25. PM 4:15.
 * 1. JOIN문만 사용 -> 흠...이미 들어간 데이터와 비교하는 서브 쿼리도 수행
 * 2. 여러 쿼리문 사용
 *      1) BUY 테이블과 SALE 테이블의 조건이 일치하는 목록을 가져온다.
 *      2) MATCHING 테이블에서 s_id, b_id 비교
 *      3) 없으면 INSERT
 *      4) 모든 쿼리 완료 후 새로 등록된 레코드에 대해 FCM 전송
 */

@Repository
public interface MatchingTaskMapper {

    @Select("")
    ArrayList<Matching> getNewMatchingBooks();

    @Select("")
    ArrayList<Matching> getMatchedBooks();

    @Insert("")
    void matchingBook();
}
