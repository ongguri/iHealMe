function cancelReservation() {
    let isCancel = confirm("접수를 취소하시겠습니까?")

    if (isCancel) {
        const updateStatusPath = '/userResCancelUpdate';

        // 버튼 클릭 이벤트가 일어나면 form 요소를 동적으로 생성
        const form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', updateStatusPath);

        const resNoInput = document.createElement('input');
        resNoInput.setAttribute('type', 'hidden');
        resNoInput.setAttribute('name', 'resNo');
        resNoInput.setAttribute('value', '${reservationInfo.resNo}');

        form.appendChild(resNoInput);
        document.body.appendChild(form);
        form.submit();

        alert("접수가 취소되었습니다.")
    }

    return isCancel;
}