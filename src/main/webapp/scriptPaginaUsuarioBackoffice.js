const sSenha = document.querySelector('#senhaLogar');
const sEmail = document.querySelector('#emailLogar');
const btnLogar = document.querySelector('#btnLogar');

console.log("Olá !");

btnLogar.onclick = e => { //botao de salvar os dados da inclusao
  	
  	console.log("Olá mundo!");
var JSONObject= {"email":sEmail, "senha":sSenha};
var jsonData = JSON.parse( JSONObject );    

var request = $.ajax({
url: "/login",
type: "POST",
data: jsonData,
dataType: "json"
});  

   
}

