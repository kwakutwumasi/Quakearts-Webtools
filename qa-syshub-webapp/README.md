# qa-syshub-webapp

This project provides the web interface for the _qa-syshub_ library. It can be deployed on any servlet container. However it is configured and optimized to run on the Tomcat 9 Container in _qa-appbase_.

##### Requirements
* Java 8+
* Maven
* qa-syshub 2.0+

##### Quick Start

The application is available in the _com.quakearts.syshub:qa-syshub-archetype:1.0.0_ maven archetype. It will be automatically deployed to the web server folders when the project is built.
<br />
The project can also be downloaded and built.

##### Deploying a ProcessingAgent

1. Select 'Create → Create AgentConfiguration'

    1. Enter the desired "Agent Name"
    2. Configure other values as required.
    3. Ensure that the module is not marked as valid.
    4. Save the Agent Configuration. Correct any errors when prompted.

2. Select 'Create → Create AgentModule'.

    1. Under "Agent Configuration" enter the name of the Agent Configuration created under 1.1. Wait for the search to complete and select the Agent Name in the drop down
    2. Enter the desired name for the MessageSpooler Module under 'Module Name'.
    3. Select 'DATASPOOLER' as 'Module Type'
    4. Select the desired DataSpooler class as the 'Module Class'
    5. Configure other values as required.
	6. Save the agent module

3. Click on 'Reset' to clear the previously selected values.
    
    1. Under 'Agent Configuration' select the name of the Agent Configuration created under 1.1.
    2. Enter the desired name for the MessageFormatter Module under 'Module Name'.
    3. Select 'FORMATTER' as 'Module Type'
	4. Select the desired MessageFormatter class as the 'Module Class'
    5. Configure other values as required.
	6. Save the agent module

4. Click on 'Reset' to clear the previously selected values.
    
    1. Under 'Agent Configuration' select the name of the Agent Configuration created under 1.1.
    2. Enter the desired name for the Messenger Module under 'Module Name'.
    3. Select 'MESSENGER' as 'Module Type'
    4. Select the desired Messenger class as the 'Module Class'
    5. Configure other values as required.
	6. Save the agent module

5. Select 'View → View Agent Configuration'
    
    1. Under 'Agent Name' enter the name of the Agent Configuration created under 1.1 and click on 'Search'
    2. Select 'View' to the right of the Agent Configuration result returned
    3. Select 'Edit' at the bottom of the Agent Configuration screen
    3. Check the 'Valid' check box then save the Agent Configuration to activate it.
    3. Select 'Deploy' and wait for deployment to be confirmed. Correct any errors when prompted

6. Click on the 'SysHub Management Interface'. Verify that the agent created under 1.1 is listed under 'Agents'. Click on the agent to view status and statistics.