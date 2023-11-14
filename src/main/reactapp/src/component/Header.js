
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect, useRef } from 'react';
export default function Header( props ){


    let 변수 = 10;
    변수++;
    // 랜더링 후
    console.log(변수);
    let Ref변수 = useRef(10);
    Ref변수++;
    // 랜더링 후
    console.log(Ref변수);

    // 2. 소켓
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
        클라이언트소켓.onmessage = (e) => { console.log(e); };

        // 5. 메세지 보내기
        //클라이언트소켓.send('안녕')

    //},[])

    // 2. 클라이언트 소켓 메세지 전송
    const msgSend = (e) => { 클라이언트소켓.send("안녕")}

    // 웹 소켓 통신 end ==============================================


    // 1. 로그인 상태를 저장할 상태변수
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
    <button type="bottom" onClick={ msgSend } > 전송 </button>
        <h2> Ezen React <span className="with">(with. yeji)</span></h2>
            <ul className="reactUL">
                <li> <Link to='/example'> 리액트예제 </Link> </li>
                <li> <Link to='/'> TODO </Link> </li>
                <li> <Link to='/'> 비회원게시판 </Link> </li>
                <li> <Link to='/board/list'> 회원게시판 </Link> </li>
                <li> <Link to='/admin/product'> 제품관리 </Link> </li>
                 { /* 상함 연산자 | 조건 ? 참 : 거짓 */}


                 { login == null
                 ? (<>
                    <li> <Link to='/login'> 로그인 </Link> </li>
                    <li> <Link to='/signup'> 회원가입 </Link> </li>
                 </>)
                 : (<>
                     <li> { login.memail } 님 </li>
                     <li> <a href='/info'> 내정보 </a> </li>
                     <li> <div onClick = { logout } className="logoutbtn"> 로그아웃 </div> </li>
                 </>) }

            </ul>
    </header>
    </>)
}