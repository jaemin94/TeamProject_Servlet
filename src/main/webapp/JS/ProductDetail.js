/** @charset "UTF-8";
 * 
 */

let swiperBtn = document.getElementById("swiperWrapper");
let gridBtn = document.getElementById("gridContainer");
	

 while(true)
 {
	 if(swiperBtn || gridBtn)
	 {
		 swiperBtn.addEventListener("click",function(){
			 window.location.href="TeamProject2/product/ProductDetail.jsp"
			 
		 });
		 gridBtn.addEventListener("click",function(){
			 window.location.href="TeamProject2/product/ProductDetail.jsp"
		 });
		 break;
	 }
	 
 }