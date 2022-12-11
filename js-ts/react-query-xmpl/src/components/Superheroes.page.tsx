import axios, { AxiosResponse } from "axios"
import {useState} from 'react'
import { Query, useMutation, useQueryClient } from 'react-query'
import { QueryFilters } from "react-query/types/core/utils"
import { Link} from 'react-router-dom'


function Superheroes() {
  const [name, setName] = useState('')
  const [alterEgo, setAlterEgo] = useState("");

  const { mutate : addHero, isLoading, isSuccess, data } =  useAddHeroData();

  const handleSubmit = (e : React.FormEvent) =>{
    e.preventDefault();
    addHero({name,alterEgo});
  }
  
  if(isLoading) {
    return <h2>sending data to thee server....</h2>
  }

  if(isSuccess) {
    return (<>
    <h2>added successfully</h2>
    <p>{JSON.stringify(data?.data)}</p>
    <Link to={`/rq-super-hero/${data.data.id}`}>go check it</Link>
    <br />
    <Link to={`/rq-super-heroes`}>go check all</Link>
    </>)

  }

  return (
    <form 
    onSubmit={handleSubmit}
    className="flex justify-around items-center py-2 mt-10">
    
      <div>
      <label className="pr-4" >name</label>
      <input className="border border-black" value={name} onChange={e=>setName(e.target.value)}  />
      </div>
    <div>

      <label className="pr-4" >alter ego</label>
      <input className="border border-black" value={alterEgo} onChange={e=>setAlterEgo(e.target.value)} />
    </div>

    <button className="bg-green-400 px-9 py-3 rounded-sm">Add</button>
    </form>
  )
}


const addHero = (hero : heroDto ) : Promise<AxiosResponse<hero>> => { 
  return axios.post("http://localhost:4001/superheroes",hero)
}


const useAddHeroData = () => {
  const queryCLient = useQueryClient();

  return useMutation(addHero,
    { onSuccess : (e) => { 

        const foo = queryCLient.getQueryData("super-heroes")
        console.log(foo)
        queryCLient.setQueryData("super-heroes",(old) => {   
          if(old) {
            return  {...(old as any), data : [...(old as any).data, e.data]}
          } else {
            return undefined;
          }
          
         });
      },
    });
}


export default Superheroes

type heroDto = {
  name : string,
  alterEgo : string
}