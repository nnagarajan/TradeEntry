<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%String contextPath = request.getContextPath();%>
    <title>Trade Entry Form</title>

    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="<%=contextPath%>/js/predefined/jquery.validationEngine-en.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="<%=contextPath%>/js/predefined/jquery.validationEngine.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="<%=contextPath%>/js/tradeEntry.js" type="text/javascript" charset="utf-8"></script>

    <link rel="stylesheet" href="<%=contextPath%>/css/predefined/validationEngine.jquery.css" type="text/css"/>
    <link rel="stylesheet" href="<%=contextPath%>/css/styles.css" type="text/css"/>

</head>
<body>
<form id="trade_entry" method="post" modelAttribute="tradeForm">
    <input type="hidden" id="form_action" name="form_action" value=""/>
    <input type="hidden" id="message" value="${message}"/>
    <table>
        <tr><br><br>
            <td><label>Symbol</label></td>
            <td><form:input type="text" class="validate[required, custom[onlyLetterSp]]" id="symbol"
                            path="tradeForm.trade.symbol"/>

        </tr>
        <tr>
            <td><label>Action</label></td>
            <td>
                <form:radiobutton name="action" value="BUY" class="validate[required]"
                                  path="tradeForm.trade.tradeAction"/> Buy
                <form:radiobutton name="action" value="SELL" class="validate[required]"
                                  path="tradeForm.trade.tradeAction"/> Sell
        </tr>

        <tr>
            <td><label>Price</label></td>
            <td><form:input type="text" class="validate[required, custom[number], min[0]]" path="tradeForm.trade.price"
                            id="price"/>
        </tr>

        <tr>
            <td><label>Quantity</label></td>
            <td><form:input type="text" class="validate[required, custom[integer], min[1]]"
                            path="tradeForm.trade.quantity" id="quantity"/>
        </tr>

        <tr>
            <td>
                <div id="trade_entry_submit">
                    <button type="submit">Execute</button>
                </div>
            </td>
        </tr>

    </table>
    <div class="hide" id="message_div">
        <div id="messageTitle">${message}</div>
    </div>

</form>


</body>
</html>