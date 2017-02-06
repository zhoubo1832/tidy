<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %> 
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<body>

<c:if test="${pagingBean.pageNum > 0}">
    	<c:if test="${pagingBean.curPageNo > 1}">
	    		<html:link action="${param.actionpath}?curPageNo=${pagingBean.curPageNo-1}&pageItemNum=${pagingBean.pageItemNum}">Prev</html:link>
    	</c:if>
    	
	    <c:forEach begin="${pagingBean.begin}" end="${pagingBean.end}" var="pageNo">
	    	
	    	<c:if test="${pagingBean.curPageNo == pageNo}">
	    		${pageNo}
	    	</c:if>
	    	
	    	<c:if test="${pagingBean.curPageNo != pageNo}">
	    		<html:link action="${param.actionpath}?curPageNo=${pageNo}&pageItemNum=${pagingBean.pageItemNum}">${pageNo}</html:link>
	    	</c:if>
    		
    		
	    </c:forEach>
	    
	    <c:if test="${pagingBean.curPageNo != pagingBean.pageNum}">
	    		<html:link action="${param.actionpath}?curPageNo=${pagingBean.curPageNo+1}&pageItemNum=${pagingBean.pageItemNum}">Next</html:link>
	    </c:if>
</c:if>
</body>
</html>