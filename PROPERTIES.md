yucca-light properties 
=============

List of configurable properties.
Required properties must be declared in custom properties file. 

How to define properties
------------------------

If you run application in standalone mode you can simply write a file application.properties in the same directory of jar on in a subdirectory named config.  

If you deploy application in tomcat 8 you can choose one of this:

* Write the properties file in a custom dir and add in $CATALINA_BASE/conf/Context.xml the line:
```xml
<Parameter name="ext.application.properties" value="file:\your_custom_path\your_properties_file"/>
```
* Write the properties file in a custom dir and create in $CATALINA_BASE/conf/[enginename]/[hostname]/yucca-light.xml with:
```xml
<Context>
<Parameter name="ext.application.properties" value="file:\your_custom_path\your_properties_file"/>
</Context>
```

Properties
-----------
