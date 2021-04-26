let taken2 = JSON.parse(getVar());
//console.log(JSON.parse(taken2))
//start(JSON.parse(taken2))

if(taken2 == null){
              window.location.href = "index.html";
}

window.onload = start();

function start(event){
    console.log(taken2);
    console.log("hey");

    let tests = document.getElementById("hellos");
    let fill = document.getElementById("tables");
    tests.innerHTML = "Good to see you, "  + taken2.username + "!";
    fill.innerHTML = "";

    let tickets = taken2.ticketList;

    console.log(tickets)

    if(event ==1){
    }
    if(event ==2){
        tickets = tickets.filter(ticket => ticket.status == "Denied" );
          console.log(tickets)
    }
    if(event ==3){
        tickets = tickets.filter(ticket => ticket.status == "Approved" );
        console.log(tickets)
    }
    if(event ==4){
        tickets = tickets.filter(ticket => ticket.status == "Unresolved" );
        console.log(tickets)
    }

    console.log(tickets[0].ticketID)
    for(let i=0; i<taken2.ticketList.length;i++){
        var ticketID= tickets[i].statusID;
        switch(taken2.ticketList[i].status){
            case 1:
                taken2.ticketList[i].status = 'Unresolved'
            break;
            case 2:
                taken2.ticketList[i].status = 'Approved'
            break;
            case 3:
                taken2.ticketList[i].status = 'Denied'
            break;
    }
    switch(taken2.ticketList[i].type){
        case 1:
            taken2.ticketList[i].type = 'Lodging'
        break;
        case 2:
            taken2.ticketList[i].type = 'Travel'
        break;
        case 3:
            taken2.ticketList[i].type = 'Food'
        break;
        case 4:
            taken2.ticketList[i].type = 'Other'
        break;
    }
        fill.innerHTML += '<tr><td>' + tickets[i].id + '</td><td>' + tickets[i].type + '</td><td>' + tickets[i].amount + '</td><td>' + tickets[i].timeSubmitted + '</td><td>' + tickets[i].timeResolved + '</td><td>' + tickets[i].status + '</td><td>' + tickets[i].message + '</td><td><button type="button" onclick="setStatus(2, ' + ticketID + ')">Approve</button></td><td><button type="button" onclick="setStatus(3, ' + ticketID + ')">Deny</button></td></tr>';
    }
}

async function setStatus(eventStatus, eventID){
    //console.log(event);
    let accountID = taken2;

    //event.preventDefault();

     let url = 'http://localhost:8002/api/queryparam-update/?id=' + eventID + '&status=' + eventStatus + '&resolvedby=' + accountID.id ;
         try {
             let res = await fetch(url);
             const data = await res.json();


         } catch (error) {
             console.log(error);
         }
         let temp = taken2;
         for(let i=0;i<temp.ticketList.length;i++){
             if(temp.ticketList[i].statusID == eventID){
                temp.ticketList[i].status = eventStatus;
             }
         }
         start(temp)
}

 function logout(){
              wipeVar();
              window.location.href = "index.html";

 }