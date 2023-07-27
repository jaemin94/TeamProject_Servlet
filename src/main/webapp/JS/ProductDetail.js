/** @charset "UTF-8";
 * 
 */

let swiperBtn = document.getElementById("swiperWrapper");
let gridBtn = document.getElementById("gridContainer");
	/*const baseURL = "http://localhost:8080/TeamProject2";*/
	pagePath = null;
	fullURL = null;
	
	

 while(true)
 {
	 
	 if(swiperBtn || gridBtn)
	 {
		 swiperBtn.addEventListener("click",function(){
			 pagePath="/product/ProductDetail.jsp"
			 fullURL = baseURL + pagePath;
			 location.href = fullURL;
			 
			 
		 });
		 gridBtn.addEventListener("click",function(){
			 pagePath="/product/ProductDetail.jsp"
			 fullURL = baseURL + pagePath;
			 location.href = fullURL;
		 });
		 break;
	 }
	 
 }