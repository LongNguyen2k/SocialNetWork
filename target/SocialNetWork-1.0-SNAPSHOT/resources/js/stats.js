/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function generateColor(){
    let r = parseInt(Math.random()*255);
    let g = parseInt(Math.random()*255);
    let b = parseInt(Math.random()*255);
   // dấu huyền
    return` rgb( ${r}, ${g} , ${b} )` 
}

function cateChart(id , cateLabels=[] , cateInfo=[])
{
    let colors = []
    for(let i = 0 ;  i < cateInfo.length ; i++)
        colors.push(generateColor()) 
    
  const data = {
  labels: cateLabels,
  datasets: [{
    label: 'Thống kê bài viết theo danh mục',
    data: cateInfo,
    backgroundColor: colors,
    hoverOffset: 4
  }]
};
        const config = {
          type: 'doughnut',
          data: data,
        };

        let ctx = document.getElementById(id).getContext("2d")
        
        new Chart(ctx,config)
}
