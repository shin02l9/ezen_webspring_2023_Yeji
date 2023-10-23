
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
import Login from './member/Login.js';
import Signup from './member/Signup.js';



export default function Index( props ){
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
                { /* 회원 */ }
                <Route path="/login" element = { <Login />} />
                <Route path="/signup" element = { <Signup />} />
            </Routes>
        </div>
        <Footer />
    </BrowserRouter>
    </>)
}
