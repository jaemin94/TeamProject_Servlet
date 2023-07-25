/**@charset "UTF-8";
 * 
 */

var $TABLE = $('.table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');
$(document).ready(function() {
    $('#plusbt').click(function () {
        var varTable = $(this).closest('.table'); // 클릭된 버튼의 가장 가까운 부모 요소 중 클래스가 'table'인 요소를 선택
        var varTableLastRow = varTable.find('#tablebodyh'); // 행추가 마지막 row
        var copyRow = varTableLastRow.clone(true); // 행추가 마지막 row 복사

        fncRowReset(copyRow);

        // 행추가 맨 마지막에 append
        varTable.append(copyRow);
    });	
});



$('.table-remove').click(function () {
  $(this).parents('tr').detach();
});

  
$('.table-up').click(function () {
  var $row = $(this).parents('tr');
  if ($row.index() === 1) return; // Don't go above the header
  $row.prev().before($row.get(0));
});

$('.table-down').click(function () {
  var $row = $(this).parents('tr');
  $row.next().after($row.get(0));
});

// A few jQuery helpers for exporting only
jQuery.fn.pop = [].pop;
jQuery.fn.shift = [].shift;

$BTN.click(function () {
  var $rows = $TABLE.find('tr:not(:hidden)');
  var headers = [];
  var data = [];

  // Get the headers (add special header logic here)
  $($rows.shift()).find('th:not(:empty)').each(function () {
    headers.push($(this).text().toLowerCase());
  });

  // Turn all existing rows into a loopable array
  $rows.each(function () {
    var $td = $(this).find('td');
    var h = {};

    // Use the headers from earlier to name our hash keys
    headers.forEach(function (header, i) {
      h[header] = $td.eq(i).text();   
    });

    data.push(h);
  });

  // Output the result
  $EXPORT.text(JSON.stringify(data));
});


document.getElementById('select-all-checkbox').addEventListener('change', function() {
	    var checkboxes = document.querySelectorAll('.order-checkbox');
	    var selectAllCheckbox = document.getElementById('select-all-checkbox');

	    checkboxes.forEach(function(checkbox) {
	      checkbox.checked = selectAllCheckbox.checked;
	    });
});

//-----------------------------------------

document.getElementById("edit_button").addEventListener("click", function() {
  // 선택된 체크박스의 값을 배열로 가져오기
  var checkboxes = document.querySelectorAll(".order-checkbox:checked");
  var selectedOrderIds = [];
  checkboxes.forEach(function(checkbox) {
    selectedOrderIds.push(checkbox.parentElement.parentElement.cells[1].innerText);
  });

  // selectedOrderIds를 hidden input에 할당하여 폼 전송
  document.getElementById("selectedOrderIds").value = JSON.stringify(selectedOrderIds);
  document.getElementById("orderForm").submit();
});
