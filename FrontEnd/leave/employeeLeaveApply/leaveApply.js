console.log(localStorage.getItem("id"));

let submitLeaveRequest = () => {
  let start = document.getElementById("start").value;
  let end = document.getElementById("end").value;
  let id = localStorage.getItem("id");
  let reason = document.getElementById("reason").value;
  if(end != '' && start != ''){
    console.log(start + " " + end + ' ' + reason);
    fetch('http://localhost:8080/requestleave/' + start + '/' + end + '/' + id
        + '/' + reason.replace(' ', `%20`),
        { method: "POST"});
  }
}