package Exam.Problem;

import java.util.HashMap;
import java.util.Map;

public class Second {
    static public Map<String, String> getExam() {
        Map<String, String> exam = new HashMap<>();
        exam.put("삼국시대 중 최초의 중앙집권 국가는 무엇인가요?", "고구려");
        exam.put("고려 시대에 인물을 신분에 따라 계급화한 것을 무엇이라고 하나요?", "봉건제도");
        exam.put("조선 후기, 역사적 사실이나 문화 등을 기록한 문헌집으로서, '동국정보'와 함께 조선시대 최대의 문화사업으로 꼽히는 것은 무엇인가요?", "세종대왕실록");
        return exam;
    }
}
