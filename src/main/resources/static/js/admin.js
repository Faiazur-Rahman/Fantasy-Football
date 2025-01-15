// $(".alert").addClass("in").fadeOut(4500);
//
// /* swap open/close side menu icons */
// $('[data-toggle=collapse]').click(function(){
//     // toggle icon
//     $(this).find("i").toggleClass("glyphicon-chevron-right glyphicon-chevron-down");
// });

var modal = document.getElementById("modal1");
var btn = document.getElementById("firstName");
var span = document.getElementsByClassName("close")[0];
btn.onclick = function() {   modal.style.display = "block"; }
span.onclick = function() {   modal.style.display = "none"; }
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    } }