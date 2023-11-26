console.log(localStorage.getItem("id"));

let submitLeaveRequest = () => {
  let start = document.getElementById("start").value;
  let end = document.getElementById("end").value;
  let id = localStorage.getItem("id");
  let reason = document.getElementById("reason").value;
  if(end != '' && start != '' && reason != ''){ 
    if(endDateGreater(start, end)){
      console.log(start + " " + end + ' ' + reason);
      fetch('http://localhost:8080/requestleave/' + start + '/' + end + '/' + id
          + '/' + reason.replace(' ', `%20`),
          { method: "POST"});
    }
    alert("Too many days requested");
  }
  else{
    alert("There are empty fields");
  }
}

let endDateGreater = (start, end) => {
  let startDate = new Date(start).getTime();
  let endDate = new Date(end).getTime();
  let userString = localStorage.getItem("userJson");
  let userJson = JSON.parse(userString);
  return start < end && 
      ((endDate - startDate) / 86400000) <= Number(
        Number(userJson.daysofleave));
}