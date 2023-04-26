var JSONObject= {"uname":uname, "password":password };
var jsonData = JSON.parse( JSONObject );    

var request = $.ajax({
url: "/login",
type: "POST",
data: jsonData,
dataType: "json"
});  

