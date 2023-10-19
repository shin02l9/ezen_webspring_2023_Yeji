// 확장자 Js, JSX 둘다 가능하다.


// 1. 컴포넌트 문법 원형
//function 컴포넌트명( props ){
//    return(<></>);
//}
//export default 컴포넌트명 ;

// 2. 컴포넌트 문법 원형
//export default function 컴포넌트명( props ){
//    return(<></>);
//}

import style from './컴포넌트.css';

export default function Css컴포넌트(props){
    // 1. CSS 를 객체에 속성으로 선언하기
        // 1. 마크업 style속성 = { CSS속성이 있는 객체 }
        // 2. 마크업 stlye속성 = { { 속성 : 값, 속성 : 값 } }
    // 2. CSS 파일에 순수 CSS 작성
        // import 하는 법 => import style from './컴포넌트.css';
    const cssStyle ={
        backgroundColor:'red',
        width: '500px',
        height: '200px',
        margin: '0 auto'
    }
    return(<>
        <div style={cssStyle}> CSS 적용하는 방법 1 </div>
        <div style={{
                        backgroundColor:'blue',
                        width: '500px',
                        height: '200px',
                        margin: '0 auto'
                    }}> CSS 적용하는 방법 2 </div>
        <div className="box3" > CSS 적용하는 방법 3 </div>
    </>)
};

