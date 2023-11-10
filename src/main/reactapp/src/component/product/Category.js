import axios from 'axios';
import { useEffect , useState } from 'react'


export default function Category( props ){
    console.log( 'Category props : ')
    console.log(props)

    const category = props.category;






    return(<>
        <div style={{ display:'flex'}} >
            <div> { category.pcno } : { category.pcname } </div>
            <div>
                <button type="button"> 수정 </button>
                <button onClick={ (e)=>{props.deleteCategory( e, category.pcno )} } type="button"> 삭제 </button>
            </div>
         </div>


    </>)
}
