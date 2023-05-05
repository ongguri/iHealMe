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

function subCounter() {
    fetch('/HptReception/HptReceptionList/subCounter', {method: 'POST'})
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Something went wrong');
            }
        })
        .then(data => {
            const counterValue = document.querySelector('.counter-value');
            counterValue.textContent = parseInt(counterValue.textContent) - 1;
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
            console.error('Error:', error);
        });
}

function acceptReception() {
  // 현재 페이지 URL에서 프로토콜과 호스트 부분을 제외한 경로를 추출
  const urlPathname = window.location.pathname;
  const updateStatusPath = '/HptReception/updateCurrentStatus';

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

  // 알림창을 띄우는 코드
  alert('접수 완료되었습니다.');
}