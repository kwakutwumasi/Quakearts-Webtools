# qa-appbase-minimal-archetype

An archetype for creating a minimal appbase project. This project will start the webserver (Tomcat) and context dependency injection (Weld).
Projects that don't connect to traditional RDBMS's or that don't require JTA can use this archetype.

##### Requirements
* Java 8+
* Maven

##### Quick Start

To create this archetype, use the command:

```
	mvn archetype:generate -DgroupId=YOUR.PROJ.GID \
	-DartifactId=YOUR-PROJ-AID \
	-DarchetypeGroupId=com.quakearts.webtools \
	-DarchetypeArtifactId=qa-appbase-minimal-archetype \
	-DarchetypeVersion=1.0.1 \
	-DremoteRepositories=https://raw.github.com/kwakutwumasi/Quakearts-Webtools/mvn-repo/
	
```

substituting _YOUR.PROJ.GID_ with your project group ID and _YOUR-PROJ-AID_ with your project artifact ID.

##### Notes

The alternate-web-configs contains example main-webapp.config.json files for use in common scenarios.
* **live.main-webapp.config.json** contains example configuration for use in a live environment.
* **jsf-live.main-webapp.config.json** contains example configuration for JSF. Replace the content of your pom with the content of jsf-pom.xml to add JSF related dependencies
* **rest-live.main-webapp.config.json** contains example configuration for JAX-RS using Jboss RestEasy. Replace the content of your pom with the content of rest-pom.xml to add JAX-RS related dependencies

To use them, remove the prefix (the content before _main-webapp.config.json_) and replace the _main-webapp.config.json_ in _src/main/resources_ folder.