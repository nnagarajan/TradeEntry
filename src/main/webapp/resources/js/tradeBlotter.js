$(document).ready(function () {
    $("#calprofitloss").click(function (event) {
        $.ajax({
            type: "GET",
            url: "/trade/tradeBlotter/getProfitLoss",
            async: false,
            cache: true,
            dataType: 'json'
        }).success(function (data) {
            var x = 1;
            var cumPL = 0;

            $.grep(data, function (item) {
                $("#profitLoss" + x).text(item.profitLoss);
                cumPL = item.cumProfitLoss;
                x = x + 1;
            });
            $("#cumulativePL").text(cumPL);
            $(".toggleCumPLCls").show();
            $(".togglePLCls").show();
        });
    });
});