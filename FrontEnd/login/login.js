let login = async () => {
  localStorage.clear();
  let username = document.getElementById('username').value;
  let password = document.getElementById('password').value;
  let role = document.getElementById('role').value;
  let count = await verifyUser(role, username, password).then(res => res);
  if(role === 'manager' && count === 1){
    localStorage.setItem("managerName", username);
    location.href = "../leave/manager.html";
  }
  else if(role === 'employee' && count === 1){
    localStorage.setItem("employeeName", username);
    localStorage.setItem("id", await getId(role, username, password));
    location.href = "../leave/employeeLeaveApply/leaveApply.html";
  }
  else{
    document.getElementById("error").innerHTML = "User not found";
  }
}

let verifyUser = async (role, username, password) => {
  role = (role === 'manager') ? 'man' : 'emp';
  let response = await fetch('http://localhost:8080/get' + role 
      + 'count/' + username + '/' + password);
  return await response.json();
}

let getId = async (role, username, password) => {
  role = (role === 'manager') ? 'man' : 'emp';
  let response = await fetch('http://localhost:8080/get' + role 
      + '/' + username + '/' + password);
  let user = await response.json();
  console.log(user + ' is logged in');
  let userJson = JSON.stringify(user);
  localStorage.setItem("userJson", JSON.stringify(user));
  return await user.id;
}