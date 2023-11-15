
import axios from 'axios';
import { useEffect , useState } from 'react'

import CategoryWrite from './CategoryWrite.js';


export default function ProductWrite( props ){



    // 제품 등록하기
    const onProductAdd = (e) => {
        let productForm = document.querySelectorAll('.productForm')[0];
        let productFormData = new FormData( productForm );

        axios.post( '/product/productPost', productFormData )
            .then( r => {
                if( r.data ){

                    alert( '제품 등록 성공 ');
                    productForm.reset();
                } else { alert ( '제품 등록 실패 ')}
            })

    }



    return(<>
        <div style={{ width:'300px', margin : '0 auto'}}>
            <h3>제품등록</h3>
            <form className="productForm">
                <select name="pcno">
                    {props.categoryList.map( (c) => {
                        return <option key={c.pcno} value={ c.pcno } > {c.pcname } </option>
                    })}
                </select> <br/>
                <input type="text" name="pname" placeholder="제품명" /> <br/>
                <textarea name="pcomment" placeholder="제품설명" > </textarea> <br/>
                <input type="text" name="pprice" placeholder="제품가격" /> <br/>
                <input type="text" name="pstock" placeholder="제품재고" /> <br/>
                <input type="file" name="fileList" multiple  /> <br/> { /* multiple 여러개 첨부 가능*/ }
                <button type="button" onClick={ onProductAdd } > 등록 </button>
            </form>
        </div>
    </>)
}