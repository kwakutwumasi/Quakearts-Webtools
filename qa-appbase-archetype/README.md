# qa-appbase-archetype

An archetype for creating a simple appbase project.

##### Requirements
* Java 8+
* Maven

##### Quick Start

To create this archetype, use the command:

```
	mvn archetype:create -DgroupId=YOUR.PROJ.GID \
	-DartifactId=YOUR-PROJ-AID \
	-DarchetypeGroupId=com.quakearts.webtools \
	-DarchetypeArtifactId=qa-appbase-archetype \
	-DarchetypeVersion=1.0.2
	-DremoteRepositories=https://raw.github.com/kwakutwumasi/Quakearts-Webtools/mvn-repo/
	
```

substituting _YOUR.PROJ.GID_ with your project group ID and _YOUR-PROJ-AID_ with your project artifact ID.