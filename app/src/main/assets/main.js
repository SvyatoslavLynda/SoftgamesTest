function sync() {
    JSBridge.makeFullName(document.getElementById("firstName").value,
    document.getElementById("lastName").value);
}

function async() {
    document.getElementById("async").disabled = true;
    JSBridge.calculateUserAge(document.getElementById("dateOfBirthday").value);
    document.getElementById("ageFragment").innerHTML = "Calculating..";
}

function setFullName(nameAndLastName) {
    document.getElementById("nameFragment").innerHTML = nameAndLastName;
}

function setAge(age) {
    document.getElementById("ageFragment").innerHTML = age;
    document.getElementById("async").disabled = false;
}