javac C:\Users\jansc\git\RechnerkommunikationUndMiddleware\Labor04_RMIClient\src\*.java
javac C:\Users\jansc\git\RechnerkommunikationUndMiddleware\Labor04_RMIServer\src\*.java
move C:\Users\jansc\git\RechnerkommunikationUndMiddleware\Labor04_RMIClient\src\*.class C:\Users\jansc\git\RechnerkommunikationUndMiddleware\Labor04_Startfiles
move C:\Users\jansc\git\RechnerkommunikationUndMiddleware\Labor04_RMIServer\src\*.class C:\Users\jansc\git\RechnerkommunikationUndMiddleware\Labor04_Startfiles
jar cfve ServerRMI.jar Program Program.class RMIServer.class RMIInterface.class DOMBuilder.class
jar cfve ClientRMI.jar RMIClient RMIClient.class RMIInterface.class DOMBuilder.class
start rmiregistry 5000
java -Djava.security.policy=file:security.policy  -jar ServerRMI.jar