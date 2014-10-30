$(document).ready(function () {
    $("#trade_entry").validationEngine({showOneMessage: true});

    if ($("#message").val() != null)
        $("#message_div").show();
    else
        $("#message_div").hide();

    $("#trade_entry_submit button").click(function (event) {
        $("#form_action").val("save");

        $("#trade_entry").submit();
    });
});
