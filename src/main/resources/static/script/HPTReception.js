var counterValue = document.querySelector(".counter-value").textContent;
var isDisabled = document.getElementById("sub-btn").disabled;
var receptionList = JSON.parse(document.getElementById("receptionList").textContent);

function addCounter() {
    counterValue++;
    isDisabled = false;
    document.getElementById("sub-btn").disabled = false;
    document.querySelector(".counter-value").textContent = counterValue;
}

function subCounter() {
    counterValue--;
    if (counterValue <= 0) {
        isDisabled = true;
        document.getElementById("sub-btn").disabled = true;
    }
    document.querySelector(".counter-value").textContent = counterValue;
}

function accept(no) {
  // 해당 번호의 접수를 접수 처리하는 알림창
  alert(`접수번호 ${no} 번 접수되었습니다.`);
}

function reject(no) {
  // 해당 번호의 접수를 거절 처리하는 알림창
  alert(`접수번호 ${no} 번 취소되었습니다.`);
}

function complete(no) {
  // 해당 번호의 접수를 진료완료 처리하는 알림창
  alert(`접수번호 ${no} 번 진료 완료했습니다.`);
}


document.getElementById("add-btn").addEventListener("click", addCounter);
document.getElementById("sub-btn").addEventListener("click", subCounter);
