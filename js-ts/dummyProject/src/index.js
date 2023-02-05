// import _ from 'lodash'
// import './style.css'
// import koalaImage from './koala.jpg'
// import printcrap from './printintcrap';

function component() {
    const element = document.createElement('div');
  
    element.innerHTML = _.join(['Hello', 'webpack'], ' ');

    const btn = document.createElement("button");
    btn.innerText = "click and checc console";
    btn.addEventListener("click", printcrap);

    const myimage = new Image();
    myimage.src = koalaImage;

    element.appendChild(myimage);
    element.appendChild(btn); 
  
    return element;
  }
  
  document.body.appendChild(component());

let gg = 78;
class human {
  constructor(){
    this.gritch = 7;
  }

  foo(...args) {
    console.log(args[2])
  }

}

let baz = () => {
  console.log("babel will get rid of this arrow func and make it a regular func")
}