
import { useSearchParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import axios from 'axios';

export default function BoardView( props ){

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

    // 게시글 수정하기
    const onUpdate = (e) => {

        // 1. 폼 가져오기
        let boardForm = document.querySelectorAll('.boardForm')[0];
        let boardFormData = new FormData(boardForm);
        // boardFormData 이 안에은 입력받은 수정할 제목과 내용이 있다.
        // 그러나 이 두가지만 보내면 안됨 그래서 Bno를 속성에 추가하기
        boardFormData.set( 'bno', bno ); // 수정할 게시물의 번호를 속성에 추가한다.
        // 2. 수정 요청하기
        axios.put("/board/do", boardFormData)
        .then( r =>{
            console.log(r);
            if( r.data ){
                alert('게시글 수정 성공')
                window.location.href ='/board/view?bno='+bno;
            } else {alert('게시글 수정 실패')}
        })
    }

    return(<>
        <h3>BoardUpdate 게시물번호 = {bno}</h3>
        <form className="boardForm" >

            <div> 제목 : <input type="text"
                name="btitle" value={ board.btitle }
                onChange={ (e) => {
                    setBoard({...board, btitle : e.target.value })
                } }
            /> </div>

            <div> 내용 : <textarea
                name="bcontent" value={ board.bcontent }
                onChange={ (e) => {
                    setBoard({...board, bcontent : e.target.value })
                } }
            > </textarea></div>

            <button onClick={ onUpdate } type="button" > 수정</button>
        </form>
      </>)
}