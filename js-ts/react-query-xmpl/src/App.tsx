import { BrowserRouter, Link, Route, Routes } from 'react-router-dom'
import SuperHeroesPage from './components/Superheroes.page';  
import RQSuperHeroesPage from './components/RQSuperHeroes.page'
import HomePage from './components/Home.page';
import { QueryClient, QueryClientProvider } from "react-query";
import { ReactQueryDevtools} from 'react-query/devtools'
import RQSuperHero from './components/RQSuperHero.page';
//http://localhost:4001/superheroes

const queryCLient = new QueryClient();
// wrap the app in a queryClientProvider a queryCLient client prop


function App() {
  return (
    <QueryClientProvider client={queryCLient} >
    <div >
      <h2 className="text-center font-bold text-red-800 mt-10">
        hello world
      </h2>
      <BrowserRouter>
      <div>
        <nav >
          <ul className='flex justify-around my-5'>
            <li>
              <Link to='/'>Home</Link>
            </li>
            <li>
              <Link to='/super-heroes'>Add Super Heroes</Link>
            </li>
            <li>
              <Link to='/rq-super-heroes'>RQ Super Heroes</Link>
            </li>
          </ul>
        </nav>
        
        <Routes>

          <Route path='/super-heroes' element={<SuperHeroesPage />}/> 
          <Route path='/rq-super-hero/:id' element= {  <RQSuperHero />}/>
          <Route path='/rq-super-heroes' element= {  <RQSuperHeroesPage />}/>
          <Route path='/' element= {  <HomePage />}/>
  
        </Routes>

      </div>
    </BrowserRouter>
    <ReactQueryDevtools position='bottom-right' initialIsOpen={false} />
    </div>
    </QueryClientProvider>
  );
}

export default App;
