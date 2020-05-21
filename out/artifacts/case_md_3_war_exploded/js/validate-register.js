let locUsername = document.getElementById("usrInput");
let locFeedbackUsername = document.getElementById("feedbackUsername");

let locPassword = document.getElementById("pwd");
let locFeedbackPassword = document.getElementById("feedbackPassword");

let locPassword2 = document.getElementById("pwd2");
let locFeedbackPassword2 = document.getElementById("feedbackPassword2");

let locEmail = document.getElementById("email");
let locFeedbackEmail = document.getElementById("feedbackEmail");

let locPhonenumber = document.getElementById("phoneNumber");
let feedbackPhonenumber = document.getElementById("feedbackPhonenumber");

let locRegisterBtn2 = document.getElementById("registerBtn2");

let locCheckbox = document.getElementById("policy");
let locFeedbackPolicy = document.getElementById("feedbackPolicy");

function checkedCheckbox(event) {
    let checked = locCheckbox.checked;
    if(!checked) {
        locFeedbackPolicy.className = "feedback";
        locFeedbackPolicy.textContent = "Bạn chưa đồng ý với các điều khoản của chúng tôi !!!";
        event.preventDefault();
    } else {
        locFeedbackPolicy.textContent = "";
    }
}

function checkPhonenumber(event) {
    let phone = locPhonenumber.value;
    let result = phone.match(/(^\d{10}$)|(^\(\+84\)(\d{9}$))/);
    if (phone.length < 5 || result == null) {
        feedbackPhonenumber.className = "feedback";
        feedbackPhonenumber.textContent = "Định dạng số điện thoại không hợp lệ !!!";
        event.preventDefault();
    } else {
        feedbackPhonenumber.textContent = "";
    }
}

function checkEmail(event) {
    let email = locEmail.value;
    let result = email.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/);
    if (email.length < 5 || result == null) {
        locFeedbackEmail.className = "feedback";
        locFeedbackEmail.textContent = "Định dạng email không hợp lệ !!!";
        event.preventDefault();
    } else {
        locFeedbackEmail.textContent = "";
    }
}

function checkUsername(event) {
    let username = locUsername.value;
    let result = username.match(/[^a-zA-Z0-9_\\. ]/);
    if (username.length < 5 || result != null) {
        locFeedbackUsername.className = "feedback";
        locFeedbackUsername.textContent = "Chiều dài username quá ngắn hoặc chứa kí tự đặc biệt !!!";
        event.preventDefault();
    } else {
        locFeedbackUsername.textContent = "";
    }
}

function tipUsername() {
    locFeedbackUsername.className = "tip";
    locFeedbackUsername.textContent = "Chiều dài username phải có ít nhất 5 kí tự và không chứa các kí tự đặc biệt";
}

function checkPassword(event) {
    let password = locPassword.value;
    let result = password.match(/[^a-zA-Z0-9_\\. ]/);
    if (password.length < 5 || result != null) {
        locFeedbackPassword.className = "feedback";
        locFeedbackPassword.textContent = "Chiều dài password quá ngắn hoặc chứa kí tự đặc biệt !!!";
        event.preventDefault();
    } else {
        locFeedbackPassword.textContent = "";
    }
}

function checkRePassword(event) {
    let password = locPassword.value;
    let password2 = locPassword2.value;

    if (password !== password2) {
        locFeedbackPassword2.className = "feedback";
        locFeedbackPassword2.textContent = "2 mẫu password không giống nhau, hãy nhập lại !!!";
        event.preventDefault();
    } else {
        locFeedbackPassword2.textContent = "";
    }
}

function tipPassword() {
    locFeedbackPassword.className = "tip";
    locFeedbackPassword.textContent = "Password phải có ít nhất 5 kí tự và không chứa các kí tự đặc biệt";
}

tipUsername();
tipPassword();

locRegisterBtn2.addEventListener("click", function(event){
    checkUsername(event);
    checkPassword(event);
    checkRePassword(event);
    checkEmail(event);
    checkPhonenumber(event);
    checkedCheckbox(event);
}, false);
