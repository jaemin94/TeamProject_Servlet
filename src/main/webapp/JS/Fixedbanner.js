/**@charset "UTF-8";
 * 
 */
// 이미지 배열
const closeButton = document.querySelector('.close-button');
const container = document.querySelector('.swiper-container');

closeButton.addEventListener('click', function() {
  container.style.display = 'none';
});

