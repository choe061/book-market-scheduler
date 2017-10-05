book-market-server
===================
#### book-market-android에서 사용되는 유저 매칭 기능
* MatchingTaskService에서 30분 단위로 Scheduler가 작동하여 유저들 매칭
* 거래가 완료되면 BUY, SALE, MATCHING 테이블에서 삭제한 후 HISTORY 테이블에 저장
