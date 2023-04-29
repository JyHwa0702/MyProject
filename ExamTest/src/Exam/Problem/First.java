package Exam.Problem;


import java.util.HashMap;
import java.util.Map;

public class First {

     static public Map<String, String> getExam() {
        Map<String, String> exam = new HashMap<>();
        exam.put("네트워크에서 데이터의 암호화를 위해 사용되는 프로토콜은 무엇인가요?", "SSL");
        exam.put("데이터베이스에서 모든 테이블과 컬럼을 조회하는 SQL문은 무엇인가요?", "SELECT * FROM tablename");
        exam.put("HTTP 메소드 중 POST의 특징은 무엇인가요?", "요청 본문에 데이터를 담아 서버로 전송한다");
        return exam;
    }


}
