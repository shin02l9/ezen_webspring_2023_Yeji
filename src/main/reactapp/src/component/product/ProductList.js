
import axios from 'axios';
import { useEffect , useState } from 'react'

import CategoryWrite from './CategoryWrite.js';

export default function ProductList( props ){

    // 0. 데이터 담아둘 상태 변수
    let [ poductList, setProductList ] = useState([]);

    // 1. 제품호출
    const onPrintProduct = (e) => {
        axios.get( '/product/productGet')
            .then( r => {
                if( r.data ){
                    alert( '제품 출력 성공 ');
                    console.log( r.data );
                    setProductList(r.data);
                } else { alert ( '제품 출력 실패 '); }
            });
    };
    // 2. 컴포넌트 실행될때, 수정될때, 삭제될때
    useEffect ( () => {  onPrintProduct()  } , []);


    return(<>
        <h3>제품 목록</h3>
        <table className="printProduct">
            <tr>
                <th> 제품명  </th>
                <th> 대표이미지  </th>
                <th> 카테고리명  </th>
                <th> 제품설명  </th>
                <th> 제품가격  </th>
                <th> 제품상태  </th>
                <th> 제품재고  </th>
                <th> 비고  </th>
            </tr>
            {
            poductList.map( (p) => {
                return(<>
                    <tr>
                        <td> { p.pname }  </td>
                        <td>
                            <img src={"http://localhost:80//static/media/"
                                        +p.imgDtoList[0].uuidFileName} />
                        </td>
                        <td> { p.categoryDto.pcno }  </td>
                        <td> { p.pcommnet}  </td>
                        <td> { p.pprice.toLocaleString() }  </td>
                        <td> { p.pstate }  </td>
                        <td> { p.pstock }  </td>
                        <td>
                            <button type="button"> 수정 </button>
                            <button type="button"> 삭제 </button>
                        </td>
                    </tr>
                </>);
            })
            }
        </table>




    </>)
}