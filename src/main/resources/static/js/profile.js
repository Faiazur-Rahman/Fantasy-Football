function updateFirstName(){
    var modal = document.getElementById("modal1");
    var btn = document.getElementById("firstName");
    var span = document.getElementsByClassName("close")[0];
    btn.onclick = function() {   modal.style.display = "block"; }
    span.onclick = function() {   modal.style.display = "none"; }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        } }

}
function updateLastName(){
    var modal = document.getElementById("modal2");
    var btn = document.getElementById("lastName");
    var span = document.getElementsByClassName("close")[1];
    btn.onclick = function() {   modal.style.display = "block"; }
    span.onclick = function() {   modal.style.display = "none"; }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        } }

}
function updatePassword(){
    var modal = document.getElementById("modal3");
    var btn = document.getElementById("password");
    var span = document.getElementsByClassName("close")[2];
    btn.onclick = function() {   modal.style.display = "block"; }
    span.onclick = function() {   modal.style.display = "none"; }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        } }
}
function validateform(){
    let password = document.changePassword.password.value;
    let len = password.length;
    console.log(password);
    console.log(len);
    if(len<8){
        document.getElementById("error").innerHTML="*Password length greater than 8";
        return false;
    }
    let letter = 0, number = 0, special = 0;
    for(let i=0; i<len; i++){
        if(password[i]>="A" && password[i]<="Z") letter++;
        else if(password[i]>="a" && password[i]<="z") letter++;
        else if(password[i]>="0" && password[i]<="9") number++;
        else special++;
    }
    if(letter==0 || number==0 || special==0){
        document.getElementById("error").innerHTML="*Password is not correct!";
        return false;
    }
    let cpassword = document.changePassword.cpassword.value;
    if(password != cpassword){
        document.getElementById("passworderror").innerHTML="*Password Does Not Match";
        return false;
    }
    return true;
}
