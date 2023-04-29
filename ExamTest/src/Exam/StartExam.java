package Exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Vocabulary {

        private HashMap<String, String>[] levels;
        private HashMap<String, String> allWords;
        private Random random;

    public Vocabulary() {
            levels = new HashMap[4];
            levels[0] = new HashMap<String, String>();
            levels[0].put("Study", "공부");
            levels[0].put("Exercise", "운동");
            levels[0].put("Travel", "여행");
            levels[0].put("Language", "언어");
            levels[0].put("Country", "나라");
            levels[0].put("City", "도시");
            levels[0].put("Nature", "자연");
            levels[0].put("Music", "음악");
            levels[0].put("Art", "미술");

            levels[1] = new HashMap<String, String>();
            levels[1].put("Love", "사랑");
            levels[1].put("Happy", "행복한");
            levels[1].put("Sad", "슬픈");
            levels[1].put("Music", "음악");
            levels[1].put("Beautiful", "아름다운");
            levels[1].put("Kind", "친절한");
            levels[1].put("Funny", "웃긴");
            levels[1].put("Angry", "화난");

            levels[2] = new HashMap<String, String>();
            levels[2].put("Animals", "동물");
            levels[2].put("Language", "언어");
            levels[2].put("Human body", "인체");
            levels[2].put("Mathematics", "수학");
            levels[2].put("Philosophy", "철학");
            levels[2].put("Religion", "종교");
            levels[2].put("Law", "법");

            levels[3] = new HashMap<String, String>();
            levels[3].put("Cybersecurity", "사이버 보안");
            levels[3].put("Virtual reality", "가상 현실");
            levels[3].put("Augmented reality", "증강 현실");
            levels[3].put("Nanotechnology", "나노 기술");
            levels[3].put("Bioengineering", "바이오 공학");
            levels[3].put("Genetics", "유전학");
            levels[3].put("Neuroscience", "신경 과학");
            levels[3].put("Psychology", "심리학");
            levels[3].put("Anthropology", "인류학");
            levels[3].put("Sociology", "사회");

            allWords = new HashMap<String, String>();
            for (HashMap<String, String> level : levels) {
                allWords.putAll(level);
            }

            random = new Random();
        }

        public void takeTest() {
            System.out.println("그만하고싶으면 quit을 치세요\n");
            int point = 0;

            for (int i = 0; i < 10; i++) {
                HashMap<String, String> words;
                if (point < 3) {
                    words = levels[0];
                } else if (point < 6) {
                    words = levels[1];
                } else if (point < 9) {
                    words = levels[2];
                } else {
                    words = levels[3];
                }

                String problem = (String) words.keySet().toArray()[random.nextInt(words.size())];
                ArrayList<String> answerList = new ArrayList<String>();
                answerList.add(words.get(problem)); // First insert the correct answer into answerList

                // Then add two incorrect answers to answerList
                ArrayList<String> otherOptions = new ArrayList<String>(allWords.values());
                otherOptions.remove(words.get(problem));
                for (int j = 0; j < 2; j++) {
                    String otherOption = otherOptions.get(random.nextInt(otherOptions.size()));
                    answerList.add(otherOption);
                    otherOptions.remove(otherOption);
                }
                Collections.shuffle(answerList);

                System.out.println("보기: " + answerList);
                String answer = System.console().readLine(problem + "의 뜻은 무엇인가요? ");

                if (answer.toLowerCase().equals("quit")) {
                    break;
                }

                if (words.get(problem).equals(answer)) {
                    point += 1;
                    System.out.println("정답입니다. (점수: " + point + ")");
                    words.remove(problem); // Remove the problem from the word list
                } else {
                    if (point > 0) {
                        point -= 1;
                    }
                    System.out.println("오답입니다. (점수: " + point + ")");
                }
            }

            System.out.println("단어시험이 종료되었습니다.");
            if (point < 3) {
                System.out.println("축하합니다! 당신은 벌레 수준입니다! 점수는 " + point);
            } else if (point < 6) {
                System.out.println("축하합니다! 당신은 짐승 수준입니다! 점수는 " + point);
            } else if (point < 9) {
                System.out.println("축하합니다! 당신은 유아 수준입니다! 점수는 " + point);
            } else {
                System.out.println("축하합니다! 당신은 인간 수준입니다! 점수는 " + point);
            }
        }

        public static void main(String[] args) {
            Vocabulary vocab = new Vocabulary();
            vocab.takeTest();
        }
    }
//        System.out.println("풀고싶은 문제를 골라주세요.");
//        System.out.println("1. 요구사항 확인");
//        System.out.println("2. 화면 설계");
//        System.out.println("3. 데이터 입출력 구현");
//        Scanner sc = new Scanner(System.in);
//        int choice = sc.nextInt();
//        Service service = new Service(choice);
