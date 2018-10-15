# Sample Login App

This is a backend for https://github.com/dlopes7/appd-demo-router

## Routes

POST /api/login

```javascript
{
	"user": "david",
	"password": "123"
}
```

Any user/password will be "authenticated", the response time will be betwenen 300ms and 1.3s.
If the response time is over 1s, Hystrix will open the circuit (timeout).

## To run

Without docker:

- mvn springboot:run

With docker and AppDynamics:

1. `docker build . -t appd-demo-login`
2. `docker run -d -p 8081:8081 --name=login -v /opt/java-agent:/usr/java-agent -e "JAVA_OPTS=-Dserver.port=8081 -javaagent:/usr/java-agent/javaagent.jar -Dappdynamics.agent.applicationName=Mobile -Dappdynamics.agent.tierName=login -Dappdynamics.agent.reuse.nodeName=true -Dappdynamics.agent.reuse.nodeName.prefix=login -Dappdynamics.controller.port=8090 -Dappdynamics.controller.hostName=controller_hostname -Dappdynamics.agent.accountAccessKey=controller_access_key" appd-demo-login`
