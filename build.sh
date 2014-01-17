#!/bin/bash

javac -cp .:/usr/share/java/tomcat7/*.jar:/usr/share/java/tomcat7/tomcat7-servlet-3.0-api-7.0.40.jar:/usr/share/java/tomcat7/catalina.jar com/redhat/valves/tomcat7/RemoteUserValve.java

jar -cvf remoteuservalve.jar com

cp remoteuservalve.jar /usr/share/java/tomcat7
