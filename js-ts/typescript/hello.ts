interface human {
    name : string;
    age : number;
}

function foo<T extends keyof human>(t : T):void {
    console.log(t)
}

foo("age")

    