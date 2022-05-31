new gridjs.Grid({
    columns: ["Name", "Email", "Phone - Number"],
    sort: true,
    pagination: true,
    fixedHeader: true,
    height: '400px',
    data: [
        ["김개똥", "john@example.com", "(353) 01 222 3333"],
        ["이만두3433", "mark@gmail.com", "(01) 22 888 4444"],
        ["홍길동", "eoin@gmail.com", "0097 22 654 00033"],
        ["권상우  aa", "sarahcdd@gmail.com", "+322 876 1233"],
        ["이만두3433", "mark@gmail.com", "(01) 22 888 4444"],
        ["홍길동", "eoin@gmail.com", "0097 22 654 00033"],
        ["권상우  aa", "sarahcdd@gmail.com", "+322 876 1233"],
        ["이만두3433", "mark@gmail.com", "(01) 22 888 4444"],
        ["홍길동", "eoin@gmail.com", "0097 22 654 00033"],
        ["권상우  aa", "sarahcdd@gmail.com", "+322 876 1233"],
        ["이만두3433", "mark@gmail.com", "(01) 22 888 4444"],
        ["홍길동", "eoin@gmail.com", "0097 22 654 00033"],
        ["권상우  aa", "sarahcdd@gmail.com", "+322 876 1233"],
        ["이만두3433", "mark@gmail.com", "(01) 22 888 4444"],
        ["홍길동", "eoin@gmail.com", "0097 22 654 00033"],
        ["권상우  aa", "sarahcdd@gmail.com", "+322 876 1233"],
        ["장동건  44", "afshin@mail.com", "(353) 22 87 8356"]
    ]
}).render(document.getElementById("wrapper"));