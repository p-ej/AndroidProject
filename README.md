## 심뇌혈관질환 케어 어플리케이션 

1. homeTest : 
 - Firebase의 Real-Time Database를 참조한 코드
 - 메인 화면 구성 시 데이터 참조 테스트 로직을 구현하기 위한 코드

2. localserver : 
 - 스프링 프레임워크 (4.3.3) / Java 8을 사용한 Spring 백엔드
 - Spring MVC를 사용하여 3계층으로 나눈뒤 MongoTemplate을 적용하여 MongoDB Atlas에 액세스가 가능한 코드

3. testFragement : 
 - 클라이언트(Android) / Java를 사용해 화면을 구성한 코드
 - 지도, 메인 화면만을 담당 및 구현 코드 (MainActivity, MapFragement, HomeFragment)
 - HTTPClient와 AsyncTask 객체를 사용하여 Spring 백엔드와의 통신
 
 
### 구현된 코드 
 - DTO : MapsDTO, MyItem
 - Http : HTTPClient
 - MarkerImage : MarkerClusterRenderer
 - Task : TestTask
 - EventDialogFragment : 랜덤 텍스트 클릭 시 발생하는 다이얼로그 
 - HomeFragment : 메인 화면을 구성하는 코드
 - MapFragment : 지도 화면을 구성하는 코드
 - MainActivity : 바텀네비게이션을 적용한 코드 
