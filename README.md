yucca-light (Yucca portable gateway )
=============


Yucca-light allows you to easily integrate intelligent objects with a platform yucca (as www.smartdatanet.it ) even when you have network discontinuity


![yucca-light architecture](src/site/resources/images/gwiot_arch.png)

First version of Yucca-Light
----------------------------

* Gateway functionality to mediate smart objects and Yucca platform (as www.smartdatanet.it)
* Web console to managed and view gatewat status
* Autoconfiguration from Yucca platform (as www.smartdatanet.it)
* Run with embedded Tomcat or in external Tomcat
* Run with embedded or external ActiveMQ

Getting started
---------------

To use yucca-light you must follow these steps:

1. Choose if you want costumize & build code or just download
2. Choose if you want run as standalone (using tomcat embedded) or install on tomcat 8
3. Build or download correct release
4. Configure properties (see [Properties]( PROPERTIES.md))
5. Run from shell or deploy on tomcat 8

yucca-light is a [Spring boot application](http://projects.spring.io/spring-boot/), you can code or use all spring boot capabilities.

Run with embedded or external ActiveMQ
--------------------------------------

In order to run yucca-light using an external ActiveMQ you must configure these properties in your properties file
```
spring.activemq.broker-url
spring.activemq.user
spring.activemq.password
``` 

for example default configuration for ActiveMQ works with:
```
spring.activemq.broker-url=tcp://localhost:61620
spring.activemq.user=system
spring.activemq.password=manager
``` 

Yucca-light detect external broker url and doesn't start internal ActiveMQ.

