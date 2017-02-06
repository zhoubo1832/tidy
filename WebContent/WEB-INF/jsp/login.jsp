<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
  <head>
    <title>Login page</title>
    
  <script>
    function doSubmit(){
    	document.forms[0].action = "/tidy/action/login/login";
    }
  </script> 
  </head>
  
<body>

  
  <bean:include id="err" page="/action/error" />
  <bean:write name="err" filter="false"/>
   
  <%=request.getSession().getId() %>
  
  
  <html:messages id="msg" message="true" property="UiMsg" bundle="ui">
  	<bean:write name="msg" />
  </html:messages>
  
  <html:form action="/login">
  
    <html:text property="name" size="20" /> <br/>
    <html:text property="password" size="20" />
    
  	<html:submit onclick="doSubmit();">
  		
  	</html:submit>
  </html:form>
  <br/>
  <br/>
  <html:errors />

</body>

</html>