$('form').submit(function(event) {
    let latitude = $('#latitude').val();
    let longitude = $('#longitude').val();
    if(isNaN(latitude) || isNaN(longitude)){
        alert("위도와 경도 값은 숫자만 입력 가능합니다.");
        event.preventDefault();
    }
});