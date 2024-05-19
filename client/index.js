import { makeRequest, makeRequestFormData } from "./common/api.js";

let arr = [];

const onPageLoad = () => {
    const form = document.getElementById('create-array-form');
    form.addEventListener('submit', onCreateArrayClick);

    const searchArrayButton = document.getElementById('search-array');
    searchArrayButton.addEventListener('click', onSearchArrayClick);

    const formDataByIndex = document.getElementById('search-by-index');
    formDataByIndex.addEventListener('submit', searchByIndex);

    const modifyBtn = document.getElementById('modify-array');
    modifyBtn.addEventListener('click', onClickModifyBtn);

    const saveArrBtn = document.getElementById('save');
    saveArrBtn.addEventListener('click', onSaveBtnClick)

    const findSmallestBtn = document.getElementById('smallest-number');
    findSmallestBtn.addEventListener('click', findSmallestElementFromArray)

    const findLargestElement = document.getElementById('largest-number');
    findLargestElement.addEventListener('click', findLargest)
};

const onCreateArrayClick = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());
    const onSuccess = () => {
        const inputVal = data['array-count'];
        const count = parseInt(inputVal);

        if (count > 0) {
            const arrayContainer = document.getElementById('array-container');
            arrayContainer.innerHTML = '';
            arr = new Array(count).fill(0);
            // Create a visual representation of an array based on the count from inputVal
            arr.forEach((val, i) => {
                const div = document.createElement('div');
                div.innerHTML = `<input id="array-id-${i}" type="text" class="value" value="${val}"><span class="index">Index ${i}</span>`;
                div.classList.add("border", "m-1", "p-2", "d-inline-block", "text-center");
                arrayContainer.appendChild(div);

                // Register listener for Inputs
                const inputArr = document.getElementById(`array-id-${i}`);
                inputArr.addEventListener('change', (event) => {updateValueAt(parseInt(event.target.value), i); console.log(arr)});
            });
        } else {
            window.alert('Array size must be greater than zero!');
        }
    };

    const updateValueAt = (val, index) => {
        arr[index] = val;
    }

    const onError = (response) => {
        window.alert("Error in the request");
    };

    makeRequestFormData("http://localhost:8080/create-array", 'POST', data, onSuccess, onError);
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

const searchByIndex = (event) => {
    event.preventDefault();
    const formData = new FormData(event.target);
    const data = Object.fromEntries(formData.entries());
    console.log("Formdata" + formData);
    console.log(data);
    const output = document.getElementById('output-value-index');
    const onSuccess = (response) => {
        output.innerHTML = response;
    }

    const onError = () => {
      window.alert("No valid response");
    }
    
    makeRequest("http://localhost:8080/search-by-index/"+ data["search-index"], 'GET', {}, onSuccess, onError);
    
};

const onClickModifyBtn = () => {
    const onSuccess = (response) => {
        const arrayContainer = document.getElementById('array-container');
            arrayContainer.innerHTML = '';
            console.log(response);
            response.forEach((val, i) => {
                const div = document.createElement('div');
                div.innerHTML = `<input type="text" class="value" value="${val}"><span class="index">Index ${i}</span>`;
                div.classList.add("border", "m-1", "p-2", "d-inline-block", "text-center");
                arrayContainer.appendChild(div);
            });
        } 
    
    makeRequest("http://localhost:8080/even-index-array", 'GET', {}, onSuccess, () => console.error('I am hurt.'));
}

const onSaveBtnClick = ()  => {
    const onSuccess = () => {
        window.alert("Array saved successfully!")
    }

 makeRequest("http://localhost:8080/update-array", 'PUT', arr, onSuccess, () => window.alert('Save operation failed!.'));

}

const findSmallestElementFromArray = () => {
    const onSuccess = (index) => {
        window.alert("Smallest index is " + index + " and the value is " + arr[index]);
    }
    makeRequest("http://localhost:8080/smallest-element", 'GET', arr, onSuccess, () => window.alert('Operation failed!.'));
}

const findLargest = () => {
    const onSuccess = (index) => {
        window.alert("Largest index is " + index + " and the value is " + arr[index]);
    }
    makeRequest("http://localhost:8080/largest-element", 'GET', arr, onSuccess, () => window.alert('Operation failed!.'));
}


document.addEventListener('DOMContentLoaded', onPageLoad);
