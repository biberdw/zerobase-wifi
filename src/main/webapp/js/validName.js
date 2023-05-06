$(document).ready(function() {
    let previousValue = $('#name-input').val(); // 이전 값 저장
    $('#name-input').blur(function() {
        if($('#valid-input').val() === "1" || $('#valid-input').val() === 1){
            let currentValue = $('#name-input').val();
            if (previousValue !== currentValue) { // 값이 변경되었을 경우
                $('#valid-input').val('0'); // valid-input의 값을 0으로 변경
                previousValue = currentValue; // 현재 값 저장
            }
        }
    });
});


$('#name-btn').click(function() {

    let nameValue = $('#name-input').val();
    let mode = $('#mode').val();
    let request_url = '/apps/bookmarks/name?name=' + nameValue;
    if(mode === "update"){
        let idVal = $('#id-input').val();
        request_url = request_url + "&id=" + idVal;
    }

    $.ajax({
        url: request_url,
        type: 'GET',
        contentType: 'application/json',
        success: function(result) {
            // 요청이 성공한 경우 실행되는 콜백 함수
            console.log(result);
            alert("사용 가능한 이름입니다.");
            $('#valid-input').val(1);
        },
        error: function(xhr, status, error) {
            alert("이미 중복되는 이름입니다.");
            $('#valid-input').val(0);
        }
    });
});