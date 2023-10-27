
// ------------------------- JS 형식 ------------------------ //
import { useState, useEffect } from 'react'
export default function 생명주기컴포넌트( props ) {

    // 1. uesState함수를 이용한 초기값 0으로 하는 [ 변수, 수정함수 ] 리턴받음
    let [ value, setValue ] = useState(0);
    const valueUpdate =  (e) => { value++; setValue( value ); }

    let [ value2, setValue2 ] = useState(0);
    const value2Update = (e) => { value2++; setValue2( value2 ); }

    // 2. 컴포넌트 생명주시 1. 탄생 | 2. 업데이트 | 3. 제거
        // 1. 컴포넌트 탄생 / 업데이트 -> 컴포넌트의 첫 실해과 업데이트에 사용되는 함수
        // uesEffect( 함수 );
    useEffect( () => { console.log('[1]Ettect 실행')} );
        // 2. 컴포넌트 탄생 -> 컴포넌트가 첫 실행 될때만 실행되는 함수
        // uesEffect( 함수, [] );
    useEffect( () => { console.log('[2]Ettect 실행')}, [] );
        // 3. 컴포넌트 탄생 / 특정상태 업데이트  ->  value가 바뀌면 실행되는 함수
        // uesEffect( 함수, [의존성배열] );
    useEffect( () => { console.log('[3]Ettect 실행')}, [ value, value2 ] );



    // ------------------- JSX 형식 START --------------- //
    return(<>
        <div> { value } </div>
        <button onClick={ valueUpdate } > + </button>

        <div> { value2 } </div>
        <button onClick={ value2Update } > + </button>
    </>);
    // ------------------- JSX 형식 END  --------------- //

}
// ------------------------- JS 형식 ------------------------ //


/*

    컴포넌트의 생명주기 [ life cycle ]
        탄생 [ Mounting ] ------------------------> 업데이트 [ Updating ] ------------------------> 제거 [ UnMount ]
        1. 함수/컴포넌트가 생성이 되고                                                                     |
                |                                                                                      |
        2. 그 함수/컴포넌트가 랜더링(호출) 될 때         1. setState()    : 상태 변경 되었을때                |
                |                                   2. forceUpdate() : 강제 랜더링                       |
                |                                   3. new props     : props가 변경되었을 때              |
                |                                         |                                             |
        가상 DOM 업데이트                             가상 DOM 업데이트                                     |
                |                                         |                                             |
                |                                         |                                             |
                |                                         |                                             |
        컴포넌트 탄생 [Mounting]                      컴포넌트업데이드 [Updating]                    컴포넌트업데이드 [Updating]
        아래 3개 모두 실행 된다.                         아래 2개만 실행된다.
      useEffect ( () => {} )                        useEffect ( () => {} )
      useEffect ( () => {} , [])                    useEffect ( () => {} , [useState변수명])
      useEffect ( () => {} , [useState변수명])






*/