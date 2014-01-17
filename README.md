This is a custom valve for JBossEWS 2 (Tomcat 7)

To add this valve to your installation, do the following:

Add the following to the server.xml

<Valve className="com.redhat.valves.tomcat7.RemoteUserValve" />

git clone this repo to your OpenShift node host.

Run the build.sh on a node host that has jbossews installed. Once you restart tomcat7, the valve will be invoked for each request.

You will then be able to use request.getRemoteUser() to get the value of HTTP_AUTH_USER.

curl -H 'http_auth_user: calfonso' http://valvetest-demo.example.com/login.jsp -v

In the above example, I have a simple login.jsp that prints out request.getRemoteUser()
