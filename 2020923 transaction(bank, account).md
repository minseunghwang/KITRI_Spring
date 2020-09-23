service에 대한 scan은 root-context

controller에 대한 scan은 dispatcher-context(여기선 servlet-context.xml 인듯)



mvc : 디자인 패턴. 개발 방법론

사용효과 : 유지보수에 용이



<h3>Spring의 MVC는!

Servlet과 Controller가 별개

Servlet은 간단한 설정만 해주고 (없는거라고 본다!?) - Front Controller 라고 부르기도 한다(가공해주는 역할) 그리고 Controller로 넘어감

Controller를 최대한! 줄이고(현업에서는) 모든 작업은 Service에서 한다 [Controller : Model과 View의 정보에 대해 알고 있어야 한다] - 어디로 이동시킬지 알고 있어야 한다 + 여기서 try/catch처리 한다



Controller DAO