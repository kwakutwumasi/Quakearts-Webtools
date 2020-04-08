# qa-approval-engine

A multi level, multi person, multi rule approval engine, designed to make it easier to incorporate an enterprise ready approval system into an application. The QA Approval Engine supports multi-threaded operating environments, and does not enforce strict approval process ordering. Such behavior is much more easily enforced by the wrapping application. This allows for multiple approval scenarios.
<br />
One Example : a company wants to have user actions approved by 1 senior manager and 1 supervisor. In the absence of senior managers, 1 supervisor and 2 team leaders may approve the action. This is only allowed if the senior manager's schedule manager shows him as unavailable.
<br />
There are two ways to go about this: 
* Create 2 rules, covering the two scenarios. Trigger the approval process for the first scenario by using the created rule. If the manager is unavailable, trigger the approval process for the second scenario
* Create 2 rules, covering the two scenarios. Trigger the approval process, providing both rules. Before processing an approval, ensure that the rule that it satisfies is active.

##### Requirements
* Java 8+
* Maven
* An application Container (Tomcat, Jetty, Wildfly, Undertow, etc) with CDI support or QA-AppBase
* an implementation of QA-ORM

##### Quick Start

Add the QuakeArts.com maven repository

```
<repositories>
    <repository>
        <id>YOUR-PROJECT-NAME-mvn-repo</id>
        <url>https://raw.github.com/kwakutwumasi/Quakearts-Webtools/mvn-repo/</url>
    </repository>
</repositories>

```

then add the dependency

```
<dependency>
	<groupId>com.quakearts.webtools</groupId>
	<artifactId>qa-approval-engine</artifactId>
	<version>1.0.0</version>
</dependency>

```

##### Usage

Use an instance _com.quakearts.approvalengine.services.ApprovalAdministrationService_ to create an 
_com.quakearts.approvalengine.model.ApprovalGroup_ by calling _createApprovalGroup(String)_. Create _com.quakearts.approvalengine.model.ApprovalRules_ by using builder calls to _createApprovalRules(String, String)_. Use _createApprover(String, int, String)_ to create _com.quakearts.approvalengine.model.Approver_s. Once created, the instances are persisted by the implementation of QA-ORM. The returned instances can be persisted by the applications own internal persistence architecture. For convenience, all instances can be retrieved using the respective names. This allows for simpler storage of the 
    
Use an instance of _com.quakearts.approvalengine.services.ApprovalService_ to instantiate an _com.quakearts.approvalengine.model.ApprovalProcess_ instance by calling one of the _initiateApproval(String, ...)_ methods. The returned instance should be persisted.
    
To process an approval or rejection from a user, call _processApproval(com.quakearts.approvalengine.context.ApprovalContext)_, using an _com.quakearts.approvalengine.context.ApprovalContext_ obtained from a instance of to _com.quakearts.approvalengine.context.ApprovalContextBuilder_. Instances of _com.quakearts.approvalengine.context.ApprovalContextBuilder_ are obtained by calling the _createApprovalContext()_ method of an instance of _com.quakearts.approvalengine.context.ApprovalContextBuilderFactory_. Once obtained use the builder methods to load the _com.quakearts.approvalengine.model.Approver_ and _com.quakearts.approvalengine.model.ApprovalProcess_.
  
Once all approval rules have been satisfied, the returned _com.quakearts.approvalengine.model.ApprovalProcess_ will be set to a complete state. This can be checked by making calls to _isApproved()_ and _isComplete()_. Approval is indicated by _isApproved()_ and _isComplete()_ both returning a boolean value that is _true_. Rejection is indicated by _isApproved()_ returning a boolean value of _false_ and _isComplete()_ returning a boolean value that is _true_. Two convenience methods _hasBeenApproved()_ and _hasBeenRejected()_ have been provided to simplify these checks.

##### Concepts

Approval processes usually revolve around having a set of approvers who must indicate approval for an action by interacting with a form. Enterprises utilize approvals to prevent internal fraud. When a particular system process involves destructive or financially costly actions, it is a good idea to have some kind of approval process around it. It also provides a record of those who gave their consent for the action. In a well designed system that includes strong secret storage, access to such dangerous actions by a single individual can be completely removed. This provides auditors and internal controllers the assurance that every such action will have the needed oversight.  

The QA Approval Engine library encapsulates this process using the concept of approval processes and approval rules. Approval rules simply state the number of approvals, per level, required to complete the approval process, and thus trigger the action. Levels have no meaning, and are simply used to group approvers into categories. Levels use numbering to make it easier to sort the approvers. They do not need to be contiguous.
  
To make it easier to have different conditions for approval, each process can have multiple sets of rules. Rules can be active or inactive. Inactive rules are not considered when determining approval process completion.
  
Further more, the concept of approval groups is introduce for multi tenant systems. Each tenant can be classified as a group. Processes belonging to one group cannot be handled by other groups.
  
In order to cater for scenarios where an individual belongs to different groups, approver profiles are unique only within approval groups. This makes it possible to tie a users globally unique identity to each of their approval group profiles.