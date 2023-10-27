
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect } from 'react';
export default function Header( props ){

    // 로그인 상태를 저장할 상태변수
    let [ login, setLogin ] = useState( null );

    // 회원정보 호출 [ 로그인여부 확인 ]
    axios.get('/member/do')
        .then( r => {
            console.log(r.data);
            if( r.data != '' ){
                setLogin( r.data );
            }
        })

    // 로그아웃 하기
    const logout = (e) => {
        axios.get('/member/logout')
        .then( r => {
            console.log(r.data);
            if( r.data ){
                window.location.reload(); // 새로고침
                // vs
                // this.forceUpdate(); // 강제 헤더만 리랜더링
            }
        })

    }


    return(<>
    <header className="headerBox">
        <h2> Ezen React <span className="with">(with. yeji)</span></h2>
            <ul className="reactUL">
                <li> <Link to='/example'> 리액트예제 </Link> </li>
                <li> <Link to='/'> TODO </Link> </li>
                <li> <Link to='/'> 비회원게시판 </Link> </li>
                <li> <Link to='/'> 회원게시판 </Link> </li>
                 { /* 상함 연산자 | 조건 ? 참 : 거짓 */}


                 { login == null
                 ? (<>
                    <li> <Link to='/login'> 로그인 </Link> </li>
                    <li> <Link to='/signup'> 회원가입 </Link> </li>
                 </>)
                 : (<>
                     <li> { login.memail } 님 </li>
                     <li> <Link to='/'> 내정보 </Link> </li>
                     <li> <div onClick = { logout } className="logoutbtn"> 로그아웃 </div> </li>
                 </>) }

            </ul>
    </header>
    </>)
}