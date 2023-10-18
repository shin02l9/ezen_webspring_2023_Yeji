
function 과제day01(){
    return(<>
    <도서목록 />
    </>)
}

function 도서목록(){
    return(<>
        <도서 도서명="이것이 자바다" 저자="유재석" 가격={30000} />
        <도서 도서명="이것이 파이썬" 저자="강호동" 가격={25000} />
        <도서 도서명="이것이 리액트" 저자="신동엽" 가격={20000} />
    </>)
}

function 도서(props){
    return(<>
    <h3> 도서명 : { props.도서명} </h3>
    <div> 저자 : {props.저자} | 소비자가격 : {props.가격} </div>
    </>)
}

export default 과제day01

