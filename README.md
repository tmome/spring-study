## spring-study-sample application
해당 어플리케이션은 스프링으로 구성된 게시판 및 계층별 테스트 기법(Slice Test) 을 사용해 어플리케이션을 구현하기 위한 샘플 프로젝트입니다.

* port : 8080
* 어플리케이션 구동 시 In Memory 사용하도록 설정 되어있습니다.
* 해당 어플리케이션 구동 시, h2 Database 를 In Memory Mode 로 사용하도록 설정해 두었습니다.
    * 콘솔 웹 접속 주소 : [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
    * ID 및 PASSWORD 는 기본 설정 그대로 사용 가능 합니다.
    * In Memory 기반 이기에 어플리케이션이 종료 시 데이터는 초기화 됩니다.
* 본 어플리케이션 구동 시, flyway 를 통하여 DB에 Schema / init Data 가 반영될 수 있도록 설정해 두었습니다.
* 어플리케이션 구동 시 flyway 를 통해 자동으로 데이터가 반영될 수 있도록 하였습니다.

