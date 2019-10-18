   Example Mule connector project, used http://training4-american-ws.cloudhub.io/api/flights/ api to fetch flights details in this example.

Steps to build:
```
1. git clone https://github.com/maddireddys/mule-flight-connector.git
2. cd mule-flight-connector
3. mvn clean install -DskipTests
```
Once build succesfull, then you can use connector by adding below dependency to your application pom.xml.
```
<groupId>mule.connector</groupId>
<artifactId>mule-flight-connector</artifactId>
<version>1.0.0</version>
<classifier>mule-plugin</classifier>
```
Once you add dependency to your appilication, you can find Flight connector on your mule Palette like below.
![alt text](https://github.com/maddireddys/mule-flight-connector/blob/master/FlightConnector.png)

Tech stack
```
Java 1.8.0_181
Mule 4.2
Mavan 3.6.2
```
