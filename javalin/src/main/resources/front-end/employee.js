let taken = JSON.parse(getVar());
if(taken == null){
              window.location.href = "index.html";
}
window.onload = func(taken);


function func(um){
    console.log(um);
    let test = document.getElementById("hello");
    let fill = document.getElementById("table");
    test.innerHTML = "Good to see you, "  + um.username + "!";

    let tickets = um.ticketList;
    for(let i=0; i<um.ticketList.length;i++){
        switch(um.ticketList[i].status){
            case 1:
                um.ticketList[i].status = 'Unresolved'
            break;
            case 2:
                um.ticketList[i].status = 'Approved'
            break;
            case 3:
                um.ticketList[i].status = 'Denied'
            break;
    }
    switch(um.ticketList[i].type){
        case 1:
        um.ticketList[i].type = 'Lodging'
        break;
        case 2:
        um.ticketList[i].type = 'Travel'
        break;
        case 3:
        um.ticketList[i].type = 'Food'
        break;
        case 4:
        um.ticketList[i].type = 'Other'
        break;
    }
        fill.innerHTML += '<tr><td data-column="First Name">' + tickets[i].type + '</td><td data-column="Last Name">' + tickets[i].amount + '</td><td data-column="Job Title">' + tickets[i].timeSubmitted + '</td><td data-column="First Name">' + tickets[i].timeResolved + '</td><td data-column="Twitter">' + tickets[i].status + '</td><td data-column="Message">' + tickets[i].message + '</td></tr>';
    }
}

async function add(event){

    let account = taken;

    let ticket = document.getElementsByName("ticket")[0].value;
    let amount = document.getElementsByName("amount")[0].value;
    let message = document.getElementsByName("box")[0].value;

    event.preventDefault();

     let url = 'http://localhost:8002/api/queryparam-add/?id=' + account.id + '&amount=' + amount + '&message=' + message + '&type=' + ticket;
         try {
             let res = await fetch(url);
             const data = await res.json();
            console.log("Here")
            console.log(data)

         } catch (error) {
             console.log(error);
         }
         var today = new Date();
var dd = String(today.getDate()).padStart(2, '0');
var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
var yyyy = today.getFullYear();

today = yyyy + '-' + mm + '-' + dd;

if(ticket == 1){
    ticket = "Lodging"
}else if(ticket == 2){
    ticket = "Travel"
}else if(ticket == 3){
    ticket = "Food"
}else if(ticket == 4){
    ticket = "Other"
}
         let fill = document.getElementById("table");
         fill.innerHTML += '<tr><td data-column="First Name">' + ticket + '</td><td data-column="Last Name">' + amount + '</td><td data-column="Job Title">' + today + '</td><td data-column="First Name">' + null + '</td><td data-column="Twitter">' + "Unresolved" + '</td><td data-column="Message">' + message + '</td></tr>';

 }

  function logout(){
               wipeVar();
               window.location.href = "index.html";
  }