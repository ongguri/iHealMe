// function cancelReservation() {
//     let isCancel = confirm("접수를 취소하시겠습니까?")
//
//     if(isCancel) {
//         const updateStatusPath = '/userResCancelUpdate';
//
//         // 버튼 클릭 이벤트가 일어나면 form 요소를 동적으로 생성
//         const form = document.createElement('form');
//         form.setAttribute('method', 'get');
//         form.setAttribute('action', updateStatusPath);
//
//         // resNo 값을 전달하기 위한 hidden input 요소 추가
//         // const resNoInput = document.createElement('input');
//         // resNoInput.setAttribute('type', 'hidden');
//         // resNoInput.setAttribute('name', 'resNo');
//         // resNoInput.setAttribute('value', '${hptReception.resNo}');
//
//         // form 요소와 hidden input 요소를 body에 추가하고 submit
//         // form.appendChild(resNoInput);
//         document.body.appendChild(form);
//         form.submit();
//
//         alert("접수가 취소되었습니다.")
//     }
//     return isCancel;
// }

function cancelReservation(resNo) {
    let isCancel = confirm("접수를 취소하시겠습니까?")

    if (isCancel) {
        const updateStatusPath = '/userResCancelUpdate';

        // 버튼 클릭 이벤트가 일어나면 form 요소를 동적으로 생성
        const form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', updateStatusPath);

        document.body.appendChild(form);
        form.submit();

        alert("접수가 취소되었습니다.")
    }
}