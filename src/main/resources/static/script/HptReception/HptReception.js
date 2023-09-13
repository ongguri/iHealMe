function addCounter() {
    fetch('/HptReception/HptReceptionList/addCounter', {method: 'POST'})
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Something went wrong');
            }
        })
        .then(data => {
            const counterValue = document.querySelector('.counter-value');
            counterValue.textContent = parseInt(counterValue.textContent) + 1;
            const subBtn = document.querySelector('#sub-btn');
            subBtn.disabled = false;
            const rtCount = parseInt(counterValue.textContent);
            const addBtn = document.querySelector('#add-btn');
            if (isNaN(rtCount) || rtCount <= 0) {
                addBtn.disabled = true;
            } else {
                addBtn.disabled = false;
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// function subCounter() {
//     fetch('/HptReception/HptReceptionList/subCounter', {method: 'POST'})
//         .then(response => {
//             if (response.ok) {
//                 return response.text();
//             } else {
//                 throw new Error('Something went wrong');
//             }
//         })
//         .then(data => {
//             const counterValue = document.querySelector('.counter-value');
//             counterValue.textContent = parseInt(counterValue.textContent) - 1;
//             const addBtn = document.querySelector('#add-btn');
//             addBtn.disabled = false;
//             const rtCount = parseInt(counterValue.textContent);
//             const subBtn = document.querySelector('#sub-btn');
//             if (isNaN(rtCount) || rtCount <= 0) {
//                 subBtn.disabled = true;
//             } else {
//                 subBtn.disabled = false;
//             }
//         })
// }
function subCounter() {
    const counterValue = document.querySelector('.counter-value');
    const rtCount = parseInt(counterValue.textContent);

    fetch('/HptReception/HptReceptionList/subCounter', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ rtCount: rtCount })
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Something went wrong');
            }
        })
        .then(data => {
            const counterValue = document.querySelector('.counter-value');
            const currentValue = parseInt(counterValue.textContent);
            if (currentValue > 0) {
                counterValue.textContent = currentValue - 1;
            }
            const addBtn = document.querySelector('#add-btn');
            addBtn.disabled = false;
            const rtCount = parseInt(counterValue.textContent);
            const subBtn = document.querySelector('#sub-btn');
            if (isNaN(rtCount) || rtCount <= 0) {
                subBtn.disabled = true;
            } else {
                subBtn.disabled = false;
            }
        })
        .catch(error => {
            // 오류 발생 시 실행할 코드
            console.error('Error:', error);
        });
}

function acceptReception() {
  const isAccept = confirm('접수를 수락하시겠습니까?');

  if (isAccept) {
    alert('접수가 수락되었습니다.');
  }

  return isAccept;
}

function rejectReception() {
  const isReject = confirm('접수를 취소하시겠습니까?');

  if (isReject) {
    alert('접수가 취소되었습니다.');
  }

  return isReject;
}


function completeTreatment() {
  const isComplete = confirm('진료를 완료하시겠습니까?');

  if (isComplete) {
     alert('진료가 완료되었습니다.');
   }

    return isComplete;
  }
