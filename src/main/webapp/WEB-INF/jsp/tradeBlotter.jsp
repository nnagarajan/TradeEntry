<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%String contextPath = request.getContextPath();%>
    <title>Trade Entry Form</title>

    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

    <script src="<%=contextPath%>/js/tradeBlotter.js" type="text/javascript" charset="utf-8"></script>

    <link rel="stylesheet" href="<%=contextPath%>/css/styles.css" type="text/css"/>

</head>

<body>

<table border="1">
    <thead>
    <tr>
        <td>Transaction ID</td>
        <td>Symbol</td>
        <td>Action</td>
        <td>Quantity</td>
        <td>Price</td>
        <td>Market Value</td>
        <td class="togglePLCls">Profit/Loss</td>
    </tr>
    </thead>

    <c:forEach items="${tradeForm.tradeList}" var="tradeItem" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${tradeItem.symbol}</td>
            <td>${tradeItem.tradeAction}</td>
            <td>${tradeItem.quantity}</td>
            <td>${tradeItem.price}</td>
            <td>${tradeItem.marketValue}</td>
            <td id="profitLoss${status.count}" class="togglePLCls"></td>
        </tr>
    </c:forEach>
    <tr class="toggleCumPLCls">
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>Cumulative</td>
        <td id="cumulativePL"></td>
    </tr>
</table>

<div id="trade_blotter_submit">
    <button type="submit" id="calprofitloss">Calculate P/L</button>
</div>


</body>
</html>