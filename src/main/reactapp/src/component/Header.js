
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect } from 'react';
export default function Header( props ){

    // 로그인 상태를 저장할 상태변수
    let [ login, setLogin ] = useState( null );


    // ---------------------------- 컴포넌트 생성될때 1번만 실행 ----------------------------
    useEffect( ()=> {
    // 회원정보 호출 [ 로그인여부 확인 ]
        axios.get('/member/do')
            .then( r => {
                console.log(r.data);
                if( r.data != '' ){
                // 브라우저 세션, 쿠키( 페이지 전환시 유지 공통점 )
                    // 1. localStorage
                    // 모든 브라우져 탭/창 공유, 브라우져가 꺼져도 유지, 자동로그인 기능 , 로그인 상태 유지
                    //          vs
                    // 2. sessionStorage
                    // 탭,창이 종료되면 사라짐
                sessionStorage.setItem( 'login_token', JSON.stringify(r.data))
                setLogin( JSON.parse(sessionStorage.getItem('login_token')) );
                }
            })
    }, [])
    // -----------------------------------------------------------------------------------

    // 로그아웃 하기
    const logout = (e) => {
        axios.get('/member/logout')
        .then( r => {
            console.log(r.data);
            if( r.data ){
                alert('로그아웃 합니다.');
                // 세션제거
                sessionStorage.removeItem( 'login_token' );
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
                     <li> <Link to='/info'> 내정보 </Link> </li>
                     <li> <div onClick = { logout } className="logoutbtn"> 로그아웃 </div> </li>
                 </>) }

            </ul>
    </header>
    </>)
}