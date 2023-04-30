$('.delete-btn').on('click', function(event) {
    event.preventDefault(); // 클릭 이벤트의 기본 동작인 링크 이동을 중지합니다.
    $(this).closest('form').submit(); // 클릭한 삭제 버튼에 가장 가까운 폼(form) 요소를 찾아서 서버로 전송합니다.
});