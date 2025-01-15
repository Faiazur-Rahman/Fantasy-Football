
// Password Showing Function
function showPassword() {
    let pass = document.getElementById("password");
    if (pass.type === "password") {
        pass.type = "text";
    } else {
        pass.type = "password";
    }
}
function validateform(){
    let password = document.signupForm.password.value;
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
    return true;
}
function confirmPassword(){
    let password = document.passwordForm.password.value;
    let cpassword = document.passwordForm.cpassword.value;
    if(cpassword != password){
        document.getElementById("passworderror").innerHTML = "Password does not match!";
        return false;
    }
    return true;
}
