<%@ attribute name="link" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pagination-section">
    <ul class="pagination">

        <c:choose>
            <c:when test="${requestScope.currentPage != 1}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}${link}?page=${requestScope.currentPage - 1}">Previous</a> </li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link disabled" href="#">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li class="page-item"><a class="page-link disabled" href="#">${i}</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}${link}?page=${i}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:choose>
            <c:when test="${requestScope.currentPage lt requestScope.noOfPages}">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}${link}?page=${requestScope.currentPage + 1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link disabled" href="#">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>