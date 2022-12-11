
let req = new XMLHttpRequest();

const url = 'http://127.0.0.1:5500/test.json';

req.open("get",url);

req.responseType = 'json';

req.send();

let obj = req.response;

//those things are bullshit, use the fetch api instead !!

url = 'https://api.openweathermap.org/data/2.5/weather?APPID=71d90f3a0d75b4ffcd687686c145742c&q=City';

fetch( url,{
    mode: 'cors'
}).then( function(response){

}).catch( function(error) {

});