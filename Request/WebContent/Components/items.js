/**
 * 
 */

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});
// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "RequestAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
 }
 }); 
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).data("req_id"));
 $("#custname").val($(this).closest("tr").find('td:eq(0)').text());
 $("#pname").val($(this).closest("tr").find('td:eq(1)').text());
 $("#department").val($(this).closest("tr").find('td:eq(2)').text());
 $("#p_des").val($(this).closest("tr").find('td:eq(3)').text());
 $("#p_purp").val($(this).closest("tr").find('td:eq(4)').text());
 $("#p_req").val($(this).closest("tr").find('td:eq(5)').text());
});

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "RequestAPI",
 type : "DELETE",
 data : "req_id=" + $(this).data("req_id"),
 dataType : "text",
 complete : function(response, status)
 {
 onItemDeleteComplete(response.responseText, status);
 }
 });
});

// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#req_id").val().trim() == "")
 {
 return "Insert ID.";
 }
// NAME
if ($("#custname").val().trim() == "")
 {
 return "Enter Your Name.";
 } 

// PRoject-------------------------------
if ($("#pname").val().trim() == "")
 {
 return "Enter Project name.";
 }
// department------------------------
if ($("#department").val().trim() == "")
 {
 return "Insert Item department.";
 }
//description------------------------
if ($("#p_des").val().trim() == "")
 {
 return "Insert Item description.";
 }
//pu------------------------
if ($("#p_purp").val().trim() == "")
 {
 return "Insert Here.";
 }
//DESCRIPTION------------------------
if ($("#p_req").val().trim() == "")
 {
 return "Insert Here.";
 }
return true;
}

function onItemSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 

 $("#hidItemIDSave").val("");
 $("#formItem")[0].reset();
}

function onItemDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divItemsGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}   
 