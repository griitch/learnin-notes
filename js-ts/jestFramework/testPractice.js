
function capitalize(s) { 
    return s.charAt(0).toUpperCase() + s.slice(1);
}

function reverseString(s){
    return s.split("").reverse().join("");
}

  
const caesarCipher = (str, shift) => {
    if (shift === 0) return str;
  
    return str.replace(/[a-z]/g, (char) =>
      String.fromCharCode(((char.charCodeAt(0) - 97 + shift) % 26) + 97)
    );
};

const analyze = (arr) => {
    return {
      average: arr.reduce((a, b) => a + b, 0) / arr.length,
      min: Math.min(...arr),
      max: Math.max(...arr),
      length: arr.length,
    };
  };
  

module.exports = {
    capitalize,
    reverseString,
    caesarCipher,
    analyze 
}