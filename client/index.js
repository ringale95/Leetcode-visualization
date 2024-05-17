import { makeRequest } from "./common/api.js";

const onPageLoad = (event) => {

    // onIncrement();
    const button = document.getElementById('create-array');
    button.addEventListener('click', onCreateArrayClick);


    const searchArrayButton = document.getElementById('search-array');
    searchArrayButton.addEventListener('click', onSearchArrayClick);
}

const onCreateArrayClick = () => {
    const inputVal = document.getElementById('array-count').value;

    if (!!inputVal) {
        const count = parseInt(inputVal);
        if (count > 0) {
            const arrayContainer = document.getElementById('array-container');
            arrayContainer.innerHTML = '';

            // Create a visual representation of an array based on the count from inputVal
            for (let i = 0; i < count; i++) {
                const div = document.createElement('div');
                div.innerHTML = `<input type="text" class="value" value="__"><span class="index">Index ${i}</span>`;
                div.classList.add("border", "m-1", "p-2", "d-inline-block", "text-center");
                arrayContainer.appendChild(div);
            }
        } else {
            window.alert('Array size must be greater than zero!');
        }
    } else {
        window.alert('Enter array size!');
    }
};

const onSearchArrayClick = () => {
    const indexInput = document.getElementById('index-count').value;
    const outputField = document.getElementById('output-value');

    if (!!indexInput) {
        const index = parseInt(indexInput);
        const arrayInputs = document.querySelectorAll('.value');

        if (index >= 0 && index < arrayInputs.length) {
            const value = arrayInputs[index].value;
            outputField.value = `Value at index ${index}: ${value}`;
        } else {
            outputField.value = 'Invalid index!';
        }
    } else {
        outputField.value = 'Enter index!';
    }
};


// const onIncrement = () => {
//     const btn = document.getElementById('button-2');
//     btn.addEventListener('click', updateCount);
// }

// const updateCount = () => {
//     makeRequest("http://localhost:8080/get-count", 'GET', {}, onSuccess, onError);
// }

// const onSuccess = (data) => {
//     const countContainer = document.getElementById('count-container');
//     countContainer.innerHTML = data;
// }

// const onError = () => {
//     window.alert("Request failed!");
// }

document.addEventListener('DOMContentLoaded', onPageLoad);
