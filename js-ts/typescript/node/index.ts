import express from "express";

const app = express();


app.get('/',(req,res) => { 
    req.url = "fobar";
    (req as any).files = "how to handle this w typsecript without casting to any"
})



type foo = {
    foo : string,
    name : string
}

type bar = {
    foo : number,
    name : string,
}



app.listen(2001,() => { console.log("started") })
