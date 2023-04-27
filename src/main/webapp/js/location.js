$('#location-btn').click(function(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(function(position){
            $('#latitude').val(position.coords.latitude);
            $('#longitude').val(position.coords.longitude);
        });
    } else {
        alert("지원되지 않는 브라우저 또는 위치 수집 허용 여부를 확인해주세요.")
    }
});