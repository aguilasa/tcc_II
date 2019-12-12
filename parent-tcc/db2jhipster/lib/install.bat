mvn install:install-file -Dfile=ucp.jar -DgroupId=com.oracle -DartifactId=ucp -Dversion=12.2.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar
mvn install:install-file -Dfile=sqljdbc42.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc42 -Dversion=4.2 -Dpackaging=jar
pause