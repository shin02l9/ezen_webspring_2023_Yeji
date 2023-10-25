/*
    * HTTP 기반의 비동기 통신 함수
    Axios <------> AJAX
    React <------> jquery
    1. 설치
        1. 터미널 [ alt + f12 ]
        2. 리액트 프로젝트 폴더내에 설치하기
        ( 선생님 깃 주석 확인하기 )
*/
import axios from 'axios';
export default function Axios컴포넌트( props ){

    // 컴포넌트(함수)안에서 함수 정의 하기
    // 1. 기본함수
    function 함수1(e){ console.log(e) } // e:event 발생 후 상태/결과 저장된 매개변수

    // 2. 화살표 함수를 저장하는 함수 *많이사용함
    const 함수2 = (e) => { console.log(e) }

    // 3. 화살표 함수에 매개변수를 받기
    const 함수3 = (e, data) => { console.log(e); console.log(data); }

    // ---------------------- Axios ----------------------
    // 1. Get --------------------------------------------
    function doGet(){
        console.log('================= doGet =================')
      //                          반환/리턴값
      // axios.http메소드타입(URL).then(response => { });
        axios.get('https://jsonplaceholder.typicode.com/posts')
            .then( response => { console.log('response ▼'); console.log(response); });

        axios.get('https://jsonplaceholder.typicode.com/posts/1') // path
            .then( result => { console.log('result ▼'); console.log(result); });

        axios.get('https://jsonplaceholder.typicode.com/comments?postId=1') // queryString
            .then( r => { console.log('r queryString ▼'); console.log(r); });

        axios.get('https://jsonplaceholder.typicode.com/comments', { params : { 'postId' : 1 }})
            .then( r => { console.log('r params ▼'); console.log(r); });
    }

    // 2. Post --------------------------------------------
    function doPost(){
        let saveInfo = { title: 'foo', body: 'bar',  userId: 1, }
        console.log('================= doPost =================')
        axios.post('https://jsonplaceholder.typicode.com/posts', saveInfo)
            .then( response => { console.log('response ▼'); console.log(response.data); });

    }
    // 3. Put --------------------------------------------
    function doPut(){
        let updateInfo = {  id: 1,  title: 'updateFoo',  body: 'updateBar',   userId: 1 }
        console.log('================= doPut =================')
        axios.put('https://jsonplaceholder.typicode.com/posts/1', updateInfo)
            .then( response => { console.log('response ▼'); console.log(response.data); });

    }

    // 4. doDelete --------------------------------------------
    function doDelete(){
        console.log('================= doDelete =================')
        axios.delete('https://jsonplaceholder.typicode.com/posts/1')
            .then( response => { console.log('response ▼'); console.log(response.data); });

    }


    return(<>

        <h3> Axios 테스트 </h3>

        {/*
            JSX에서의 이벤트 속성
                1. 이벤트명(카멜표기법),
                2. {함수명},
                3. { e => 함수( e, 매개변수1, 매개변수2 ) }
        */}

        <button type="button" onClick={ 함수1 }> 함수1 </button>
        <button type="button" onClick={ 함수2 }> 함수2 </button>
        <button type="button" onClick={ e => 함수3(e,3) }> 함수3 </button>

        <button type="button" onClick={ doGet }> doGet Axios </button>
        <button type="button" onClick={ doPost }> doPost Axios </button>
        <button type="button" onClick={ doPut }> doPut Axios </button>
        <button type="button" onClick={ doDelete }> doDelete Axios </button>

    </>)
}