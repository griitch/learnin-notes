import {useQuery, UseQueryOptions, useQueryClient } from 'react-query'
import axios, { AxiosResponse } from 'axios';
import {useParams } from 'react-router-dom'

function RQSuperHero() {

    const {id} = useParams();
    const {isLoading, data, isError, error } = useSuperHeroData(+id!);


   if(isLoading) {
       return <h1>loading....</h1>
   }

   if(isError) {
       return <h1>Error {(error as Error).message}</h1>
   }

  return (
    <div>
        {data?.data.name } - { data?.data.alterEgo}     
    </div>
  )
}


const useSuperHeroData = (id : number, ) => {
    const queryCLient = useQueryClient();
    return useQuery(["super-hero",id], fetchSuperhero, {
        initialData : () => {
            const hero = queryCLient.getQueryData<AxiosResponse<hero[],any>>("super-hero")
            ?.data?.find(h => h.id === id)
            if(hero) {
                return { data : hero};
            } else {
                return undefined
            }
        }
    })
 }
const fetchSuperhero = ({ queryKey } : UseQueryOptions): Promise<AxiosResponse<hero, any>> => { 
const id = queryKey![1];
return axios.get(`http://localhost:4001/superheroes/${id}`)
}

export default RQSuperHero