$("#submit-btn").click(function(event) {
    let nameVal = $('input[name="name"]').val();
    let sequenceNumVal = $('input[name="sequenceNum"]').val();
    // name과 sequenceNum 값의 유효성을 검사합니다.
    if (!nameVal || nameVal.trim() === '' || !sequenceNumVal || sequenceNumVal.trim() === '' || isNaN(sequenceNumVal)) {
        event.preventDefault(); // 폼의 기본 동작을 중지합니다.
        alert('이름과 순서는 필수 입력 값이며, 순서는 숫자여야 합니다.');
    }
});

