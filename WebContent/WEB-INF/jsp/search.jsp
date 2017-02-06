<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>
  <head>
    <title>Search page</title>
    
  <script>
    function doSubmit(){
    	document.forms[0].action = "/tidy/action/search/search";
    }
  </script> 
  </head>
  
<body>
  
  

  <html:form action="/search">
  	<jsp:include page="paging.jsp">
  		<jsp:param name="actionpath" value="/search/search"/>
  	</jsp:include>
  
  	<c:forEach items="${pagingBean.listResult}" var="emp">
  	<div>
  		${emp.id}
  		${emp.name}
  		${emp.age}
  	</div>
  	</c:forEach>
    
    <jsp:include page="paging.jsp">
  		<jsp:param name="actionpath" value="/search/search"/>
	</jsp:include>
  		
  </html:form>
  
  
  
  
  <br/>
  <br/>
  <html:errors />

</body>

</html>