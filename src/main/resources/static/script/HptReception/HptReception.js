function addCounter() {
    fetch('/HptReception/addCounter', {method: 'POST'})
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
    fetch('/HptReception/subCounter', {method: 'POST'})
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