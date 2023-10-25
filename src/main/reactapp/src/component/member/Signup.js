import style from './Member.css';
import { Link } from 'react-router-dom';
import axios from 'axios';

export default function Signup( props ){

    // 1. 회원가입 버튼을 클릭 했을때
    function onSignup(e){
        console.log(e);
        // 2. axios를 이용한 restApi 로 spring Controller 데이터 전달
            // 3. 데이터 구성
            let info = {
                memail : document.querySelector('.memail').value,
                mpassword : document.querySelector('.mpassword').value,
                mname : document.querySelector('.mname').value,
                mphone : document.querySelector('.mphone').value
            };
            console.log(info);
            console.log(info.mpassword);

            // 비밀번호 확인
            let mpasswordCom = document.querySelector('.mpasswordCom').value;
            /*let json = JSON.stringify(info)
            const config = {"Content-Type": 'application/json'};*/

            // 4. 유효성검사
            if( info.mpassword == mpasswordCom ){
                // 5. !! AXIOS 통신 [ Spring Controller 매핑 확인]
                axios
                    .post('/member/do', info)
                        .then( r=>{
                            if(r.data==true){
                                alert('회원가입 성공');
                                window.location.href = '/login'; // get 방식의 요청
                            } else{alert('회원가입 실패');}
                        })
            } else{alert('비밀번호가 일치하지 않습니다.');}
    }



    return(<>
    <div className="signupBox">
        <h3 className="joinTitle">Signup</h3>
        <form className="info">
            <h4> 이메일[아이디] </h4>
            <input className="memail" type="text" /> <br/>
            <h4> 비밀번호 </h4>
            <input className="mpassword" type="password" /> <br/>
            <h4>  비밀번호확인 </h4>
            <input className="mpasswordCom" type="password" /> <br/>
            <h4> 이름  </h4>
            <input className="mname" type="text" /> <br/>
            <h4> 전화번호 </h4>
            <input className="mphone" type="text" /> <br/>
        </form>
        <button className="signupbtn" onClick={ onSignup } type="button">Signup</button>
    </div>
    </>)
}