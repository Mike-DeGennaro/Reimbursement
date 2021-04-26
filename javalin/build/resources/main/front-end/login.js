
async function hey(event){

   event.preventDefault();
    let fName = document.getElementsByName("fname")[0].value;
    let pass = document.getElementsByName("pass")[0].value;

    console.log('submitted')

    let temp;

    let url = 'http://localhost:8002/api/queryparam-demo/?applejacks=' + fName + '&humanwords=' + pass;
        try {
            let res = await fetch(url);
            const data = await res.json()
            temp = data;
            console.log(data)

        } catch (error) {
            console.log(error);
            let failed = document.getElementById("failed");
            failed.innerHTML = "Oops! Login failed, try again.";
        }

        if(temp.role==1){
            localStorage.setItem('myCat', JSON.stringify(temp));
            employee(temp)
            //employee(temp)
        }else{
            localStorage.setItem('myCat', JSON.stringify(temp));
            admin(temp)
        }
}

function getVar(){
    let cat = localStorage.getItem('myCat');
    return cat;

}
function wipeVar(){
  localStorage.clear();
}

function employee(employee){
    window.location.href = "employee.html";
}

function admin(admin){
    window.location.href = "admin.html";
}

