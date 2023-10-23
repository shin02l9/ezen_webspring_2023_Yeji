/*
    컴포넌트 만들기
        - 파일명 아무거나.js 혹은 아무거나.jsx [ 권장 : 컴포넌트명과 동일하게 ]
        - 컴포넌트 원형
            export default function 컴포넌트 ( props ) {
                return(<></>);
            }
        컴포넌트를 랜더링
            - 최상위 랜더링(가장 먼저 랜더링)
                1. index.js
                    import 라우터컴포넌트 from './component/example/day03/1_라우터컴포넌트.js';
                    root.render(<React.StrictMode><라우터컴포넌트 /> </React.StrictMode>);
            - 라우터 : 가상 URL 만들기
                - 실제 라우터 : 연결 경로를 자동으로 전환해주는 기계
                - 리액트 라우터 : 가상 경로 [URL]를 만들어서 컴포넌트를 전환해주는 라이브러리
                        ( 선생님 깃 보기 )
                - 해당 파일에서 외부라이브러리 import
*/
import {BrowserRouter, Routes, Route, Link } from 'react-router-dom'; // react-router-dom 설치 후
import 컴포넌트1 from '../day01/1_컴포넌트'; // 다른 폴더에 있는 컴포넌트 호출하는 방법
import 컴포넌트2 from '../day01/2_컴포넌트'; // 다른 폴더에 있는 컴포넌트 호출하는 방법
import 컴포넌트3 from '../day01/3_컴포넌트'; // 다른 폴더에 있는 컴포넌트 호출하는 방법
import 컴포넌트4 from '../day01/4_컴포넌트'; // 다른 폴더에 있는 컴포넌트 호출하는 방법

export default function 라우터컴포넌트 ( props ) {
    return(<>
        <BrowserRouter> {/* 브라우저 라우터 시작 */}
        <고정컴포넌트/> {/* BrowserRouter 안에 있고 Routes 밖에 있는 컴포넌트 */}
            <Routes>
                <Route path="/day01/컴포넌트1" element = { <컴포넌트1 />} /> {/* 컴포넌트 연결할 가상 URL 경로 정의 */}
                <Route path="/day01/컴포넌트2" element = { <컴포넌트2 />} /> {/* 컴포넌트 연결할 가상 URL 경로 정의 */}
                <Route path="/day01/컴포넌트3" element = { <컴포넌트3 />} /> {/* 컴포넌트 연결할 가상 URL 경로 정의 */}
                <Route path="/day01/컴포넌트4" element = { <컴포넌트4 />} /> {/* 컴포넌트 연결할 가상 URL 경로 정의 */}
            </Routes>
        </BrowserRouter> {/* 브라우저 라우터 끝 */}

    </>);
}

function 고정컴포넌트( props ){
    return(<>
    <div>
        <a href="/day01/컴포넌트1"> 컴포넌트1 </a>
        <a href="/day01/컴포넌트2"> 컴포넌트2 </a>
        <a href="/day01/컴포넌트3"> 컴포넌트3 </a>
        <a href="/day01/컴포넌트4"> 컴포넌트4 </a>
    </div>
    <div>
        <Link to="/day01/컴포넌트1"> 컴포넌트1 </Link>
        <Link to="/day01/컴포넌트2"> 컴포넌트2 </Link>
        <Link to="/day01/컴포넌트3"> 컴포넌트3 </Link>
        <Link to="/day01/컴포넌트4"> 컴포넌트4 </Link>
    </div>
    </>)
}