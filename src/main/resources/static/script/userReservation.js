function cancelReservation() {
    var isCancel = confirm("접수를 취소하시겠습니까?")

    if(isCancel) {
        alert("접수가 취소되었습니다.")
    }
    else {
        return false;
    }
}