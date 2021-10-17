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

function postChartFromCategory(id , cateLabels=[] , cateInfo=[])
{
     let colors = []
    for(let i = 0 ;  i < cateInfo.length ; i++)
        colors.push(generateColor()) 
    
  const data = {
  labels: cateLabels,
  datasets: [{
    label: 'THỐNG KÊ SỐ LƯỢNG BÀI VIẾT THEO TỪ KHÓA DANH MỤC THEO THOI GIAN',
    data: cateInfo,
    backgroundColor: colors,
     borderColor: colors,
   borderWidth: 1
  }]
    };
        const config = {
          type: 'bar',
          data: data,
        };

        let ctx = document.getElementById(id).getContext("2d")
        new Chart(ctx,config)
    
}

function likeChart(id , cateLabels=[], likeInfo=[])
{
    
    let colors =[]
    for(let i = 0 ; i < likeInfo.length;i++)
        colors.push(generateColor())
    
    const data = {
        labels: cateLabels,
        datasets: [{
          label: 'Thống kê số lượng like theo Danh Mục',
          data: likeInfo,
          backgroundColor: colors ,
          hoverOffset: 4
        }]
    };
    
        const config = {
          type: 'pie',
          data: data,
        };
        let ctx = document.getElementById(id).getContext("2d")
        new Chart(ctx,config)
}

function commentChart(id , cateLabels=[], commentInfo=[])
{
    let colors =[]
    for(let i = 0; i < commentInfo.length; i++)
        colors.push(generateColor())
        const data = {
      labels: cateLabels,
      datasets: [{
        label: 'Thống kê số lượng bình luận theo danh mục',
        data: commentInfo,
        backgroundColor: colors
      }]
    };
    const config = {
        type: 'polarArea',
        data: data,
        options: {}
      };
      
      let ctx = document.getElementById(id).getContext("2d");
      new Chart(ctx,config);
    
}

function commentDayMonthChart(id,cateLabels=[],commentInfo=[])
{
    
      let colors = []
        for(let i = 0 ;  i < commentInfo.length ; i++)
            colors.push(generateColor()) 
    
    const data = {
        labels: cateLabels,
        datasets: [{
          label: 'THỐNG KÊ SỐ LƯỢNG Bình Luận THEO TỪ KHÓA DANH MỤC THEO THOI GIAN',
          data: commentInfo,
           borderColor: colors,
         tension: 0.1
    }]
      };
       const config = {
            type: 'line',
            data: data,
          };

        let ctx = document.getElementById(id).getContext("2d")
        new Chart(ctx,config)
}

function reportChart(id,reportPostLabels=[],reportPostInfo=[],reportCommentInfo=[])
{
     let colors1 = []
        for(let i = 0 ;  i < reportPostInfo.length ; i++)
            colors1.push(generateColor()) 
       let colors2 = []
        for(let i = 0 ;  i < reportCommentInfo.length ; i++)
            colors2.push(generateColor()) 
        
            const data = {
         labels: reportPostLabels,
         datasets: [{
           label: 'Thống kê số lượng báo cáo bài viết theo danh mục',
           data: reportPostInfo,
           fill: true,
           backgroundColor: 'rgba(255, 99, 132, 0.2)',
           borderColor: 'rgb(255, 99, 132)',
           pointBackgroundColor: 'rgb(255, 99, 132)',
           pointBorderColor: '#fff',
           pointHoverBackgroundColor: '#fff',
           pointHoverBorderColor: 'rgb(255, 99, 132)'
         }, {
           label: "Thống kê số lượng báo cáo bình luận theo danh mục",
           data: reportCommentInfo,
           fill: true,
           backgroundColor: 'rgba(54, 162, 235, 0.2)',
           borderColor: 'rgb(54, 162, 235)',
           pointBackgroundColor: 'rgb(54, 162, 235)',
           pointBorderColor: '#fff',
           pointHoverBackgroundColor: '#fff',
           pointHoverBorderColor: 'rgb(54, 162, 235)'
         }]
       };
       
       const config = {
            type: 'radar',
            data: data,
            options: {
              elements: {
                line: {
                  borderWidth: 3
                }
              }
            },
          };
       
        let ctx = document.getElementById(id).getContext("2d")
        new Chart(ctx,config)
    
}
