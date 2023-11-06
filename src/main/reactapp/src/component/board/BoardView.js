
import { useSearchParams, Link } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';

export default function BoardView( props ){

    // 쿼리스트링 매개변수 호출
    const[ searchParams, setSearchParams ] = useSearchParams();
    const bno = searchParams.get('bno');

    // 현재 게시물의 정보를 가지는 상태관리 변수
    const [ board, setBoard ] = useState({});

    // 개별 게시물 호출 [ 컴포넌트 실행 했을때 최초 한번 ]
    useEffect (()=>{ onGet() },[])
    const onGet = (e) => {
        axios.get("/board/doOne", { params: { bno : bno }})
        .then( r => {
             console.log(r.data);
             setBoard(r.data)
        })
    }

    // 게시물 삭제 [ 실행조건 : 삭제버튼 클릭 했을때 ]
    const onDelete = (e) => {
        axios.delete("/board/do", { params: { bno : bno }})
        .then( r => {
            if(r.data){
                alert('게시물 삭제 성공')
                window.location.href ='/board/list';
            } else {
                alert('게시물 삭제 실패')
            }
        })
    }



    return(<>
        <h3>BoardView 게시물번호 = {bno}</h3>
        <div> 제목 : { board.btitle } </div>
        <div> 내용 : { board.bcontent } </div>
        <button type="button" onClick={onDelete} > 삭제 </button>
        <Link to={"/board/update?bno="+bno} > <button type="button" > 수정 </button> </Link>
     </>)
}