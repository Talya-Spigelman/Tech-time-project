import React from 'react'
import { Link, Outlet, useNavigate } from 'react-router-dom';

export default function ChoosingButtons({setStatus}) {

    const desc = "The free, fun, and effective way to learn a language!";
  return (
    <>
    <p className='desc'>{desc}</p>
    
    <div className='buttons'>
        <div className='pink'>
        <button id='pink' onClick={()=>{setStatus(2)}}>GET STARTED</button>
        </div>

        <br></br>

        <div className='white'>
        <button id='white' onClick={()=>{setStatus(1)}}>I ALREADY HAVE AN ACCOUNT</button>
        </div>
    </div>
    
    </>
  )
}
