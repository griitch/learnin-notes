import React from 'react'
import {useQuery} from 'react-query'
import axios from 'axios';
import {Link} from 'react-router-dom'

function RQSuperHeroesPage() {

  // 1st arg query identifier
  // 2nd arg function that returns a promise that resolves
  // to the data
  const { isLoading,
    refetch,
    isFetching,
    data,
    isError,
    error} = useQuery('super-heroes', () => { 
   return axios.get("http://localhost:4001/superheroes")
  },{
    enabled : false,
    staleTime : 10000, // default : 0
    // stale time : duration until a query transitions from fresh to stale, while the query is
    // fresh, data will only be read from the cache, no network calls, if the query is stale
    // data will still be read from the cache, but a background fetch can happeb

    cacheTime : 10000, // defaault 5 mins
    // duration until inactive queries will be removed from the cache
    // inactive : no observers (no mounted components that use the query)
    refetchOnMount : false,
    refetchOnWindowFocus : false,
    
    // refetchInterval : 2000, refetch every 2 seconds
    // refetchIntervalInBackground : true, refetch even if window is not focused

    //select : (data) => data.data.map((hero:hero) => hero.name ),
    // select used to transform the data we get from the query
    
    
  });


  console.log({isFetching,isLoading})


  if(isError) {
    return <h2>{(error as Error).message}</h2>
  }

  if( isLoading || isFetching) {
    return <h1>loading</h1>
  }
  return (
    <>
      <h2>Superheroes</h2>
      <button
      onClick = {()=>refetch()}
      className='px-5 py-2 mx-auto rounded-md bg-green-800 text-white hover:bg-green-900 ' >fetch</button>
      <ul>
      {
       data?.data.map((hero:hero) => <Link  key={hero.name} to={`/rq-super-hero/${hero.id}`}>
         <li>{hero.name}</li>
       </Link>
         ) 
       // commented is data after transforming it w select
       // data?.map((heroname:string) => <li key={heroname}>{heroname}</li>)
      }
      </ul>
    </>
  )
}


export default RQSuperHeroesPage 