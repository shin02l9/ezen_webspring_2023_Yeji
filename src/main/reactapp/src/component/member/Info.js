import style from './Member.css';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { useState, useEffect } from 'react';

export default function Info( props ){
        // 세션에서 현재 로그인 한 사람 정보 받기
        // let login_token = JSON.parse(sessionStorage.getItem('login_token'));



        // 호출한 정보 담아둘 곳
        const [ member,  setMember ] =useState(null);

        // 로그인 정보 호출해서 출력하기 [ 컴포넌트가 열린 후 최초 1번 실행 ]
        useEffect( ()=> {
            axios.get('/member/do').then( r => {setMember(r.data) })
        }, [])

        const mnameInputChange = (e) => {
            console.log(e.target.value); // 이 함수를 실행시킨자의 값을 가져온다.
            //let mnameInput = e.target.value;
            // setMember( mnameInput ); // 생기는 문제점
            // member 상태변수 특정 속성만 교체 해야해야해서 !!!

            //let changeMember = member; // 기존객체를 새로운 객체에 대입
            //changeMember.mname = mnameInput; // 객체의 특정 속성만 새로운값 대입
            //setMember(changeMember); // 수정된 새로운 객체를 상태변수에 대입
                // 또 다른 문제점...? 기존 객체를 새로운 객체로 만드는것 같지만 힙의 주소값이 변경되지 않아서 랜더링이 되지 않음

            //let changeMember = {...member}; // {...객체} 이렇게 작성하면 새로운 객체를 생성해서 힙주소를 변경 할 수 있다.
            //changeMember.mname = mnameInput;
            setMember( {...member, mname : e.target.value} );
        };

        const mphoneInputChange = (e) => {
            console.log(e.target.value); // 이 함수를 실행시킨자의 값을 가져온다.
            setMember( {...member, mphone : e.target.value } );
        };

        const deleteMember = (e) => {
            if( window.confirm('정말 탈퇴하시겠습니까?')){
                axios.delete("/member/do", { params : { mno : member.mno} } )
                .then( r => {
                    if( r.data ){
                        alert('회원탈퇴 성공.')
                        sessionStorage.removeItem('login_token')
                        window.location.href = '/';
                    } else { alert('회원탈퇴 실패.') }
                })
            }
        }

        const [ newPassword, setNewPassword ] = useState({ mpassword:'', mpasswordnew:'', mpasswordnewCon:''})


        const updateMember = (e) => {

            //if( window.confirm('비밀번호를 수정하십니까?') ){
                if( window.confirm('정말 수정하시겠습니까?')){
                    let newInfo = {
                        mno : member.mno, // 수정대상
                        mname : member.mname, // 수정할 값
                        mpassword : newPassword.mpassword,
                        mpasswordnew : newPassword.mpasswordnew, // 수정할 값
                        mphone : member.mphone // 수정할 값
                    }
                    axios.put("/member/do", newInfo )
                    .then( r => {
                        if( r.data ){
                            alert('회원수정 성공.')
                            window.location.href = '/';
                        } else { alert('회원수정 실패.') }
                    })
                }
            //}
        }


    return(<>
    <div className="signupBox">
        <h3 className="joinTitle">Info</h3>
        <form className="info">
            <h4> 이메일[아이디] </h4>
            <input value={ member!=null ? member.memail : '' }
                    disabled="disabled" className="memail" type="text" /> <br/>

            <h4> 기존 비밀번호 </h4>
            <input value={ newPassword.mpassword }
                    onChange={ (e) => setNewPassword ({ ...newPassword, mpassword : e.target.value }) }
                    className="mpassword" type="password" /> <br/>

            <h4> 새 비밀번호 </h4>
                        <input value={ newPassword.mpasswordnew }
                                onChange={ (e) => setNewPassword ({ ...newPassword, mpasswordnew : e.target.value }) }
                                className="mpassword" type="password" /> <br/>

            <h4> 새 비밀번호 확인 </h4>
            <input value={ newPassword.mpasswordnewCon }
                    onChange={ (e) => setNewPassword ({ ...newPassword, mpasswordnewCon : e.target.value }) }
                    className="mpasswordCon" type="password" /> <br/>

            <h4> 이름  </h4>
            <input value={ member!=null ? member.mname : '' }
                    className="mname" type="text"
                    onChange={ mnameInputChange }/> <br/>

            <h4> 전화번호 </h4>
            <input value={ member!=null ? member.mphone : '' }
                    className="mphone" type="text"
                    onChange={ mphoneInputChange }/> <br/>

        </form>
        <button className="updatebtn" onClick={ updateMember } type="button"> 정보수정 </button>
        <button className="deletebtn" onClick={ deleteMember } type="button"> 회원탈퇴 </button>
    </div>
    </>)
}