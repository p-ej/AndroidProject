심뇌혈관질환 케어 어플리케이션 

1. homeTest : 
 - Firebase의 Real-Time Database를 참조한 코드
 - 메인 화면 구성 시 데이터 참조 테스트 로직을 구현하기 위한 코드

2. localserver : 
 - 스프링 프레임워크 (4.3.3) / Java 8을 사용한 Spring 백엔드
 - Spring MVC를 사용하여 3계층으로 나눈뒤 MongoTemplate을 적용하여 MongoDB Atlas에 액세스가 가능한 코드

3. testFragement : 
 - 클라이언트(Android) / Java를 사용해 화면을 구성한 코드
 - 지도, 메인 화면을 담당하여 지도와 메인 화면을 구성하는 코드