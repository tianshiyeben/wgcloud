<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="panel-footer">
<ul class="pagination pagination-sm">
  <li>
    <a href="${pageContext.request.contextPath}${pageUrl}&curPage=1">«</a>
  </li>
              
              
<c:choose>
	<c:when test="${page.viewCountPages > 9}">
		<c:forEach varStatus="i"  begin="${page.viewCountPages -9 }" end="${page.viewCountPages }">
			<c:choose>
			 <c:when test="${page.currentPage == i.count}">
			 	<li class="active"><a href="${pageContext.request.contextPath}${pageUrl}&curPage=${i.count}">${i.count}</a></li>
			 </c:when>
			 <c:otherwise>
			 	<li><a href="${pageContext.request.contextPath}${pageUrl}&curPage=${i.count}">${i.count}</a></li>
			 </c:otherwise>
			</c:choose>
		</c:forEach>
	</c:when>
	
	<c:otherwise>
	 	<c:forEach varStatus="i"  begin="1" end="${page.viewCountPages }">
			<c:choose>
			 <c:when test="${page.currentPage == i.count}">
			 	<li class="active"><a href="${pageContext.request.contextPath}${pageUrl}&curPage=${i.count}">${i.count}</a></li>
			 </c:when>
			 <c:otherwise>
			 	<li><a href="${pageContext.request.contextPath}${pageUrl}&curPage=${i.count}">${i.count}</a></li>
			 </c:otherwise>
			</c:choose>
		</c:forEach>
	</c:otherwise>
 
</c:choose>

<li>
  <a href="${pageContext.request.contextPath}${pageUrl}&curPage=${page.totalPage}">»</a>
</li>
</ul>
  <div class="pull-right">共${page.totalPage}页</div>
</div>