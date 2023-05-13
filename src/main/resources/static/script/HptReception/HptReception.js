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
    const updateStatusPath = '/HptReception/HptReceptionList/updateCurrentStatusToAccept';

    // 버튼 클릭 이벤트가 일어나면 form 요소를 동적으로 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', updateStatusPath);

    // resNo 값을 전달하기 위한 hidden input 요소 추가
    const resNoInput = document.createElement('input');
    resNoInput.setAttribute('type', 'hidden');
    resNoInput.setAttribute('name', 'resNo');
    resNoInput.setAttribute('value', '${hptReception.resNo}');

    // form 요소와 hidden input 요소를 body에 추가하고 submit
    form.appendChild(resNoInput);
    document.body.appendChild(form);

    //confirm 메시지를 띄우고, 사용자가 확인을 누른 경우에만 form을 submit합니다.
    form.submit();
    alert('접수가 수락되었습니다.');
  }

  return isAccept;
}

function rejectReception() {
  const isReject = confirm('접수를 취소하시겠습니까?');

  if (isReject) {
    const updateStatusPath = '/HptReception/HptReceptionList/updateCurrentStatusToReject';

    // 버튼 클릭 이벤트가 일어나면 form 요소를 동적으로 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', updateStatusPath);

    // resNo 값을 전달하기 위한 hidden input 요소 추가
    const resNoInput = document.createElement('input');
    resNoInput.setAttribute('type', 'hidden');
    resNoInput.setAttribute('name', 'resNo');
    resNoInput.setAttribute('value', '${hptReception.resNo}');

    // form 요소와 hidden input 요소를 body에 추가하고 submit
    form.appendChild(resNoInput);
    document.body.appendChild(form);

    //confirm 메시지를 띄우고, 사용자가 확인을 누른 경우에만 form을 submit합니다.
    form.submit();
    alert('접수가 취소되었습니다.');
  }

  return isReject;
}


function completeTreatment() {
  const isComplete = confirm('진료를 완료하시겠습니까?');

  if (isComplete) {
    const updateStatusPath = '/HptReception/HptReceptionList/updateCurrentStatusToComplete';

    // 버튼 클릭 이벤트가 일어나면 form 요소를 동적으로 생성
    const form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', updateStatusPath);

    // resNo 값을 전달하기 위한 hidden input 요소 추가
    const resNoInput = document.createElement('input');
    resNoInput.setAttribute('type', 'hidden');
    resNoInput.setAttribute('name', 'resNo');
    resNoInput.setAttribute('value', '${hptReception.resNo}');

    // form 요소와 hidden input 요소를 body에 추가하고 submit
    form.appendChild(resNoInput);
    document.body.appendChild(form);
    form.submit();

    //confirm 메시지를 띄우고, 사용자가 확인을 누른 경우에만 form을 submit합니다.
     form.submit();
     alert('진료가 완료되었습니다.');
   }

    return isComplete;
  }




