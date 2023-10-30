import style from './Member.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Login( props ){

    // 1. 로그인 버튼을 클릭 했을때
    function onLogin(e){
        console.log(e);

        // 2. axios를 이용한 restApi 로 spring Controller 데이터 전달
            // 3. 데이터 구성
                let info = {
                    memail : document.querySelector('.memail').value,
                    mpassword : document.querySelector('.mpassword').value
                }
                console.log(info);
             // 4. !! AXIOS 통신 [ Spring Controller 매핑 확인]

               axios
                    .post('/member/login', info)
                        .then( r=>{
                            if(r.data==true){
                                alert('로그인 성공');
                                window.location.href = '/';
                            } else{alert('로그인 실패');}
                        })
                // CORS policy 오류 해결 방안
                    // 자바 컨트롤러에 @CrossOrigin 어노테이션 추가하기
                    // @CrossOrigin("http://localhost:3000 교차 리소스 공유


    }


    return(<>
    <div className="loginBox">
        <h3 className="joinTitle">Login</h3>
        <form className="info" action="/member/login" method="post">
            <h4> 이메일[아이디]  </h4>
            <input className="memail" name="memail" type="text" /> <br/>
            <h4> 비밀번호  </h4>
            <input className="mpassword" name="mpassword" type="password" /> <br/>
            <div className="btnBoxforlogin">
                <button className="findbtn" type="button"> 아이디찾기 </button> <span> | </span>
                <button className="findbtn" type="button"> 비밀번호찾기 </button><br/>
                <button className="loginbtn" type="submit">Login</button>
            </div>
        </form>
    </div>
    </>)
}

//
//        <form className="info">
//            <h4> 이메일[아이디]  </h4>
//            <input className="memail" type="text" /> <br/>
//            <h4> 비밀번호  </h4>
//            <input className="mpassword" type="password" /> <br/>
//        </form>
//        <button className="findbtn" type="button"> 아이디찾기 </button> <span> | </span>
//        <button className="findbtn" type="button"> 비밀번호찾기 </button><br/>
//        <button className="loginbtn" onClick={ onLogin } type="button">Login</button>

//    <form action="/member/login" method="post">
//        아이디 <input type="text" placeholder='email address' name='memail' />
//        비밀번호 <input type="password"  placeholder='password' name='mpassword' />
//        <Link to=''>아이디찾기 </Link> <Link to=''> 비밀번호찾기 </Link>
//        <button type="submit">로그인</button>
//    </form>