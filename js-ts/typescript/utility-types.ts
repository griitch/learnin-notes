
interface Human {
    name : string,
    age : number,
    email?: string,
}

type partialHuman = Partial<Human>;
// human but all of its keys are optional

type emailRequiredHuman = Required<Human>
// human but all keys are required

type humanWithEmailOnly = Pick<Human,"email">

type humanWithNameOnly = Omit<Human,"email" | "age">

type MapNameToHuman = Record<"name", Human>

type immutableHuman = Readonly<Human>

// remarque readonly tuples and arrays

type readonlyTuple = Readonly<[string, number, boolean]>;

let bar: readonlyTuple = ["foobar",1,true]
// bar[1] = 4 won't work

const immutableArray = [1,2,4,5,3] as const
// immutableArray[2] = 4 error


// ---------------------------------------------------------

type Name = {
    first : string,
    last : string
}

function addFullName(name : Name) : Name & {fullName : string} {
      return {
          ...name,
          fullName : `${name.first} ${name.last} `
      }
}

function permuteRows<T extends (...args: any[]) => any>(
    iteratorFunc : T, data: Parameters<T>[0][] 
): ReturnType<T>[]
{
    return data.map(iteratorFunc)
}

