const dict : { [key : string] : string}  = {};

const cypher = "iukmfwfipiczlacaudki";
const clear = "sinceres salutations";
const cypher2 = "tdk.dlws";
const clear2 = "bonjour.";

for (let i = 0; i < cypher.length; i++) {
  let letter = cypher[i];
  if (/\s/.test(letter)) letter = "_";
  dict[letter] = clear[i];
}
for (let i = 0; i < cypher2.length; i++) {
  let letter = cypher2[i];
  if (/\s/.test(letter)) letter = "_";
  dict[letter] = clear2[i];
}

function decrypt(message : string) : string {

  let clear = "";
  for (let i = 0 ; i < message.length ; i++ ) {
    clear += dict[message[i]] || "_"
  }
  return clear
}

console.log(decrypt("tdk.dlwsptwlmfp chkfpfaptcayckpidkapzcpyfyfprfwidkkfspiukmfwfipiczlacaudkis"))