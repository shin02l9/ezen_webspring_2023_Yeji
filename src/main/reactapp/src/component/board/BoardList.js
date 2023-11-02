
import axios from 'axios';
import { useState, useEffect } from 'react';

export default function BoardList( props ){

//      axios 사용전 쓴 샘플데이터
//    let rows = [
//        { bno : 1 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 2 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 3 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 4 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 },
//        { bno : 5 , btitle : '안녕', mno : 1 , cdate : '2023-11-02', bview : 0 }
//    ];
    // 0. 컴포넌트 상태변수 관리
    let [ rows, setRows ] = useState([ ]);

    // 1. axios를 이용한 스프링의 컨트롤과 통신
    useEffect ( () => { // -> 컴포넌트가 실행될때 한번만 실행하기
        axios.get("/board/do").then( r => {
            console.log(r.data);
            setRows(r.data); // 응답받은 모든 게시물을 상태 변수에 저장한다.
            // setState : 해당 컴포넌트가 업데이트(새로고침/재랜더링/return재실행)
            // 무한루프에 빠지는 문제점 발생 ㅋㅋㅋㅋㅋㅋㅋ
            // 그래서 useEffect 으로 감싸준다.
        })
    } , [])





    return(<>
        <div>
            <h3> 게시물 목록 </h3>
            <a href="/board/write"> 글쓰기 </a>

            <table className="">
                <tr>
                    <th>번호</th> <th>제목</th> <th>작성자</th>
                    <th>작성일</th> <th>조회수</th>
                </tr>

                {/* 게시물목록 출력*/}
                {
                rows.map( (row) => {
                    return(<>
                        <tr>
                            <td>{row.bno}</td> <td>{row.btitle}</td> <td>{row.mno}</td>
                            <td>{row.cdate}</td> <td>{row.bview}</td>
                        </tr>
                    </>)
                })
                }
            </table>


        </div>
    </>)
}