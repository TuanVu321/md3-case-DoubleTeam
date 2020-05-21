let locBtnSubmit = document.getElementById("submitBtn");

let locUsername = document.getElementById("usrInput");
let locFeedbackUsername = document.getElementById("feedbackUsername");

let locPassword = document.getElementById("pwd");
let locFeedbackPassword = document.getElementById("feedbackPassword");

function checkUsername(event) {
    let username = locUsername.value;
    let result = username.match(/[^a-zA-Z0-9_\\. ]/);
    if (username.length < 5 || result != null) {
        locFeedbackUsername.style.display = "block";
        locFeedbackUsername.style.visibility = "visible";
        locFeedbackUsername.className = "feedback";
        locFeedbackUsername.textContent = "Chiều dài username quá ngắn hoặc chứa kí tự đặc biệt !!!";
        event.preventDefault();
    } else {
        locFeedbackUsername.style.display = "none";
        locFeedbackUsername.style.visibility = "hidden";
    }
}

function tipUsername() {
    locFeedbackUsername.className = "tip";
    locFeedbackUsername.textContent = "Username phải có ít nhất 5 kí tự và không chứa các kí tự đặc biệt";
}

function checkPassword(event) {
    let password = locPassword.value;
    let result = password.match(/[^a-zA-Z0-9_\\. ]/);
    if (password.length < 5 || result != null) {
        locFeedbackPassword.style.display = "block";
        locFeedbackPassword.style.visibility = "visible";
        locFeedbackPassword.className = "feedback";
        locFeedbackPassword.textContent = "Chiều dài password quá ngắn hoặc chứa kí tự đặc biệt !!!";
        event.preventDefault();
    } else {
        locFeedbackPassword.style.display = "none";
        locFeedbackPassword.style.visibility = "hidden";
    }
}

function tipPassword() {
    locFeedbackPassword.className = "tip";
    locFeedbackPassword.textContent = "Password phải có ít nhất 5 kí tự và không chứa các kí tự đặc biệt";
}

tipUsername();
tipPassword();
locBtnSubmit.addEventListener("click", function(event){
    checkUsername(event);
    checkPassword(event);
}, false);