$(document).ready(function(){
    let locReviewButton = document.getElementById("postreview-btn");
    locReviewButton.addEventListener("click", function(){
        $("#contain-switch").load("../view/post-review-admin.jsp");
    }, false);

    let locAccountButton = document.getElementById("account-btn");
    locAccountButton.addEventListener("click", function(){
        $("#contain-switch").load("../view/account-admin.jsp");
    }, false);
})
