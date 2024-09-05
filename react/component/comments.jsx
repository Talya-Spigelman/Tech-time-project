import React from 'react'
export default function Comments() {
    const comments =[{id:1, userName:"@jf567gg" ,desc:"cool!!"}, {id:2, userName:"@michi456" ,desc:"nice"}];

    // const commentsList = comments.map( (comment, index) => (
    //     <li id={comment.id} key={index}>{comment.desc}</li> 
        
    // ))

  return (
    <>
    <ul >
      {
        comments.map( (comment, index) => (
          <li  className='post' id={comment.id} key={index}>{comment.userName}<br></br> {comment.desc}</li> 
          
      ))
      }

    </ul>
    
    
    
    </>
  )
}




