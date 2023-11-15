
/*
    Index : 여럿 컴포넌트들을 연결하는 최상위 컴포넌트

*/
// 리액트 라우터 라이브러리 호출
import {BrowserRouter, Routes, Route, Link } from 'react-router-dom';

import Header from './Header.js';
import Main from './Main.js';
import Footer from './Footer.js';
import ExampleList from './example/ExampleList.js';
import style from './Index.css';

// 다른 폴더에 있는 컴포넌트 호출하는 방법
import 컴포넌트1 from './example/day01/1_컴포넌트.jsx';
import 컴포넌트2 from './example/day01/2_컴포넌트.jsx';
import 컴포넌트3 from './example/day01/3_컴포넌트.jsx';
import 컴포넌트4 from './example/day01/4_컴포넌트.jsx';
import Css컴포넌트 from './example/day02/1_CSS적용컴포넌트.js';
import CommentList from './example/day02/CommentList.js';
import 과제day01 from './example/day01/과제1_도서목록.jsx';
import TodoList from './example/day02/과제1_할일목록/TodoList.js';
import Axios컴포넌트 from './example/day04/1_Axlos컴포넌트.js';

// member
import Login from './member/Login.js';
import Signup from './member/Signup.js';
import Info from './member/Info.js';

//board
import BoardList from './board/BoardList.js';
import BoardWrite from './board/BoardWrite.js';
import BoardView from './board/BoardView.js';
import BoardUpdate from './board/BoardUpdate.js';

//product
import ProductAdmin from './product/ProductAdmin';



// 훅 라이브러리
import { useState, useEffect, useRef } from 'react';
/* MUI 라이브러리 호출 */
import {  useSnackbar } from 'notistack'; // npm i notistack


export default function Index( props ){
    /* MUI 라이브러리 객체 호출  */
    const { enqueueSnackbar } = useSnackbar();

    // 웹 소켓 통신 ==================================================
    //useEffect(()=>{
        let 클라이언트소켓 = new WebSocket("ws://localhost:80/chat");
        console.log(클라이언트소켓);

        // 1. 서버소켓과 연동 성공했을때. 이후 행동/메소드 정의
        클라이언트소켓.onopen = (e) => { console.log(e); };
        // 2. 서버소켓과 세션 오류가 발생했을때. 이후 행동/메소드 정의
        클라이언트소켓.onerror = (e) => { console.log(e); };
        // 3. 서버소켓과 연동이 끊겼을때. 이후 행동/메소드 정의
        클라이언트소켓.onclose = (e) => { console.log(e); };
        // 4. 서버소켓으로부터 메세지를 받았을때. 이후 행동/메소드 정의
        클라이언트소켓.onmessage = (e) => {
            console.log(e);
             enqueueSnackbar('This is a success message!', { variant : 'success' });
        };

        // 5. 메세지 보내기
        //클라이언트소켓.send('안녕')

    //},[])

    // 2. 클라이언트 소켓 메세지 전송
    const msgSend = (e) => { 클라이언트소켓.send("안녕")}

    // 웹 소켓 통신 end ==============================================






    return(<>
    <BrowserRouter>
        <Header />
        <div className="RoutesBox">
            <Routes>
                { /* 메인  */}
                <Route path="/"  element = { < Main /> } />

                { /* 예제 및 과제  */ }
                <Route path="/example" element = { <ExampleList />} />
                    <Route path="/example/day01/컴포넌트1" element = { <컴포넌트1 />} />
                    <Route path="/example/day01/컴포넌트2" element = { <컴포넌트2 />} />
                    <Route path="/example/day01/컴포넌트3" element = { <컴포넌트3 />} />
                    <Route path="/example/day01/컴포넌트4" element = { <컴포넌트4 />} />
                    <Route path="/example/day02/Css컴포넌트" element = { <Css컴포넌트 />} />
                    <Route path="/example/day02/CommentList" element = { <CommentList />} />
                    <Route path="/example/day01/TodoList" element = { <과제day01 />} />
                    <Route path="/example/day02/과제1_할일목록/TodoList" element = { <TodoList />} />
                    <Route path="/example/day04/Axlos컴포넌트" element = { <Axios컴포넌트 />} />
                { /* 회원 */ }
                <Route path="/login" element = { <Login />} />
                <Route path="/signup" element = { <Signup />} />
                <Route path="/info" element = { <Info />} />

                { /* 게시판 */ }
                <Route path="/board/list" element = { <BoardList />} />
                <Route path="/board/write" element = { <BoardWrite />} />
                <Route path="/board/view" element = { <BoardView />} />
                <Route path="/board/update" element = { <BoardUpdate />} />

                {/* 제품 */}
                <Route path='/admin/product' element ={ <ProductAdmin/> }/>

            </Routes>
        </div>
        <Footer />
    </BrowserRouter>
    </>)
}
