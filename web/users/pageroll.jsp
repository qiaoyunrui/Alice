<jsp:useBean id="pageRoll" scope="request" type="com.juhezi.alice.base.PageRoll"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: qiao1
  Date: 2017/4/14
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        function firstPage() {
            var _currPage = document.pageRollFrom.currPage.value;
            if (_currPage == 1) {
                alert("已经是第一页了");
                return;
            }
            document.pageRollFrom.currPage.value = 1;
            document.pageRollFrom.submit();
        }
        function prevPage() {
            var _currPage = document.pageRollFrom.currPage.value;
            if (_currPage == 1) {
                alert("已经是第一页了");
                return;
            }
            document.pageRollFrom.currPage.value = --_currPage;
            document.pageRollFrom.submit();
        }
        function nextPage() {
            var _currPage = document.pageRollFrom.currPage.value;
            var _pageCount = document.pageRollFrom.pageCount.value;
            if (_currPage === _pageCount) {
                alert("已经是最后一页了");
                return;
            }
            document.pageRollFrom.currPage.value = ++_currPage;
            document.pageRollFrom.submit();
        }
        function lastPage() {
            var _currPage = document.pageRollFrom.currPage.value;
            var _pageCount = document.pageRollFrom.pageCount.value;
            if (_currPage === _pageCount) {
                alert("已经是最后一页了");
                return;
            }
            document.pageRollFrom.currPage.value = _pageCount;
            document.pageRollFrom.submit();
        }
        function gotoPage() {
            var _selectValue = document.pageRollFrom.goto_page.value;
            document.pageRollFrom.currPage.value = _selectValue;
            document.pageRollFrom.submit();
        }
    </script>
</head>
<body>

<form action="${param.action}" name="pageRollFrom" method="post">
    当前是第 ${pageRoll.currPage} 页 |
    共 ${pageRoll.pageCount} 页 |
    共 ${pageRoll.totalCount} 条记录 |

    <input type="hidden" name="currPage" value="${pageRoll.currPage}">
    <input type="hidden" name="pageCount" value="${pageRoll.pageCount}">

    <input type="button" value="首页" onclick="firstPage()">
    <input type="button" value="上一页" onclick="prevPage()">
    <input type="button" value="下一页" onclick="nextPage()">
    <input type="button" value="尾页" onclick="lastPage()">
    <select name="goto_page" onchange="gotoPage()">
        <c:forEach begin="1" end="${pageRoll.pageCount}" var="i">
            <c:choose>
                <c:when test="${pageRoll.currPage == i}">
                    <option value="${i}" selected="selected">${i}</option>
                </c:when>
                <c:otherwise>
                    <option value="${i}">${i}</option>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </select>
</form>

</body>
</html>
