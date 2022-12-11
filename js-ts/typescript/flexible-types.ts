type foo = {
    name : string;
    [kevin : string] : string | number
}

const fog : foo = {
    name : "am",
    bar : 1,
    vaz : "3"
}

type mapTypeToVoidFunc<T> = {
    [p in keyof T] : () => void
}

type mapTypeToVoidFuncWNameChange<T> = {
    [p in keyof T as `foobar${Capitalize<p & string>}`] : () => void
    // Capitalize is an utility type that gives the same type but w capitalized att names
}

type foovoid = mapTypeToVoidFunc<foo>
// type with the same attribute names, but all of them are void retuning funcs


type gritch = {
    gritch : string,    
    fritch : number,
    foobar : boolean
}

type foobar = mapTypeToVoidFuncWNameChange<gritch>  

const k = typeof fog;