package Exam.Problem;


import java.util.*;


public class First {
   private static final Map<String, String> map = new HashMap<>();

     static public Map<String, String> getExam() {

        map.put("소프트웨어 생명주기 모델 종류에 대해 작성하세요 4개", "폭포수모델, 프로토타이핑 모델, 나선형 모델, 반복적 모델");
        map.put("소프트웨어 개발 방법론에 대해서 서술하세요.","소프트웨어 개발의 시작부터 시스템을 사용하지 않는 과정까지의 전 과정을 형상화한 방법론");
        map.put("전체 시스템을 기능에 따라 나누어 개발하고, 이를 통합하는 방법론은?","구조적 방법론");
        map.put("논리의 기술에 중점을 둔 도형식 표현방법은?","나씨 슈나이더만 차트");
        map.put("정보시스템 개발에 필요한 관리 절차와 작업 기법을 체계화한 방법론은?","정보공학 방법론");
        map.put("'객체'라는 기본 단위로 시스템을 분석 및 설계하는 방법론은?","객체 지향 방법론");
        map.put("컴포넌트를 조립해서 하나의 새로운 응용프로그램을 작성하는 방법론은?","컴포넌트 방법론");
        map.put("절차보다는 사람이 중심이 되어 변화에 유연하고 신속하게 적응하면서 효율적인 시스템 개발할 수 있는 신속 적응적 개량 개발 방법론은?","애자일 방법론");
        map.put("특정 제품에 적용하고 싶은 공통된 기능을 정의해 개발하는 방법론은? 임베디드 S/W작성에 유용하다.","제품계열 방법론");
        map.put("애자일 방법론의 종류는?","XP,스크럼,Lean");
        map.put("객체지향 설계 원칙(SOLID)의 종류는?","SRP,OCP,LSP,ISP,DIP");
        map.put("소프트웨어 구성요소는 확정에는 열려있고 변경에는 닫혀 있어야 한다는 원칙은?","OCP 또는 개방 폐쇄원칙");
        map.put("서브타입은 어디서나 자신의 기반 타입으로 교체할 수 있어야 한다는 법칙은? ","LSP 또는 리스코프 치환의원칙");
        map.put("하나의 클래스는 하나의 목적을 위해서 생성되며, 클래스가 제공하는 모든 서비스는 하나의 책임을 수행하는데 집중돼 있어야 한다는 원칙","SRP 또는 단일 책임의 원칙");
        map.put("한 클래스는 자신의 사용하지 않는 인터페이스는 구현하지 말아야한다는 원칙은?","ISP 또는 인터페이스 분리의 원칙");
        map.put("객체 설계시 특정 기능에 대한 인터페이스는 그 기능과 상관없는 부분이 변해도 영향을 받지않아야 한다는 원칙.","ISP 또는 인터페이스 분리의 원칙"  );
        map.put("실제 사용 관계는 바뀌지 않으며 추상을 매개로 메세지를 주고받음으로써, 관계를 최대한 느슨하게 만드는 원칙","DIP 또는 의존성 역전의 원칙");
        map.put("소프트웨어 생명주기 모델중에서 시스템 명세 단계에서 정의한 기능을 실제 수행할 수 있도록 수행 방법을 논리적으로 결정하는 단계는?","설계단계");
        map.put("소프트웨어 개발 시 각 단계를 확실히 마무리 지은 후에 다음 단계로 넘어가는 모델은 무엇인가?","폭포수모델");
        map.put("시스템 개발시 위험을 최소화하기 위해 점진적으로 완벽한 시스템으로 개발해 나가는 모델은?","나선형모델");
        map.put("구축대상을 나누어 병렬적으로 개발 후 통합하거나, 반복적으로 개발하여 점증 완성시키는 모델","반복적모델");
        map.put("작성해야 하는 프로그램에 대한 테스트를 먼저 수행하고, 이 테스트를 통과할 수 있도록 실제 프로그램의 코드를 작성한다는 원리는?","TDD");
        map.put("애자일 방법론 중에서 매일 정해진 시간, 장소에서 짧은 시간의 개발을 하는 팀을 위한 프로젝트 관리 중심 방법론은?","스크럼");
        map.put("델파이 기법에 대해서 서술하세요.","전문가의 경험적 지식을 통한 문제 해결 및 미래예측을 위한 기법이다.");
        map.put("요구 기능을 증가시키는 인자별로 가중치를 부여하고, 요인별 가중치를 합산하여 총 기능 점수를 계산하여 비용을 산정하는 방식은?","기능점수(FP)");
        map.put("소프트웨어 각 기능의 원시코드 라인 수의 낙관치,중간치,비관치를 측정하여 예측치를 구하고 이를 이용하여 비용을 산정하는 방식은? ","LoC");
        map.put("한 사람이 1개월동안 할 수 있는 일의 양을 기준으로 프로젝트 비용을 산정하는 방식은?","Man Month");
        map.put("소프트웨어 개발주기의 단계별로 요구할 인력의 분포를 가정하는 방식은?","푸트남");
        map.put("보헴이 제안한 모형으로 프로그램 규모에 따라 비용을 산정하는 방식은?","COCOMO");
        map.put("일의 순서를 계획적으로 정리하기 위한 수렴기법으로, 비관치,중간치,낙관치의 3점 추정방식을 통해 일정을 관리하는 방식은?","PERT");
        map.put("여러 작업들의 수행 순서가 얽혀있는 프로젝트의 일정을 계산하는 기법은?","주 공정법 또는 CPM");
        map.put("한 객체의 상태가 바뀌면 그 객체에 의존하는 다른 객체들에 연락이 가고 자동으로 내용이 갱신되는 방법으로 일대다의 의존성을 가지고 상호작용하는 객체 사이에서는 가능하면 느슨하게 결합하는 디자인을 사용해야하는 것은?","Observer 또는 옵저버패턴");
        map.put("디자인 패턴 유형에는 (),(),()이 있다. 무엇인가? 3가지","생성,구조,행위");
        map.put("상위 클래스에서 인터페이스만 정의하고 실제 생성은 서브 클래스가 담당하는 디자인패턴은?","팩토리 메서드, Factory Method" );
        map.put("디자인 패턴중 () 패턴은 반복적으로 사용되는 객체들의 상호 작용을 패턴화 한 것으로 클래스나 객체들이 상호 작용하는 방법,알고리즘 등과 관련된 패턴이다. ()는?","행위");
        map.put("소프트웨어 아키텍처란? 서술하셈","여러가지 소프트웨어 구성요소와 그 구성요소가 가진 특성중에서 외부에 드러나는 특성, 그리고 구성요소 간의 관계를 표현하는 시스템의 구조나 구조체이다.");
        map.put("4+1뷰에는 어떤 뷰가 있는가?","유스케이프 뷰,프로세스 뷰,논리 뷰,구현 뷰,배포 뷰");
        map.put("시스템의 비기능적인 속성으로서 자원의 효율적인 사용,병행 실행, 비동기,이벤트 처리 등을 표현한 뷰는? , 개발자,시스템 통합자 관점이다.","프로세스 뷰");
        map.put("시스템을 계층으로 구분하여 구성하는 패턴으로 서로 마주보는 두개의 계층 사이에서만 상호작용이 이루어지는 패턴은?","계층화 패턴");
        map.put("데이터 스트림을 생성하고 처리하는 시스템에서 사용 가능한 패턴이고, 서브 시스템이 입력 데이터를 받아 처리하고, 결과를 다음 서브 시스템으로 넘겨주는걸 반복하는 패턴은?","파이프 필터패턴");
        map.put("MVC 패턴에 대해서 서술하세요.","대화형 애플리케이션을 모델,뷰,콘트롤러 3개의 서브 시스템으로 구조화하는 패턴이다.");
        map.put("알고리즘 군을 정의하고 같은 알고리즘을 각각 하나의 클래스로 캡슐화한 다음, 필요할 때 서로 교환해서 사용할 수 있게 하는 패턴은이고, 행위를 클래스로 캡슐화해 동적으로 행위를 자유롭게 바꿀 수 있게 해주는 디자인패턴은?","Strategy 패턴");
        map.put("상위 클래스에는 추상 메서드를 통해 기능의 골격을 제공하고, 하위 클래스의 메서드에는 세부 처리를 구체화하는 방식으로 사용하는 디자인 패턴은?","Template Method패턴");
        map.put("기존에 생성된 클래스를 재사용할 수 있도록 중간에서 맞춰주는 역할을 하는 인터페이스를 만드는 패턴은?","Adapter 또는 어댑터 패턴");
        map.put("Template Method, State, Observer 패턴이 포함된 디자인 패턴의 종류는 무엇인가?","행위패턴");
        map.put("( ) 요구사항은 시스템이 제공하는 기능, 서비스에 대한 요구사항이다. ()안에 들어갈 것은?","기능적");
        map.put("( ) 요구사항은 시스템이 수행하는 기능 이외의 사항, 시스템 구축에 대한 제약사항에 관한 요구사항이다. ()안에 들어갈 것은?","비기능적");
        map.put("요구공학에 대해서 서술하세요","사용자의 요구가 반영된 시스템을 개발하기 위하여 사용자 요구사항에 대한 도출,분석,명세,확인 및 검증하는 구조화 활동이다.");
        map.put("요구사항 개발 단계중 소프트웨어가 해결해야 할 문제를 이해하고, 고객으로부터 제시되는 추상적 요구에 대해 관련 정보를 식별하고 수집 방법 결정, 수집된 요구사항을 구체적으로 표현하는 단계는?","요구사항 도출");
        map.put("말을 꺼내기 쉬운 분위기로 만들어, 회의 참석자들이 내놓은 아이디어들을 비판없이 수용할 수 있도록 하는 회의는?","브레인스토밍");
        map.put("전문가의 경험적 지식을 통한 문제해결 및 미래예측을 위한 방법은?","델파이 기법");
        map.put("현실에 일어나는 장면을 설정하고 여러사람이 각자가 맡은 역을 연기함으로써 요구사항을 분석하고 수집하는 방법","롤플레잉"   );
        map.put("단기간의 집중적인 노력을 통해 다양하고 전문적인 정보를 획득하고 공유하는 방법은?","워크숍");
        map.put("요구사항 명세 단계에서 사용자의 요구를 표현할 때 자연어를 기반으로 서술하는 기법은 무엇인가?","비정형 명세기법, 정형 명세기법은 수학적인 원리와 표기법");
        map.put("소프트웨어 개발 프로세스의 시작인 소프트웨어의 요구사항을 분석하고 정의하는 단계에서 작성되는 최종 산출물은 무엇인가?","요구사항 명세서");
        map.put("소프트웨어 요구,설계 원시 코드 등의 저작자 외의 다른 전문가 또는 팀이 검사하여 문제를 식별하고 문제에 대한 올바른 해결을 찾아내는 형식적인 검토기법은?","인스펙션");
        map.put("검토 자료를 회의 전에 배포해서 사전 검토한 후 짧은 시간 동안 회의를 진행하는 형태로 가장 비형식적인 검토기법은?","워크스루");
        map.put("형상통제 위원회(CCB)에 대해서 서술하세요.","형상 관리에 대한 주요 방침을 정하고 산출물을 검토하며, 단계별 의사결정을 수행하는 조직이다.");
        map.put("시스템이 인수되고 설치된 후 일어나는 모든 활동 단계는 무엇인가?","유지보수");
        map.put("짝 프로그래밍(Pair Programming)에 대하여 서술하시오. ", "개발자 둘이서 짝으로 코딩한다는 원리이다.");
        map.put("XP 12가지 가치 중 공통적인 이름 체게와 시스템 서술서를 통해 고객과 개발자 간의 의사소통을 원활하게 한다는 원리는 무엇인가.?","메타포어");
        map.put("프로그램의 기능을 바꾸지 않으면서 중복제거,단순화 등을 위해 시스템 재구성을 한다는 원리는?","리팩토링"  );
        map.put("도요타의 시스템 품질기법을 소프트웨어 개발 프로세스에 적용해서 낭비 요소를 제거하여 품질을 향상시킨다는 방법론은?","Lean 또는 린");
        map.put("소프트웨어 집약적인 시스템에서 아키텍쳐가 표현해야하는 내용 및 이들 간의 관게를 제공하는 아키텍쳐 기술 표준은 무엇인가?","소프트웨어 아키텍처 프레임워크");
        map.put("변경 용이성과 기능성에 집중,평가가 용이하여 경험이 없는 조직에서도 활용 가능한 비용 평가 모델은 무엇인가?","SAAM");
        map.put("아키텍처 품질 속성을 만족시키는지 판단 및 품질 속성들의 이해 상충관계까지 평가하는 모델은?", "ATAM");
        map.put("ATAM 바탐의 시스템 아키텍처 분석 중심으로 경제적 의사결정에 대한 요구를 충족하는 비용 평가모델은?","CBAM");
        map.put("소프트웨어 아키텍처 구성요소 간 응집도를 평가하는 모델은?","ADR");
        map.put("전체 아키텍처가 아닌 특정 분야에 대한 품질요소에 집중하는 비용 평가 모델은?","ARID");
        map.put("복잡한 인스턴스를 조립하여 만드는 구조로, 복합 객체를 생성하는 방법과 객체를 구현하는 방법을 분리함으로써 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있는 디자인 패턴은?","빌더패턴");
        map.put("사용자의 요구가 반영된 시스템을 개발하기 위하여 사용자 요구사항에 대한 도출,분석,명세,확인 및 검증을 하는 구조화된 활동은 무엇인가?","요구공학");
        map.put("이해관계자와 직접 대화를 통해 정보를 구하는 공식적,비공식적 정보 수집 방법은 무엇인가?","인터뷰");
        map.put("요구사항 명세 단계에서 사용자의 요구를 표현할 때 수학적인 원리와 표기법으로 서술하는 기법은 무엇인가?", "정형 명세기법");

        return map;
    }

}
