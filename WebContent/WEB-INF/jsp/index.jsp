<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.*" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Home page</title>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />  
    <script>
    function doSubmit(){
    	document.forms[1].action = "/tidy/action/fileupload/upload";
    }
  </script> 
  
  </head>
  <body>
    <div align="center">
      <h1>Test struts-bean:define Tag</h1>
    </div>
    <h3>Test 1 -- Direct Scalar Variable Defines</h3>
    
    
    <%
    Enumeration e = request.getAttributeNames();
    while (e.hasMoreElements()) {
    	String name = (String)e.nextElement();
    	System.out.println(name + ":" + request.getAttribute(name));
    }
    //<bean:define id="myform" name="userForm"/>
    %>
    
    
    <br/>
    <html:messages id="msg" message="false" property="testMsg" name="show">
    	<bean:write name="msg" />
    	<br/>
    </html:messages>
    
   	<html:messages id="msg" message="false" property="UiMsg" name="show" bundle="ui">
    	<bean:write name="msg" />
    	<br/>
    </html:messages>
    
    <bean:message key="tidy.page.message"/>
    <bean:message key="login.user.welcome" bundle="ui" arg0="Tom" arg1="Jack" />
    
    <html:form action="/index" >
    	<html:text property="name" size="20" /> <br/>
    	<html:text property="password" size="20" />
    </html:form>
        
    <html:errors/>
    <logic:notEmpty name="jobHistory" scope="session">
    	<bean:write name="jobHistory" property="companyName" scope="session"/>
    </logic:notEmpty>
    
    <c:if test="${sessionScope.jobHistory != null}">
    	${sessionScope.jobHistory.companyName}
    </c:if>
    
    <bean:write name="jobHistory" property="duration"/>
    <c:out value="hello world" />
    <c:out value="${jobHistory.duration}" />
    ${jobHistory.duration}
    
    <html:form enctype="multipart/form-data" action="/fileupload" method="post">
    	<html:text property="desc" size="20" /> <br/>
    	<input type="text" name="selectedfile" id="selectedfile" size="20" />
    	<input type="file" name="file[0]" size="30" value=""
    		onchange="document.getElementById('selectedfile').value=this.value"/> <br/>
    	<input type="file" name="file[1]" size="30" value=""
    		onchange="document.getElementById('selectedfile').value=this.value"/> <br/>
    	<html:submit onclick="doSubmit();">Upload file</html:submit>
    </html:form>
  </body>
</html>
