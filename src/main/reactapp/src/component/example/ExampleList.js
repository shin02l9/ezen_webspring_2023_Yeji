


import { Link } from 'react-router-dom';
export default function ExampleList ( props ){
    return(<>
    <div style = {{ display: 'flex', justifyContent: 'space-between' }}>  </div>
        <div>
            <Link to='/example/day01/컴포넌트1' > 컴포넌트1 예제 </Link>
            <Link to='/example/day01/컴포넌트2' > 컴포넌트2 예제 </Link>
            <Link to='/example/day01/컴포넌트3' > 컴포넌트3 예제 </Link>
            <Link to='/example/day01/컴포넌트4' > 컴포넌트4 예제 </Link>
            <Link to='/example/day02/Css컴포넌트' > Css컴포넌트 예제 </Link>
            <Link to='/example/day02/CommentList' > CommentList 예제 </Link>
            <Link to='/example/day01/TodoList' > 리액트과제01 </Link>
            <Link to='/example/day02/과제1_할일목록/TodoList' > 리액트과제02 </Link>
            <Link to='/example/day04/Axlos컴포넌트' > Axios컴포넌트 </Link>
        </div>
    </>)
}