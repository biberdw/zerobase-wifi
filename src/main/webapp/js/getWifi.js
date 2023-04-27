
$('#fetchWifi').on('click', function(e) {
    e.preventDefault();
    let form = $('<form method="POST" action="/apps/wifi"></form>');
    $('body').append(form);
    form.submit();
});