/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function calculateFunction(){
         let dates = document.querySelectorAll(".my-date > i")
    for(let i = 0 ; i <  dates.length ; i++)
    {
        let d = dates[i]
        d.innerText = moment(d.innerText).fromNow()
    }
 
}

function calculateFunctionForPost()
{
         let dates = document.querySelectorAll(".my-datePost > i")
    for(let i = 0 ; i <  dates.length ; i++)
    {
        let d = dates[i]
        d.innerText = moment(d.innerText).fromNow()
    }
}
function calculateFunctionNotify()
{
         let dates = document.querySelectorAll(".my-dateNotify > i")
    for(let i = 0 ; i <  dates.length ; i++)
    {
        let d = dates[i]
        d.innerText = moment(d.innerText).fromNow()
    }
}