


function 컴포넌트4(){
    return(<>
        <input type="text" value="데이터" />
        <내가만든속성 이름="신예지" 나이 = {28}/>
        <내가만든속성 이름="이도규" 나이 = {29}/>
    </>)
}

/* 매개변수 전달을 해볼것이다. 음하하 */
function 내가만든속성( props ){ // props : 컴포넌트의 매개변수
    // JS 구역 ----------------------------------------------------------
    console.log( props );
    // return은 JSX 구역 ------------------------------------------------
    return(<>
        <div> 컴포넌트 4가 전달한 속성 : { props.이름 }, { props.나이 } </div>
    </>)
}

export default 컴포넌트4

