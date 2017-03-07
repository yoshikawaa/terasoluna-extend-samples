<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Sample</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div id="wrapper">
    	<spring:nestedPath path="sampleForm">
    		<form:errors path="*" />
    	</spring:nestedPath>
    	<form:form method="POST" modelAttribute="sampleForm">
    		<form:label path="name">name</form:label>
    		<form:input path="name" />
    		<form:label path="email">email</form:label>
    		<form:input path="email"/>
    		<form:button id="submit">submit</form:button>
    	</form:form>
    </div>
</body>
</html>
