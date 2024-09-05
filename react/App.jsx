import './App.css'



// import Profil from './component/profil'
import Menu from './component/menu'
import Routing from './component/routing'
import Footer from './component/footer'
import Open from './pages/open/open'
import Login from './component/open/login'
import Signin from './component/open/signin'
import { Box } from '@mui/system'
import { useEffect, useState } from 'react'
import Home from './pages/home'
// import BaseLayout from '../baseLayout'
// import logo from './imges/200.png'
// import { Avatar } from '@mui/material'
// import { fetchUsers } from './service/user'
// import { useEffect, useState } from 'react'
function App() {

  // const [users,setUsers] = useState([]);
  // useEffect(()=>{
  //   fetchUsers().then((response)=>{

  //     console.log("returned from server",response)
  //     setUsers(response.data)
  //   }).catch((error)=>{
  //     console.log(error)
  //   })
  // },[])
  const [isLoged,setIsLoged] = useState(0);


  return (
    <>
    {isLoged == 0 && <Open/>}
    {isLoged == 1 && <Home/>}
    {/* <Menu /> */}

   
   {/* <Login /> */}
   {/* <Signin /> */}
    
{/* <Profil /> */}


      {/* change color mode */}
      {/* <button onClick={() => handel()} style={{background:'none'}}>{isdark?'🌕':'🌑'}</button> */}

    </>
  )
}

export default App
