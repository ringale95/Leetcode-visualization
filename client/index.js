const onPageLoad = (event) => {
    const button = document.getElementById('create-array');
    button.addEventListener('click', onCreateArrayClick);

    const searchArrayButton = document.getElementById('search-array');
    searchArrayButton.addEventListener('click', onSearchArrayClick);
}

const onCreateArrayClick = () => {
    const inputVal = document.getElementById('array-count').value;

    if (!!inputVal) {
        const dimensions = inputVal.split(',').map(Number);
        if (dimensions.length === 2 && dimensions[0] > 0 && dimensions[1] > 0) {
            const rows = dimensions[0];
            const cols = dimensions[1];
            const arrayContainer = document.getElementById('array-container');
            arrayContainer.innerHTML = '';

            for (let i = 0; i < rows; i++) {
                const rowDiv = document.createElement('div');
                rowDiv.classList.add('mb-5', 'row');

                for (let j = 0; j < cols; j++) {
                    const cellDiv = document.createElement('div');
                    cellDiv.innerHTML = `<input type="text" class="value form-control" value="__"><span class="index">Index [${i}][${j}]</span>`;
                    cellDiv.classList.add('border', 'm-1', 'p-2', 'd-inline-block', 'col');
                    rowDiv.appendChild(cellDiv);
                }

                arrayContainer.appendChild(rowDiv);
            }
        } else {
            window.alert('Please enter valid row and column sizes!');
        }
    } else {
        window.alert('Enter array size in the format rows,columns!');
    }
};

const onSearchArrayClick = () => {
    const indexInput = document.getElementById('index-count').value;
    const outputField = document.getElementById('output-value');

    if (!!indexInput) {
        const indices = indexInput.split(',').map(Number);
        if (indices.length === 2) {
            const rowIndex = indices[0];
            const colIndex = indices[1];
            const rows = document.querySelectorAll('#array-container > .row');
            if (rowIndex >= 0 && rowIndex < rows.length) {
                const cols = rows[rowIndex].querySelectorAll('.value');
                if (colIndex >= 0 && colIndex < cols.length) {
                    const value = cols[colIndex].value;
                    outputField.value = `Value at index [${rowIndex}][${colIndex}]: ${value}`;
                } else {
                    outputField.value = 'Invalid column index!';
                }
            } else {
                outputField.value = 'Invalid row index!';
            }
        } else {
            outputField.value = 'Enter valid indices in the format row,column!';
        }
    } else {
        outputField.value = 'Enter indices!';
    }
};

document.addEventListener('DOMContentLoaded', onPageLoad);
