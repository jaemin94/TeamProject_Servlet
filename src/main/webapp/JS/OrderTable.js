// ordertable.js
var $TABLE = $('#table');
var $BTN = $('#export-btn');
var $EXPORT = $('#export');

$('.table-add').click(function () {
  var $clone = $TABLE.find('tr.hide').clone(true).removeClass('hide table-line');
  $TABLE.find('table').append($clone);
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


// ordertable.js
document.getElementById('select-all-checkbox').addEventListener('change', function() {
  var checkboxes = document.querySelectorAll('.li input[type="checkbox"]');
  var selectAllCheckbox = document.getElementById('select-all-checkbox');

  checkboxes.forEach(function(checkbox) {
    checkbox.checked = selectAllCheckbox.checked;
  });
});

document.getElementById('edit_button').addEventListener('click', function() {
  var selectedOrderIds = [];
  var checkboxes = document.querySelectorAll('.li input[type="checkbox"]');

  checkboxes.forEach(function(checkbox) {
    if (checkbox.checked) {
      var orderId = checkbox.parentElement.querySelector('span:nth-child(1)').innerText;
      selectedOrderIds.push(orderId);
    }
  });

  // 선택된 주문 ID에 대한 수정 기능 실행
  // selectedOrderIds 배열을 이용하여 선택된 주문 ID를 전달하고 수정을 수행

  // 예시: 선택된 주문 ID를 콘솔에 출력
  console.log(selectedOrderIds);
});

document.getElementById('delete_button').addEventListener('click', function() {
  var selectedOrderIds = [];
  var checkboxes = document.querySelectorAll('.li input[type="checkbox"]');

  checkboxes.forEach(function(checkbox) {
    if (checkbox.checked) {
      var orderId = checkbox.parentElement.querySelector('span:nth-child(1)').innerText;
      selectedOrderIds.push(orderId);
    }
  });

  // 선택된 주문 ID에 대한 삭제 기능 실행
  // selectedOrderIds 배열을 이용하여 선택된 주문 ID를 전달하고 삭제를 수행

  // 예시: 선택된 주문 ID를 콘솔에 출력
  console.log(selectedOrderIds);
});